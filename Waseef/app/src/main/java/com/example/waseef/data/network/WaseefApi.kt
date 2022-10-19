package com.example.waseef.data.network


import com.example.waseef.BuildConfig
import com.example.waseef.model.*
import retrofit2.Call
import retrofit2.http.*

interface WaseefApi {

    @POST("Login.ashx")
    fun login(
        @Header("Authorization") token: String = BuildConfig.token,
        @Body login: UserDataLogin
    ): Call<LoginResponse>

    @POST("GetTransactions.ashx")
    fun getInvoicesList(
        @Header("Authorization") token: String = "knhOeWvcNjCBgAPIGxt",
        @Body login: InvoicesListData
    ): Call<AllInvoicesResponse>

    @POST("SendInvoice.ashx")
    fun newInvoice(
        @Header("Authorization") token: String = "knhOeWvcNjCBgAPIGxt",
        @Body note: InvoiceDataAdd
    ): Call<NewInvoiceResponse>

    @GET("EnquiryInvoice.ashx")
    fun search(
        @Query("criteria") code:Int,
        @Query("type") type:Int
    ): Call<SearchResponse>

}
