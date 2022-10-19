package com.example.caching.data

import androidx.room.withTransaction
import com.example.caching.api.PartAPI
import com.example.caching.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class PartRepository @Inject constructor(
    private val api: PartAPI,
    private val db: PartDataBase
) {
    private val partDao = db.partDao()

    fun getParts() = networkBoundResource(
        query = {
            partDao.getAllParts()
        },
        fetch = {
            delay(2000)
            api.getParts()
        },
        saveFetchResult = { parts ->
            db.withTransaction {
                partDao.deleteAllParts()
                partDao.insertParts(parts)
            }
        }
    )
}