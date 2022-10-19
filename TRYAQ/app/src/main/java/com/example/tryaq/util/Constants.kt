package com.example.tryaq.util

import android.annotation.SuppressLint
import java.time.LocalDate

object Constants {
    const val BASE_URL = "https://blooming-mountain-28049.herokuapp.com"
    const val DETAILS_ARGUMENT_KEY = "adId"
    const val DETAILS_DEPARTMENT_KEY = "departmentId"
    const val CANCEL_APPOINTMENT_KEY = "appointmentId"
    const val NEW_APPOINTMENT_KEY = "serviceId"
    const val DETAILS_DIAGNOSIS_KEY = "diagnosisId"

    // DATABASE NAME
    const val TRYAQ_DATABASE = "tryaq_database"

    // Ads table
    const val ADS_DATABASE_TABLE = "ad_table"
    const val ADS_REMOTE_KEY_DATABASE_TABLE = "ad_remote_key_table"

    // Appointments table
    const val APPOINTMENTS_DATABASE_TABLE = "appointments_table"
    const val APPOINTMENTS_REMOTE_KEY_DATABASE_TABLE = "appointments_remote_key_table"

    // All Appointments table
    const val APPOINTMENTS2_DATABASE_TABLE = "appointments2_table"
    const val APPOINTMENTS2_REMOTE_KEY_DATABASE_TABLE = "appointments2_remote_key_table"

    // Diagnosis table
    const val DIAGNOSIS_DATABASE_TABLE = "diagnosis_table"
    const val DIAGNOSIS_REMOTE_KEY_DATABASE_TABLE = "diagnosis_remote_key_table"

    // Medicine table
    const val MEDICINES_DATABASE_TABLE = "medicine_table"
    const val MEDICINES_REMOTE_KEY_DATABASE_TABLE = "medicine_remote_key_table"

    // Patient table
    const val PATIENT_DATABASE_TABLE = "patient_table"
    const val PATIENT_REMOTE_KEY_DATABASE_TABLE = "patient_remote_key_table"

    // Service table
    const val SERVICES_DATABASE_TABLE = "service_table"
    const val SERVICES_REMOTE_KEY_DATABASE_TABLE = "service_remote_key_table"

    // Department table
    const val DEPARTMENT_DATABASE_TABLE = "department_table"
    const val DEPARTMENT_REMOTE_KEY_DATABASE_TABLE = "department_remote_key_table"

    const val PREFERENCES_NAME = "tryaq_preferences"
    const val PREFERENCES_KEY = "on_boarding_completed"
        const val REMEMBER_ME_KEY = "remember_me_completed"
        const val PATIENT_ID_KEY = "patient_id_completed"
            const val PATIENT_NAME_KEY = "patient_name_completed"
        const val PATIENT_IMAGE_KEY = "patient_image_completed"

    const val ON_BOARDING_PAGE_COUNT = 3
    const val LAST_ON_BOARDING_PAGE = 2

    const val ITEMS_PER_PAGE = 50

    var servicesIds = ArrayList<Int>()
    var appointmentsIds = ArrayList<Int>()
    var appointsIdAndName =  ArrayList<String>()

    var newAppointmentId = 0
    var newAppointmentDoctorName= ""
    var newAppointmentDepartmentName= ""
    var newAppointmentDoctorImage= ""
    var newAppointmentServiceName= ""
    var newAppointmentServiceId= 0
    var newAppointmentDepartmentId= 0
    var DAY_A = ""
    var DATE_A =""
    var TIME_A = ""
    var PATIENT_ID = 0
    var PATIENT_PASSWORD = ""
    @SuppressLint("NewApi")
    var MONTH_A = LocalDate.now().month.toString().substring(0,3)

    const val USER_TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ0cnlhcS1hcHAiLCJpc3MiOiJ0cnlhcS1hcHAiLCJpZCI6NTU1NTU1NTU1fQ.ek0dQHZIGlpIZ8i_IHrFjTeRdhi6TZJ2lv8iz-Sr1vI"
    // Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ0cnlhcS1hcHAiLCJpc3MiOiJ0cnlhcS1hcHAiLCJpZCI6NTU1NTU1NTU1fQ.ek0dQHZIGlpIZ8i_IHrFjTeRdhi6TZJ2lv8iz-Sr1vI
}