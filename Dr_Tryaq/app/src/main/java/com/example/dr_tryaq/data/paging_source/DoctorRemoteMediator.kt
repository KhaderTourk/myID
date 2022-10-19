package com.example.dr_tryaq.data.paging_source

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.dr_tryaq.data.local.TryaqDatabase
import com.example.dr_tryaq.data.remote.TryaqApi
import com.example.dr_tryaq.domain.model.models.Doctor
import com.example.dr_tryaq.domain.model.remote_keys.DoctorRemoteKeys
import javax.inject.Inject

@ExperimentalPagingApi
class DoctorRemoteMediator @Inject constructor(
    private val tryaqApi: TryaqApi,
    private val tryaqDatabase: TryaqDatabase,
    private val password: String,
    private val doctorId: Int
) : RemoteMediator<Int, Doctor>() {

    private val dao = tryaqDatabase.tryaqDao()
    private val remoteKeysDao = tryaqDatabase.tryaqRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = remoteKeysDao.getDoctorRemoteKeys(id = 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if (diffInMinutes.toInt() <= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }


    override suspend fun load(loadType: LoadType, state: PagingState<Int, Doctor>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            Log.e("doctorId", doctorId.toString())
            Log.e("password", password)
            val response = tryaqApi.login(doctorId = doctorId, password = password)
            Log.e("response", response.doctors.toString())
            Log.e("response", response.message.toString())
            if (response.doctors.isNotEmpty()) {
                if (loadType == LoadType.REFRESH) {
                    dao.deleteAllDoctors()
                    remoteKeysDao.deleteAllDoctorRemoteKeys()
                }
                tryaqDatabase.withTransaction {
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.doctors.map { doctor ->
                        DoctorRemoteKeys(
                            id = doctor.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    remoteKeysDao.addAllDoctorRemoteKeys(doctorRemoteKeys = keys)
                    dao.addDoctors(doctors = response.doctors)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Doctor>
    ): DoctorRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.getDoctorRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Doctor>
    ): DoctorRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { model ->
                remoteKeysDao.getDoctorRemoteKeys(id = model.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Doctor>
    ): DoctorRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { model ->
                remoteKeysDao.getDoctorRemoteKeys(id = model.id)
            }
    }

}