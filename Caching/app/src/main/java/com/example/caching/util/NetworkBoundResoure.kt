package com.example.caching.util

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resourse.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resourse.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resourse.Error(throwable, it) }
        }
    } else {
        query().map { Resourse.Success(it) }
    }

    emitAll(flow)
}