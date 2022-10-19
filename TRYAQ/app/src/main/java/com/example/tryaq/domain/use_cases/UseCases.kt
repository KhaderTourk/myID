package com.example.tryaq.domain.use_cases

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

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val saveRememberMeUseCase: SaveRememberMeUseCase,
    val readRememberMeUseCase: ReadRememberMeUseCase,
    val savePatientDataUseCase: SavePatientDataUseCase,
    val readPatientNameUseCase: ReadPatientNameUseCase,
    val readPatientIdUseCase: ReadPatientIdUseCase,
    val readePatientImageUseCase: ReadePatientImageUseCase,
    val getAllAdsUseCase: GetAllAdsUseCase,
    val getAllAppointmentsUseCase: GetAllAppointmentsUseCase,
    val getAllAppointmentsUseCase2: GetAllAppointmentsUseCase2,
    val deleteAppointmentUseCase: DeleteAppointmentUseCase,
    val addAppointmentUseCase: AddAppointmentUseCase,
    val signUpUseCase: SignUpUseCase,
    val getAllDepartmentsUseCase: GetAllDepartmentsUseCase,
    val getAllDiagnosisUseCase: GetAllDiagnosisUseCase,
    val getAllMedicinesUseCase: GetAllMedicinesUseCase,
    val searchMedicinesUseCase: SearchMedicinesUseCase,
    val getPatientUseCase: GetPatientUseCase,
    val getSelectedDiagnosisUseCase: GetSelectedDiagnosisUseCase,
    val getAllServicesUseCase: GetAllServicesUseCase
)