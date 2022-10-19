package com.example.caching.api

import com.example.caching.data.Part
import retrofit2.http.GET

interface PartAPI {
    companion object {
        const val BASE_URL = "https://script.google.com/macros/s/AKfycbwXb6g0TFt0mXKTVtGiadvoKc3DHMFN1fhYLQ8CKW72UKY3-FAX64JG9--B4bK7puzA/"
    }

    @GET("https://script.google.com/macros/s/AKfycbwXb6g0TFt0mXKTVtGiadvoKc3DHMFN1fhYLQ8CKW72UKY3-FAX64JG9--B4bK7puzA/exec")
    suspend fun getParts(): List<Part>
}