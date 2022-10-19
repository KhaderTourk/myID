package com.example.tryaq.data.remote

import com.example.tryaq.domain.model.models.*
import java.io.IOException

class FakeTryaqApi2: TryaqApi{

private val ads: Map<Int, List<Ad>> by lazy {
    mapOf(
        1 to page1,
        2 to page2,
        3 to page3,
        4 to page4,
        5 to page5
    )
}

private var page1 = listOf(
    Ad(
        id = 1,
        title = "Sasuke",
        image = "/images/sasuke.jpg",
        date = "",
        status = 1
    ),
    Ad(
        id = 2,
        title = "Sasuke",
        image = "/images/sasuke.jpg",
        date = "",
        status = 1
    ),
    Ad(
        id = 3,
        title = "Sasuke",
        image = "/images/sasuke.jpg",
        date = "",
        status = 1

    )
)
private var page2 = listOf(
    Ad(
        id = 4,
        title = "Sasuke",
        image = "/images/sasuke.jpg",
        date = "",
        status = 1
    ),
    Ad(
        id = 5,
        title = "Sasuke",
        image = "/images/sasuke.jpg",
        date = "",
        status = 1
    ),
    Ad(
        id = 6,
        title = "Sasuke",
        image = "/images/sasuke.jpg",
        date = "",
        status = 1
    )
)
private var page3 = listOf(
    Ad(
        id = 7,
        title = "Sasuke",
        image = "/images/sasuke.jpg",
        date = "",
        status = 1
    ),
    Ad(
        id = 8,
        title = "Sasuke",
        image = "/images/sasuke.jpg",
        date = "",
        status = 1
    ),
    Ad(
        id = 9,
        title = "Sasuke",
        image = "/images/sasuke.jpg",
        date = "",
        status = 1
    )
)
private var page4 = listOf(
    Ad(
        id = 10,
        title = "Sasuke",
        image = "/images/sasuke.jpg",
        date = "",
        status = 1
    ),
    Ad(
        id = 11,
        title = "Sasuke",
        image = "/images/sasuke.jpg",
        date = "",
        status = 1
    ),
    Ad(
        id = 12,
        title = "Sasuke",
        image = "/images/sasuke.jpg",
        date = "",
        status = 1
    )
)
private var page5 = listOf(
    Ad(
        id = 13,
        title = "Sasuke",
        image = "/images/sasuke.jpg",
        date = "",
        status = 1
    ),
    Ad(
        id = 14,
        title = "Sasuke",
        image = "/images/sasuke.jpg",
        date = "",
        status = 1
    ),
    Ad(
        id = 15,
        title = "Sasuke",
        image = "/images/sasuke.jpg",
        date = "",
        status = 1
    )
)

fun clearData() {
    page1 = emptyList()
}

private var exception = false

fun addException() {
    exception = true
}


    override suspend fun getAllAds(jwtToken: String?): AdApiResponse {
        if (exception) {
            throw IOException()
        }

        return AdApiResponse(
            success = true,
            message = "ok",
            prevPage = null,
            nextPage = null,
            ads = ads[1]!!,
            lastUpdated = 0L
        )
    }

    override suspend fun getAllAppointments(
        jwtToken: String?,
        patientId: Int
    ): AppointmentApiResponse {
        return AppointmentApiResponse(
            success = false,
            message = "",
            prevPage = null,
            nextPage = null,
            appointments = emptyList(),
            lastUpdated = 0L
        )
    }

    override suspend fun getAllAppointments2(jwtToken: String?): AppointmentApiResponse2 {
        return AppointmentApiResponse2(
            success = false,
            message = "",
            prevPage = null,
            nextPage = null,
            appointments = emptyList(),
            lastUpdated = 0L
        )
    }

    override suspend fun getAllDepartments(jwtToken: String?): DepartmentApiResponse {
        return DepartmentApiResponse(
            success = false,
            message = "",
            prevPage = null,
            nextPage = null,
            departments = emptyList(),
            lastUpdated = 0L
        )
    }

    override suspend fun getAllDiagnosis(jwtToken: String?, patientId: Int): DiagnosisApiResponse {
        return DiagnosisApiResponse(
            success = false,
            message = "",
            prevPage = null,
            nextPage = null,
            diagnosis = emptyList(),
            lastUpdated = 0L
        )
    }

    override suspend fun getAllMedicines(jwtToken: String?): MedicineApiResponse {
        return MedicineApiResponse(
            success = false,
            message = "",
            prevPage = null,
            nextPage = null,
            medicines = emptyList(),
            lastUpdated = 0L
        )
    }

    override suspend fun login(patientId: Int, password: String): PatientApiResponse {
        return PatientApiResponse(
            success = false,
            message = "",
            prevPage = null,
            nextPage = null,
            patient = emptyList(),
            lastUpdated = 0L
        )
    }

    override suspend fun signUp(patientId: Int, password: String): BaseResponse {
        return BaseResponse(
            success = false,
            message = "",
            code = 0,
            lastUpdated = 0L
        )
    }

    override suspend fun getDepartmentServices(
        jwtToken: String?,
        departmentId: Int
    ): ServiceApiResponse {
        return ServiceApiResponse(
            success = false,
            message = "",
            prevPage = null,
            nextPage = null,
            services = emptyList(),
            lastUpdated = 0L
        )
    }

    override suspend fun newAppointment(
        jwtToken: String?,
        id: Int,
        time: String,
        day: String,
        month: String,
        doctorName: String,
        date: String,
        doctorImage: String,
        serviceName: String,
        patientId: Int,
        serviceId: Int,
        departmentId: Int
    ): BaseResponse {
        return BaseResponse(
            success = false,
            message = "",
            code = 0,
            lastUpdated = 0L
        )
    }

    override suspend fun cancelAppointment(jwtToken: String?, appointmentId: Int): BaseResponse {
        return BaseResponse(
            success = false,
            message = "",
            code = 0,
            lastUpdated = 0L
        )
    }

    override suspend fun searchMedicines(jwtToken: String?, name: String): MedicineApiResponse {
        return MedicineApiResponse(
            success = true,
            message = "success",
            medicines = emptyList(),
            lastUpdated = 0L
        )
    }

    private fun calculate(page: Int): Map<String, Int?> {
        if (page1.isEmpty()) {
            return mapOf("prevPage" to null, "nextPage" to null)
        }
        var prevPage: Int? = page
        var nextPage: Int? = page
        if (page in 1..4) {
            nextPage = nextPage?.plus(1)
        }
        if (page in 2..5) {
            prevPage = prevPage?.minus(1)
        }
        if (page == 1) {
            prevPage = null
        }
        if (page == 5) {
            nextPage = null
        }
        return mapOf("prevPage" to prevPage, "nextPage" to nextPage)
    }
}