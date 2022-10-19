package com.example.dr_tryaq.presentation.screens.drawer_screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dr_tryaq.R
import com.example.dr_tryaq.navigation.Screen
import com.example.dr_tryaq.presentation.screens.logging.LoggingViewModel
import com.example.dr_tryaq.presentation.screens.splash.SplashViewModel
import com.example.dr_tryaq.ui.theme.primeColor
import com.example.dr_tryaq.util.Constants.DAY_D
import com.example.dr_tryaq.util.Constants.DOCTOR_ID
import com.example.dr_tryaq.util.Constants.DOCTOR_PASSWORD
import com.example.dr_tryaq.util.Constants.D_ANALYSIS_REQ
import com.example.dr_tryaq.util.Constants.D_ANALYSIS_RESULT
import com.example.dr_tryaq.util.Constants.D_APPOINT_ID
import com.example.dr_tryaq.util.Constants.D_DEPARTMENT_ID
import com.example.dr_tryaq.util.Constants.D_DOCTOR_IMAGE
import com.example.dr_tryaq.util.Constants.D_DOCTOR_NAME
import com.example.dr_tryaq.util.Constants.D_IS_ANALYSIS
import com.example.dr_tryaq.util.Constants.D_MEDICINE_REQ
import com.example.dr_tryaq.util.Constants.D_NEW_A_DAY
import com.example.dr_tryaq.util.Constants.D_NOTE
import com.example.dr_tryaq.util.Constants.D_PATIENT_ID
import com.example.dr_tryaq.util.Constants.D_PATIENT_IMAGE
import com.example.dr_tryaq.util.Constants.D_PATIENT_NAME
import com.example.dr_tryaq.util.Constants.D_SERVICE_ID
import com.example.dr_tryaq.util.Constants.D_SERVICE_IMAGE
import com.example.dr_tryaq.util.Constants.D_SERVICE_NAME
import com.example.dr_tryaq.util.Constants.MONTH_A
import com.example.dr_tryaq.util.Constants.New_Service_Name
import com.example.dr_tryaq.util.Constants.New_Service_Price
import com.example.dr_tryaq.util.Constants.SERVICE_ID
import com.example.dr_tryaq.util.Constants.TIME_D

@Composable
fun NavigationDrawer(
    navController: NavHostController,
    loggingViewModel: LoggingViewModel = hiltViewModel(),
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val doctorId by splashViewModel.doctorId.collectAsState()
    val departmentId by splashViewModel.departmentId.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .background(MaterialTheme.colors.primeColor)
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        NavigationItem(
            resId = R.drawable.ic_outline_person_24,
            text = "حسابي",
            itemClicked = {
                navController.popBackStack()
                navController.navigate(Screen.MyAccount.route)
            }
        )
        NavigationItem(
            resId = R.drawable.ic_my_appointments,
            text = "الخدمات",
            itemClicked = {
                navController.popBackStack()
                navController.navigate(Screen.Services.passDepartmentId(departmentId = departmentId))
            }
        )
        NavigationItem(
            resId = R.drawable.ic_record,
            text = "سجل المرضى",
            itemClicked = {
                navController.popBackStack()
                navController.navigate(Screen.Results.passDoctorId(doctorId = doctorId))
            }
        )
        NavigationItem(
            resId = R.drawable.ic_pharm,
            text = "الأدوية",
            itemClicked = {
                navController.popBackStack()
                navController.navigate(Screen.Medicines.route)
            }
        )
        NavigationItem(
            resId = R.drawable.ic_support,
            text = "تواصل معنا",
            itemClicked = {
                navController.popBackStack()
                navController.navigate(Screen.ContactUs.route)
            }
        )
        NavigationItem(
            resId = R.drawable.ic_lock_for,
            text = "نظرة مستقبلية",
            itemClicked = {
                navController.popBackStack()
                navController.navigate(Screen.LookingFor.route)
            }
        )
        NavigationItem(
            resId = R.drawable.ic_pre,
            text = "أهمية الدائرة الطبية",
            itemClicked = {
                navController.popBackStack()
                navController.navigate(Screen.Importance.route)
            }
        )
        Spacer(modifier = Modifier.padding(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 50.dp)
                .height(56.dp)
                .clickable {
                    SERVICE_ID = 0
                    DOCTOR_ID = 0
                    DOCTOR_PASSWORD = ""


                    New_Service_Name = ""
                    New_Service_Price = ""


                    D_APPOINT_ID = 0
                    DAY_D = ""
                    TIME_D = ""
                    D_ANALYSIS_REQ = ""
                    D_MEDICINE_REQ = ""
                    D_SERVICE_NAME = ""
                    D_DOCTOR_NAME = ""
                    D_PATIENT_NAME = ""
                    D_PATIENT_IMAGE = ""
                    D_PATIENT_ID = 0
                    D_SERVICE_ID = 0
                    D_SERVICE_IMAGE = ""
                    D_NOTE = ""
                    D_ANALYSIS_RESULT = ""
                    D_DOCTOR_IMAGE = ""
                    D_IS_ANALYSIS = 0
                    D_NEW_A_DAY = 0
                    D_DEPARTMENT_ID = 0

                    MONTH_A = ""

                    navController.popBackStack()
                    loggingViewModel.saveRememberState(isChecked = false)
                    loggingViewModel.saveDoctorData(id = -1, name = "", image = "")
                    navController.navigate(Screen.Login.route)
                },
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_log_out),
                contentDescription = "Logout",
                colorFilter = ColorFilter.tint(Color.White)
            )
            Text(
                text = "تسجيل الخروج",
                color = Color.White,
                fontSize = 17.sp
            )
        }
    }
}

@Composable
fun NavigationItem(
    resId: Int,
    text: String,
    itemClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(start = 28.dp)
            .height(48.dp)
            .clickable { itemClicked() }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = resId),
                contentDescription = "Item Image",
                modifier = Modifier.size(28.dp)
            )
            Text(
                text = text,
                color = Color.White,
                fontSize = 18.sp
            )
        }

//        Box(
//            modifier = Modifier
//                .padding(start = 35.dp, top = 6.dp, bottom = 6.dp)
//                .size(120.dp, 0.5.dp)
//                .background(Color.Gray)
//        )
    }
}
