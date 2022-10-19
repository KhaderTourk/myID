package com.example.dr_tryaq.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.dr_tryaq.data.local.TryaqDatabase
import com.example.dr_tryaq.data.remote.TryaqApi
import com.example.dr_tryaq.domain.model.models.Department
import com.example.dr_tryaq.domain.model.remote_keys.DepartmentRemoteKeys
import javax.inject.Inject


@ExperimentalPagingApi
class DepartmentRemoteMediator  @Inject constructor(
    private val tryaqApi: TryaqApi,
    private val tryaqDatabase: TryaqDatabase
) : RemoteMediator<Int, Department>() {

    private val dao = tryaqDatabase.tryaqDao()
    private val remoteKeysDao = tryaqDatabase.tryaqRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = remoteKeysDao.getDepartmentRemoteKeys(id = 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if (diffInMinutes.toInt() <= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Department>): MediatorResult {
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

            val response = tryaqApi.getAllDepartments()
            if (response.departments.isNotEmpty()) {
                tryaqDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        dao.deleteAllDepartments()
                        remoteKeysDao.deleteAllDepartmentRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.departments.map { departments ->
                        DepartmentRemoteKeys(
                            id = departments.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    remoteKeysDao.addAllDepartmentRemoteKeys(departmentRemoteKeys = keys)
                    dao.addDepartments(departments = response.departments)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Department>
    ): DepartmentRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.getDepartmentRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Department>
    ): DepartmentRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { model ->
                remoteKeysDao.getDepartmentRemoteKeys(id = model.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Department>
    ): DepartmentRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { model ->
                remoteKeysDao.getDepartmentRemoteKeys(id = model.id)
            }
    }
}