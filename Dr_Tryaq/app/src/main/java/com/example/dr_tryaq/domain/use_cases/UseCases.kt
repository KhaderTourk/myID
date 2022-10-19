package com.example.dr_tryaq.domain.use_cases

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

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val saveRememberMeUseCase: SaveRememberMeUseCase,
    val saveDoctorDepartmentUseCase: SaveDoctorDepartmentUseCase,
    val readDoctorDepartmentUseCase: ReadDoctorDepartmentUseCase,
    val readRememberMeUseCase: ReadRememberMeUseCase,
    val saveDoctorDataUseCase: SaveDoctorDataUseCase,
    val readDoctorIdUseCase: ReadDoctorIdUseCase,
    val readDoctorNameUseCase: ReadDoctorNameUseCase,
    val readeDoctorImageUseCase: ReadeDoctorImageUseCase,
    val getAllAdsUseCase: GetAllAdsUseCase,
    val getAllAppointmentsUseCase: GetAllAppointmentsUseCase,
    val deleteAppointmentUseCase: DeleteAppointmentUseCase,
    val addAppointmentUseCase: AddAppointmentUseCase,
    val newServiceUseCase: NewServiceUseCase,
    val getMyPatientsUseCase: GetMyPatientsUseCase,
    val newDiagnosisUseCase: NewDiagnosisUseCase,
    val changeServiceStateUseCase: ChangeServiceStateUseCase,
    val getAllDepartmentsUseCase: GetAllDepartmentsUseCase,
    val getAllDiagnosisUseCase: GetAllDiagnosisUseCase,
    val getAllMedicinesUseCase: GetAllMedicinesUseCase,
    val searchMedicinesUseCase: SearchMedicinesUseCase,
    val login: LoginUseCase,
    val getSelectedDiagnosisUseCase: GetSelectedDiagnosisUseCase,
    val getAllServicesUseCase: GetAllServicesUseCase
)