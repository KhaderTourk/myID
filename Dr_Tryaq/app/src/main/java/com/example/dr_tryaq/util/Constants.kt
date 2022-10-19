package com.example.dr_tryaq.util

import android.annotation.SuppressLint
import java.time.LocalDate

object Constants {

    const val BASE_URL = "https://blooming-mountain-28049.herokuapp.com"
    const val DETAILS_DEPARTMENT_KEY = "departmentId"
    const val CANCEL_APPOINTMENT_KEY = "appointmentId"
    const val DETAILS_DIAGNOSIS_KEY = "diagnosisId"
    const val MY_PATIENTS_KEY = "doctorId"

    // DATABASE NAME
    const val TRYAQ_DATABASE = "dr_tryaq_database"

    // Ads table
    const val ADS_DATABASE_TABLE = "dr_ad_table"
    const val ADS_REMOTE_KEY_DATABASE_TABLE = "dr_ad_remote_key_table"

    // Appointments table
    const val APPOINTMENTS_DATABASE_TABLE = "dr_appointments_table"
    const val APPOINTMENTS_REMOTE_KEY_DATABASE_TABLE = "dr_appointments_remote_key_table"

    // Diagnosis table
    const val DIAGNOSIS_DATABASE_TABLE = "dr_diagnosis_table"
    const val DIAGNOSIS_REMOTE_KEY_DATABASE_TABLE = "dr_diagnosis_remote_key_table"

    // Medicine table
    const val MEDICINES_DATABASE_TABLE = "dr_medicine_table"
    const val MEDICINES_REMOTE_KEY_DATABASE_TABLE = "dr_medicine_remote_key_table"

    // Patient table
    const val PATIENT_DATABASE_TABLE = "dr_patient_table"
    const val PATIENT_REMOTE_KEY_DATABASE_TABLE = "dr_patient_remote_key_table"

    // Doctor table
    const val DOCTOR_DATABASE_TABLE = "dr_doctor_table"
    const val DOCTOR_REMOTE_KEY_DATABASE_TABLE = "dr_doctor_remote_key_table"

    // Service table
    const val SERVICES_DATABASE_TABLE = "dr_service_table"
    const val SERVICES_REMOTE_KEY_DATABASE_TABLE = "dr_service_remote_key_table"

    // Department table
    const val DEPARTMENT_DATABASE_TABLE = "dr_department_table"
    const val DEPARTMENT_REMOTE_KEY_DATABASE_TABLE = "dr_department_remote_key_table"

    const val PREFERENCES_NAME = "dr_tryaq_preferences"
    const val PREFERENCES_KEY = "dr_on_boarding_completed"
    const val REMEMBER_ME_KEY = "dr_remember_me_completed"
    const val DOCTOR_ID_KEY = "dr_patient_id_completed"
    const val DOCTOR_DEPARTMENT_KEY = "dr_patient_department_completed"
    const val DOCTOR_NAME_KEY = "dr_patient_name_completed"
    const val DOCTOR_IMAGE_KEY = "dr_patient_image_completed"

    const val ON_BOARDING_PAGE_COUNT = 3
    const val LAST_ON_BOARDING_PAGE = 2

    const val ITEMS_PER_PAGE = 50

    const val USER_TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ0cnlhcS1hcHAiLCJpc3MiOiJ0cnlhcS1hcHAiLCJpZCI6NTU1NTU1NTU1fQ.ek0dQHZIGlpIZ8i_IHrFjTeRdhi6TZJ2lv8iz-Sr1vI"

    var SERVICE_ID = 0
    var DOCTOR_ID = 0
    var DOCTOR_PASSWORD = ""


    var New_Service_Name= ""
    var New_Service_Price= ""


    var D_APPOINT_ID = 0
    var DAY_D = ""
    var TIME_D = ""
    var D_ANALYSIS_REQ = ""
    var D_MEDICINE_REQ = ""
    var D_SERVICE_NAME = ""
    var D_DOCTOR_NAME = ""
    var D_PATIENT_NAME = ""
    var D_PATIENT_IMAGE = ""
    var D_PATIENT_ID = 0
    var D_SERVICE_ID = 0
    var D_SERVICE_IMAGE = ""
    var D_NOTE = ""
    var D_ANALYSIS_RESULT = ""
    var D_DOCTOR_IMAGE = ""
    var D_IS_ANALYSIS = 0
    var D_NEW_A_DAY = 0
    var D_DEPARTMENT_ID = 0


    @SuppressLint("NewApi")
    var MONTH_A = LocalDate.now().month.toString().substring(0,3)
}