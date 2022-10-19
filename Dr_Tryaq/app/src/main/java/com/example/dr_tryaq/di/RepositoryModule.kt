package com.example.dr_tryaq.di

import android.content.Context
import com.example.dr_tryaq.data.repository.DataStoreOperationsImpl
import com.example.dr_tryaq.data.repository.Repository
import com.example.dr_tryaq.domain.repository.DataStoreOperations
import com.example.dr_tryaq.domain.use_cases.UseCases
import com.example.dr_tryaq.domain.use_cases.add_appointment.AddAppointmentUseCase
import com.example.dr_tryaq.domain.use_cases.delete_appointment.DeleteAppointmentUseCase
import com.example.dr_tryaq.domain.use_cases.get_all_ads.GetAllAdsUseCase
import com.example.dr_tryaq.domain.use_cases.get_all_appointments.GetAllAppointmentsUseCase
import com.example.dr_tryaq.domain.use_cases.get_all_departments.GetAllDepartmentsUseCase
import com.example.dr_tryaq.domain.use_cases.get_all_diagnosis.GetAllDiagnosisUseCase
import com.example.dr_tryaq.domain.use_cases.get_all_diagnosis.GetMyPatientsUseCase
import com.example.dr_tryaq.domain.use_cases.get_all_diagnosis.NewDiagnosisUseCase
import com.example.dr_tryaq.domain.use_cases.get_all_medicines.GetAllMedicinesUseCase
import com.example.dr_tryaq.domain.use_cases.get_all_services.GetAllServicesUseCase
import com.example.dr_tryaq.domain.use_cases.get_image.GetSelectedDiagnosisUseCase
import com.example.dr_tryaq.domain.use_cases.get_patient.LoginUseCase
import com.example.dr_tryaq.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.dr_tryaq.domain.use_cases.read_remember_me.*
import com.example.dr_tryaq.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.example.dr_tryaq.domain.use_cases.save_remember_me.SaveDoctorDataUseCase
import com.example.dr_tryaq.domain.use_cases.save_remember_me.SaveDoctorDepartmentUseCase
import com.example.dr_tryaq.domain.use_cases.save_remember_me.SaveRememberMeUseCase
import com.example.dr_tryaq.domain.use_cases.search_medicines.SearchMedicinesUseCase
import com.example.dr_tryaq.domain.use_cases.sign_up.ChangeServiceStateUseCase
import com.example.dr_tryaq.domain.use_cases.sign_up.NewServiceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            saveRememberMeUseCase = SaveRememberMeUseCase(repository),
            readRememberMeUseCase = ReadRememberMeUseCase(repository),
            saveDoctorDataUseCase = SaveDoctorDataUseCase(repository),
            saveDoctorDepartmentUseCase = SaveDoctorDepartmentUseCase(repository),
            readDoctorDepartmentUseCase = ReadDoctorDepartmentUseCase(repository),
            readDoctorNameUseCase = ReadDoctorNameUseCase(repository),
            readeDoctorImageUseCase = ReadeDoctorImageUseCase(repository),
            readDoctorIdUseCase = ReadDoctorIdUseCase(repository),
            getAllAdsUseCase = GetAllAdsUseCase(repository),
            getAllAppointmentsUseCase = GetAllAppointmentsUseCase(repository),
            addAppointmentUseCase = AddAppointmentUseCase(repository),
            newServiceUseCase = NewServiceUseCase(repository),
            newDiagnosisUseCase = NewDiagnosisUseCase(repository),
            deleteAppointmentUseCase = DeleteAppointmentUseCase(repository),
            changeServiceStateUseCase = ChangeServiceStateUseCase(repository),
            getAllDepartmentsUseCase = GetAllDepartmentsUseCase(repository),
            getAllDiagnosisUseCase = GetAllDiagnosisUseCase(repository),
            getAllMedicinesUseCase = GetAllMedicinesUseCase(repository),
            searchMedicinesUseCase = SearchMedicinesUseCase(repository),
            login = LoginUseCase(repository),
            getSelectedDiagnosisUseCase = GetSelectedDiagnosisUseCase(repository),
            getMyPatientsUseCase = GetMyPatientsUseCase(repository),
            getAllServicesUseCase = GetAllServicesUseCase(repository)
        )
    }

}