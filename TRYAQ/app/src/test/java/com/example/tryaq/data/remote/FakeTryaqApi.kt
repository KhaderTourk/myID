package com.example.tryaq.data.remote

import com.example.tryaq.domain.model.models.*

class FakeTryaqApi: TryaqApi {
    private val medicines = listOf(
        Medicine(1,"m1",15.1,"",1),
        Medicine(2,"m2",25.2,"",1),
        Medicine(3,"m3",35.3,"",1),
    )
    override suspend fun searchMedicines(jwtToken: String?, name: String): MedicineApiResponse {
        val searchedMedicines = findMedicines(name = name)
        return MedicineApiResponse(
            success = true,
            message = "success",
            medicines = searchedMedicines,
            lastUpdated = 0L
        )
    }
    private fun findMedicines(name: String): List<Medicine> {
        val founded = mutableListOf<Medicine>()
        return if (name.isNotEmpty()) {
            medicines.forEach { medicine ->
                if (medicine.name.lowercase().contains(name.lowercase())) {
                    founded.add(medicine)
                }
            }
            founded
        } else {
            emptyList()
        }
    }

    override suspend fun getAllAds(jwtToken: String?): AdApiResponse {
       return AdApiResponse(
           success = false,
           message = "",
           prevPage = null,
           nextPage = null,
           ads = emptyList(),
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



}