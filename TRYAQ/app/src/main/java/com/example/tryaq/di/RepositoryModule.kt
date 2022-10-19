package com.example.tryaq.di

import android.content.Context
import com.example.tryaq.data.repository.DataStoreOperationsImpl
import com.example.tryaq.data.repository.Repository
import com.example.tryaq.domain.repository.DataStoreOperations
import com.example.tryaq.domain.use_cases.UseCases
import com.example.tryaq.domain.use_cases.add_appointment.AddAppointmentUseCase
import com.example.tryaq.domain.use_cases.delete_appointment.DeleteAppointmentUseCase
import com.example.tryaq.domain.use_cases.get_all_ads.GetAllAdsUseCase
import com.example.tryaq.domain.use_cases.get_all_appointments.GetAllAppointmentsUseCase
import com.example.tryaq.domain.use_cases.get_all_appointments.GetAllAppointmentsUseCase2
import com.example.tryaq.domain.use_cases.get_all_departments.GetAllDepartmentsUseCase
import com.example.tryaq.domain.use_cases.get_all_diagnosis.GetAllDiagnosisUseCase
import com.example.tryaq.domain.use_cases.get_all_medicines.GetAllMedicinesUseCase
import com.example.tryaq.domain.use_cases.get_all_services.GetAllServicesUseCase
import com.example.tryaq.domain.use_cases.get_image.GetSelectedDiagnosisUseCase
import com.example.tryaq.domain.use_cases.get_patient.GetPatientUseCase
import com.example.tryaq.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.tryaq.domain.use_cases.read_remember_me.ReadPatientIdUseCase
import com.example.tryaq.domain.use_cases.read_remember_me.ReadPatientNameUseCase
import com.example.tryaq.domain.use_cases.read_remember_me.ReadRememberMeUseCase
import com.example.tryaq.domain.use_cases.read_remember_me.ReadePatientImageUseCase
import com.example.tryaq.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.example.tryaq.domain.use_cases.save_remember_me.SavePatientDataUseCase
import com.example.tryaq.domain.use_cases.save_remember_me.SaveRememberMeUseCase
import com.example.tryaq.domain.use_cases.search_medicines.SearchMedicinesUseCase
import com.example.tryaq.domain.use_cases.sign_up.SignUpUseCase
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
            savePatientDataUseCase = SavePatientDataUseCase(repository),
            readPatientNameUseCase = ReadPatientNameUseCase(repository),
            readePatientImageUseCase = ReadePatientImageUseCase(repository),
            readPatientIdUseCase = ReadPatientIdUseCase(repository),
            getAllAdsUseCase = GetAllAdsUseCase(repository),
            getAllAppointmentsUseCase = GetAllAppointmentsUseCase(repository),
            getAllAppointmentsUseCase2 = GetAllAppointmentsUseCase2(repository),
            addAppointmentUseCase = AddAppointmentUseCase(repository),
            signUpUseCase = SignUpUseCase(repository),
            deleteAppointmentUseCase = DeleteAppointmentUseCase(repository),
            getAllDepartmentsUseCase = GetAllDepartmentsUseCase(repository),
            getAllDiagnosisUseCase = GetAllDiagnosisUseCase(repository),
            getAllMedicinesUseCase = GetAllMedicinesUseCase(repository),
            searchMedicinesUseCase = SearchMedicinesUseCase(repository),
            getPatientUseCase = GetPatientUseCase(repository),
            getSelectedDiagnosisUseCase = GetSelectedDiagnosisUseCase(repository),
            getAllServicesUseCase = GetAllServicesUseCase(repository)
        )
    }

}