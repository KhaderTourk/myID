package com.example.dr_tryaq.data.remote

import com.example.dr_tryaq.domain.model.models.*
import com.example.dr_tryaq.util.Constants.USER_TOKEN
import retrofit2.http.*

interface TryaqApi {

    @GET("/ads")
    suspend fun getAllAds(
        @Header("Authorization") jwtToken: String?= USER_TOKEN
    ): AdApiResponse

    @FormUrlEncoded
    @POST("/doctor/appointments")
    suspend fun getAllAppointments(
        @Header("Authorization") jwtToken: String?= USER_TOKEN,
        @Field("department_id") departmentId: Int
    ): AppointmentApiResponse

    @GET("/departments")
    suspend fun getAllDepartments(
        @Header("Authorization") jwtToken: String?= USER_TOKEN
    ): DepartmentApiResponse

    @FormUrlEncoded
    @POST("/doctor/diagnosis")
    suspend fun getAllDiagnosis(
        @Header("Authorization") jwtToken: String?= USER_TOKEN,
        @Field("doctor_id") doctorId: Int
    ): DiagnosisApiResponse

    @GET("/medicines")
    suspend fun getAllMedicines(
        @Header("Authorization") jwtToken: String?= USER_TOKEN,
    ): MedicineApiResponse

    @FormUrlEncoded
    @POST("/doctor/login")
    suspend fun login(
        @Field("id") doctorId: Int,
        @Field("password") password: String,
    ): DoctorApiResponse

    @FormUrlEncoded
    @POST("/services/add")
    suspend fun newService(
        @Header("Authorization") jwtToken: String?= USER_TOKEN,
        @Field("user_id") adminId: Int = 111111111,
        @Field("department_id") departmentId: Int,
        @Field("time_of_work") timeOfWork: String = "8am-2pm",
        @Field("name")name: String,
        @Field("image")image: String,
        @Field("price")price: String,
    ): BaseResponse

    @FormUrlEncoded
    @POST("/services")
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
//f54neYX9Q7u3lMuB1aaUo0:APA91bGxyEo6SzmLjedezJ6dJs8nNHCBmJ_p0jGNs-OxP6xIzxVnq51Co3eEOd2m7fNAXc6dT1UIN6OuY0_11mqJQ7P4GABMMRYaOJvbPKKzJSMwal_aYHVDB2ixufTAojqHEZjraZcW
    @FormUrlEncoded
    @POST("/diagnosis/add")
    suspend fun newDiagnosis(
        @Header("Authorization") jwtToken: String?= USER_TOKEN,
        @Field("doctor_id") doctorId: Int,
        @Field("time") time: String,
        @Field("analysis_required") analysisRequired: String,
        @Field("medicine_required") medicineRequired: String,
        @Field("doctor_name") doctorName: String,
        @Field("date") date: String,
        @Field("is_analysis") isAnalysis: Int,
        @Field("service_image") serviceImage: String,
        @Field("service_name") serviceName: String,
        @Field("patient_id") patientId: Int,
        @Field("service_id") serviceId: Int,
        @Field("patient_name") patientName: String,
        @Field("patient_image") patientImage: String,
        @Field("note") note: String,
        @Field("analyzes_result") analyzesResult: String,
        @Field("appointment_id") appointmentId: Int
    ): BaseResponse

    @FormUrlEncoded
    @POST("/appointments/cancel")
    suspend fun cancelAppointment(
        @Header("Authorization") jwtToken: String?= USER_TOKEN,
        @Field("appointment_id") appointmentId: Int
    ): BaseResponse

    @FormUrlEncoded
    @POST("/services/changeStatus")
    suspend fun changeServiceState(
        @Header("Authorization") jwtToken: String?= USER_TOKEN,
        @Field("user_id") adminId: Int = 111111111,
        @Field("service_id") serviceId: Int
    ): BaseResponse

    @FormUrlEncoded
    @POST("/search/medicine")
    suspend fun searchMedicines(
        @Header("Authorization") jwtToken: String?= USER_TOKEN,
        @Field("medicine_name") name: String
    ): MedicineApiResponse
}