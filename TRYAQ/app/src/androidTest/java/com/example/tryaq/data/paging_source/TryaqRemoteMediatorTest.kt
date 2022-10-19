package com.example.tryaq.data.paging_source

import androidx.paging.*
import androidx.paging.RemoteMediator.*
import androidx.test.core.app.ApplicationProvider
import com.example.tryaq.data.local.TryaqDatabase
import com.example.tryaq.data.remote.FakeTryaqApi2
import com.example.tryaq.domain.model.models.Ad
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
class TryaqRemoteMediatorTest {
    private lateinit var tryaqApi: FakeTryaqApi2
    private lateinit var tryaqDatabase: TryaqDatabase
    @Before
    fun setup() {
        tryaqApi = FakeTryaqApi2()
        tryaqDatabase = TryaqDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true)
    }
    @After
    fun cleanup() { tryaqDatabase.clearAllTables() }
    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() =
        runBlocking {
            val remoteMediator = AdRemoteMediator(
                tryaqApi = tryaqApi, tryaqDatabase = tryaqDatabase
            )
            val pagingState = PagingState<Int, Ad>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is MediatorResult.Success)
            assertFalse((result as MediatorResult.Success).endOfPaginationReached)
        }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadSuccessAndEndOfPaginationTrueWhenNoMoreData() =
        runBlocking {
            tryaqApi.clearData()
            val remoteMediator = AdRemoteMediator(
                tryaqApi = tryaqApi,
                tryaqDatabase = tryaqDatabase
            )
            val pagingState = PagingState<Int, Ad>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is MediatorResult.Success)
            assertTrue((result as MediatorResult.Success).endOfPaginationReached)
        }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() =
        runBlocking {
            tryaqApi.addException()
            val remoteMediator = AdRemoteMediator(
                tryaqApi = tryaqApi,
                tryaqDatabase = tryaqDatabase
            )
            val pagingState = PagingState<Int, Ad>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is MediatorResult.Error)
        }

}