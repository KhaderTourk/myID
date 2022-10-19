package com.example.tryaq.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.tryaq.data.local.TryaqDatabase
import com.example.tryaq.data.remote.TryaqApi
import com.example.tryaq.domain.model.models.Appointment
import com.example.tryaq.domain.model.remote_keys.AppointmentRemoteKeys
import javax.inject.Inject

@ExperimentalPagingApi
class AppointmentRemoteMediator @Inject constructor(
    private val tryaqApi: TryaqApi,
    private val tryaqDatabase: TryaqDatabase,
    private val patientId: Int
) : RemoteMediator<Int, Appointment>() {

    private val dao = tryaqDatabase.tryaqDao()
    private val remoteKeysDao = tryaqDatabase.tryaqRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = remoteKeysDao.getAppointmentRemoteKeys(id = 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if (diffInMinutes.toInt() <= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Appointment>): MediatorResult {
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

            val response = tryaqApi.getAllAppointments(patientId = patientId)
            if (response.appointments.isNotEmpty()) {
                tryaqDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        dao.deleteAllAppointments()
                        remoteKeysDao.deleteAllAppointmentRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.appointments.map { appointment ->
                        AppointmentRemoteKeys(
                            id = appointment.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    remoteKeysDao.addAllAppointmentRemoteKeys(appointmentRemoteKeys = keys)
                    dao.addAppointments(appointments = response.appointments)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Appointment>
    ): AppointmentRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.getAppointmentRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Appointment>
    ): AppointmentRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { model ->
                remoteKeysDao.getAppointmentRemoteKeys(id = model.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Appointment>
    ): AppointmentRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
        ?.let { model ->
                remoteKeysDao.getAppointmentRemoteKeys(id = model.id)
            }
    }

}