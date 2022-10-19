package com.example.tryaq.data.remote

import com.example.tryaq.domain.model.models.*
import com.example.tryaq.util.Constants.USER_TOKEN
import retrofit2.http.*

interface TryaqApi {

    @GET("/ads")
    suspend fun getAllAds(
        @Header("Authorization") jwtToken: String?= USER_TOKEN
    ): AdApiResponse

    @FormUrlEncoded
    @POST("/appointments")
    suspend fun getAllAppointments(
        @Header("Authorization") jwtToken: String?= USER_TOKEN,
        @Field("id") patientId: Int
    ): AppointmentApiResponse

    @POST("/allAppointments")
    suspend fun getAllAppointments2(
        @Header("Authorization") jwtToken: String?= USER_TOKEN
    ): AppointmentApiResponse2


    @GET("/departments")
    suspend fun getAllDepartments(
        @Header("Authorization") jwtToken: String?= USER_TOKEN
    ): DepartmentApiResponse

    @FormUrlEncoded
    @POST("/patient/diagnosis")
    suspend fun getAllDiagnosis(
        @Header("Authorization") jwtToken: String?= USER_TOKEN,
        @Field("patient_id") patientId: Int
    ): DiagnosisApiResponse

    @GET("/medicines")
    suspend fun getAllMedicines(
        @Header("Authorization") jwtToken: String?= USER_TOKEN,
    ): MedicineApiResponse

    @FormUrlEncoded
    @POST("/patient/login")
    suspend fun login(
        @Field("id") patientId: Int,
        @Field("password")password: String,
    ): PatientApiResponse

    @FormUrlEncoded
    @POST("/patient/register")
    suspend fun signUp(
        @Field("id") patientId: Int,
        @Field("password")password: String,
    ): BaseResponse

    @FormUrlEncoded
    @POST("/patient/services")
    suspend fun getDepartmentServices(
        @Header("Authorization") jwtToken: String?= USER_TOKEN,
        @Field("department_id") departmentId: Int
    ): ServiceApiResponse

    @FormUrlEncoded
    @POST("/appointments/add")
    suspend fun newAppointment(
        @Header("Authorization") jwtToken: String?= USER_TOKEN,
        @Field("id") id: Int,
        @Field("time") time: String,
        @Field("day") day: String,
        @Field("month") month: String,
        @Field("doctor_name") doctorName: String,
        @Field("date") date: String,
        @Field("doctor_image") doctorImage: String,
        @Field("service_name") serviceName: String,
        @Field("patient_id") patientId: Int,
        @Field("service_id") serviceId: Int,
        @Field("patient_name") patientName: String,
        @Field("patient_image") patientImage: String,
        @Field("department_id") departmentId: Int
    ): BaseResponse

    @FormUrlEncoded
    @POST("/appointments/cancel")
    suspend fun cancelAppointment(
        @Header("Authorization") jwtToken: String?= USER_TOKEN,
        @Field("appointment_id") appointmentId: Int
    ): BaseResponse

    @FormUrlEncoded
    @POST("/search/medicine")
    suspend fun searchMedicines(
        @Header("Authorization") jwtToken: String?= USER_TOKEN,
        @Field("medicine_name") name: String
    ): MedicineApiResponse
}