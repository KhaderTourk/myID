package com.example.dr_tryaq.domain.use_cases.add_appointment

import androidx.paging.PagingData
import com.example.dr_tryaq.data.repository.Repository
import com.example.dr_tryaq.domain.model.models.BaseResponse
import kotlinx.coroutines.flow.Flow

class AddAppointmentUseCase (
    private val repository: Repository
) {
    operator fun invoke(
        id: Int,
        day: String,
        date: String,
        time: String,
        month: String,
        doctorName: String,
        doctorImage: String,
        serviceName: String,
        patientName: String,
        patientImage: String,
        patientId: Int,
        serviceId: Int,
        departmentId: Int
    ): Flow<PagingData<BaseResponse>> {
        return repository.addAppointment(
            id = id,
            day = day,
            date = date,
            time = time,
            month = month,
            doctorName=doctorName,
            doctorImage=doctorImage,
            serviceName=serviceName,
            patientName = patientName,
            patientImage = patientImage,
            patientId=patientId,
            serviceId=serviceId,
            departmentId=departmentId
        )
    }
}