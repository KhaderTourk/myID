package com.example.tryaq.navigation

import com.example.tryaq.R
import com.example.tryaq.domain.model.models.Service

sealed class Screen(val route: String, val title: String, val icon:Int) {
    object Splash : Screen("splash_screen", "splash", R.drawable.ic_logo)
    object Welcome : Screen("welcome_screen", "", R.drawable.ic_logo)
    object Notifications : Screen("notifications_screen", "الإشعارات", R.drawable.ic_logo)
    object MyAccount : Screen("my_account_screen", "حسابي", R.drawable.ic_outline_person_24)
    object Home : Screen("home_screen", "الرئيسية", R.drawable.ic_home)
    object BottomNavigationWithSwipeScreen : Screen("bottom_nav_screen", "", R.drawable.ic_logo)
    object Departments : Screen("department_screen", "الأقسام", R.drawable.ic_grid)
    object Results : Screen("results_screen", "النتائج", R.drawable.ic_file_text)
    object Login : Screen("login_screen", "النتائج", R.drawable.ic_file_text)
    object SignUp : Screen("sign_up_screen", "النتائج", R.drawable.ic_file_text)
    object LoadingSignUp : Screen("login_sign_up_screen", "النتائج", R.drawable.ic_file_text)

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
    object NewAppointment : Screen("new_appointment_screen/{serviceId}", "", R.drawable.ic_file_text){
        fun passServiceId(serviceId: Int): String {
            return "new_appointment_screen/$serviceId"
        }
    }
    object LoadingAddAppointment : Screen("loading_add_appointment_screen", "", R.drawable.ic_file_text)
    object LoadingLogin : Screen("loading_login_screen", "", R.drawable.ic_file_text)

    object ContactUs : Screen("contact_us_screen", "", R.drawable.ic_file_text)
    object History : Screen("history_screen", "", R.drawable.ic_file_text)
    object Importance : Screen("importance_screen", "", R.drawable.ic_file_text)
    object LookingFor : Screen("looking_for_screen", "", R.drawable.ic_file_text)
    object Medicines : Screen("medicines_screen", "", R.drawable.ic_file_text)
    object MyAppointments : Screen("my_appointments_screen", "", R.drawable.ic_file_text)

    object Details : Screen("details_screen/{heroId}", "", R.drawable.ic_logo) {
        fun passAdId(heroId: Int): String {
            return "details_screen/$heroId"
        }
    }

    object Search : Screen("search_screen", "", R.drawable.ic_logo)
}

