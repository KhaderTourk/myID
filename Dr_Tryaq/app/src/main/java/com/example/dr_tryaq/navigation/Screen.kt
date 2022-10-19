package com.example.dr_tryaq.navigation

import com.example.dr_tryaq.R

sealed class Screen(val route: String, val title: String, val icon:Int) {
    object Splash : Screen("splash_screen", "splash", R.drawable.ic_home)
    object Welcome : Screen("welcome_screen", "", R.drawable.ic_home)
    object Notifications : Screen("notifications_screen", "الإشعارات", R.drawable.ic_home)
    object MyAccount : Screen("my_account_screen", "حسابي", R.drawable.ic_outline_person_24)
    object Home : Screen("home_screen", "الرئيسية", R.drawable.ic_home)
    object BottomNavigationWithSwipeScreen : Screen("bottom_nav_screen", "", R.drawable.ic_home)
    object LoadingNewDiagnosis : Screen("loading_new_diagnosis_screen", "النتائج", R.drawable.ic_file_text)
    object NewDiagnosisScreen : Screen("new_diagnosis_screen", "النتائج", R.drawable.ic_file_text)
    object Login : Screen("login_screen", "النتائج", R.drawable.ic_file_text)
    object AddNewService : Screen("add_new_service_screen", "النتائج", R.drawable.ic_file_text)
    object LoadingNewService : Screen("login_new_service_screen", "النتائج", R.drawable.ic_file_text)

    object Results : Screen("result_screen/{doctorId}", "الخدمات", R.drawable.ic_file_text){
        fun passDoctorId(doctorId: Int): String {
            return "result_screen/$doctorId"
        }
    }
    object ResultDetail : Screen("result_detail_screen/{diagnosisId}", "الخدمات", R.drawable.ic_file_text){
        fun passDiagnosisId(diagnosisId: Int): String {
            return "result_detail_screen/$diagnosisId"
        }
    }
    object Services : Screen("services_screen/{departmentId}", "الخدمات", R.drawable.ic_file_text){
        fun passDepartmentId(departmentId: Int): String {
            return "services_screen/$departmentId"
        }
    }
    object Loading : Screen("loading_screen/{appointmentId}", "", R.drawable.ic_file_text){
        fun passAppointmentId(appointmentId: Int): String {
            return "loading_screen/$appointmentId"
        }
    }
    object LoadingAddAppointment : Screen("loading_add_appointment_screen", "", R.drawable.ic_file_text)
    object LoadingLogin : Screen("loading_login_screen", "", R.drawable.ic_file_text)
    object LoadingChangeServiceState : Screen("loading_change_service_state_screen", "", R.drawable.ic_file_text)

    object ContactUs : Screen("contact_us_screen", "", R.drawable.ic_file_text)
    object History : Screen("history_screen", "", R.drawable.ic_file_text)
    object Importance : Screen("importance_screen", "", R.drawable.ic_file_text)
    object LookingFor : Screen("looking_for_screen", "", R.drawable.ic_file_text)
    object Medicines : Screen("medicines_screen", "", R.drawable.ic_file_text)
    object MyAppointments : Screen("my_appointments_screen", "", R.drawable.ic_file_text)



    object Search : Screen("search_screen", "", R.drawable.ic_home)
}

