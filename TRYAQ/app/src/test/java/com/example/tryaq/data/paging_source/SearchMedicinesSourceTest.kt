package com.example.tryaq.data.paging_source

import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import com.example.tryaq.data.remote.FakeTryaqApi
import com.example.tryaq.data.remote.TryaqApi
import com.example.tryaq.domain.model.models.Medicine
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class SearchMedicinesSourceTest {
    private lateinit var tryaqApi: TryaqApi
    private lateinit var medicines: List<Medicine>
    @Before
    fun setup() {
        tryaqApi = FakeTryaqApi()
        medicines = listOf(
            Medicine(1,"m1",15.1,"",1),
            Medicine(2,"m2",25.2,"",1),
            Medicine(3,"m3",35.3,"",1),)
    }
    @Test
    fun `Search api with existing medicine name, expect single medicine result, assert LoadResult_Page`() =
        runTest {
            val heroSource = SearchMedicinesSource(tryaqApi = tryaqApi, query = "1")
            assertEquals<LoadResult<Int, Medicine>>(
                expected = LoadResult.Page(
                    data = listOf(medicines.first()),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroSource.load(
                    LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with existing medicine name, expect multiple medicines result, assert LoadResult_Page`() =
        runTest {
            val heroSource = SearchMedicinesSource(tryaqApi = tryaqApi, query = "m")
            assertEquals<LoadResult<Int, Medicine>>(
                expected = LoadResult.Page(
                    data = listOf(medicines.first(), medicines[1], medicines[2]),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroSource.load(
                    LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with empty medicine name, assert empty heroes list and LoadResult_Page`() =
        runTest {
            val heroSource = SearchMedicinesSource(tryaqApi = tryaqApi, query = "")
            val loadResult = heroSource.load(
                LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            val result = tryaqApi.searchMedicines(jwtToken = "", name = "").medicines

            assertTrue { result.isEmpty() }
            assertTrue { loadResult is LoadResult.Page }
        }

    @Test
    fun `Search api with non_existing medicine name, assert empty medicine list and LoadResult_Page`() =
        runTest {
            val heroSource = SearchMedicinesSource(tryaqApi = tryaqApi, query = "Unknown")
            val loadResult = heroSource.load(
                LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            val result = tryaqApi.searchMedicines(jwtToken = "", name = "Unknown").medicines

            assertTrue { result.isEmpty() }
            assertTrue { loadResult is LoadResult.Page }
        }

}
