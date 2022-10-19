package com.example.tryaq.presentation.screens.drawer_screens

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
import com.example.tryaq.R
import com.example.tryaq.navigation.Screen
import com.example.tryaq.presentation.screens.logging.LoggingViewModel
import com.example.tryaq.ui.theme.primeColor
import com.example.tryaq.util.Constants.DATE_A
import com.example.tryaq.util.Constants.DAY_A
import com.example.tryaq.util.Constants.PATIENT_ID
import com.example.tryaq.util.Constants.PATIENT_PASSWORD
import com.example.tryaq.util.Constants.TIME_A
import com.example.tryaq.util.Constants.appointmentsIds
import com.example.tryaq.util.Constants.appointsIdAndName
import com.example.tryaq.util.Constants.newAppointmentDepartmentId
import com.example.tryaq.util.Constants.newAppointmentDepartmentName
import com.example.tryaq.util.Constants.newAppointmentDoctorImage
import com.example.tryaq.util.Constants.newAppointmentDoctorName
import com.example.tryaq.util.Constants.newAppointmentId
import com.example.tryaq.util.Constants.newAppointmentServiceId
import com.example.tryaq.util.Constants.newAppointmentServiceName
import com.example.tryaq.util.Constants.servicesIds

@Composable
fun NavigationDrawer(navController: NavHostController, loggingViewModel: LoggingViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .background(MaterialTheme.colors.primeColor)
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        NavigationItem(
            resId = R.drawable.ic_outline_person_24,
            text = "حسابي",
            itemClicked = {navController.popBackStack()
                navController.navigate(Screen.MyAccount.route)}
        )
        NavigationItem(
            resId = R.drawable.ic_my_appointments,
            text = "حجوزاتي",
            itemClicked = {navController.popBackStack()
                navController.navigate(Screen.MyAppointments.route)}
        )
        NavigationItem(
            resId = R.drawable.ic_pharm,
            text = "الأدوية",
            itemClicked = {navController.popBackStack()
                navController.navigate(Screen.Medicines.route)}
        )
        NavigationItem(
            resId = R.drawable.ic_support,
            text = "تواصل معنا",
            itemClicked = {navController.popBackStack()
                navController.navigate(Screen.ContactUs.route)}
        )
        NavigationItem(
            resId = R.drawable.ic_record,
            text = "السجل الطبي",
            itemClicked = {
                navController.popBackStack()
                navController.navigate(Screen.History.route)}
        )
        NavigationItem(
            resId = R.drawable.ic_lock_for,
            text = "نظرة مستقبلية",
            itemClicked = {
                navController.popBackStack()
                navController.navigate(Screen.LookingFor.route)}
        )
        NavigationItem(
            resId = R.drawable.ic_pre,
            text = "أهمية الدائرة الطبية",
            itemClicked = {
                navController.popBackStack()
                navController.navigate(Screen.Importance.route)}
        )
        Spacer(modifier = Modifier.padding(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 50.dp)
                .height(56.dp)
                .clickable {
                    servicesIds.clear()
                    appointmentsIds.clear()
                    appointsIdAndName.clear()

                     newAppointmentId = 0
                     newAppointmentDoctorName= ""
                     newAppointmentDepartmentName= ""
                     newAppointmentDoctorImage= ""
                     newAppointmentServiceName= ""
                     newAppointmentServiceId= 0
                     newAppointmentDepartmentId= 0
                     DAY_A = ""
                     DATE_A =""
                     TIME_A = ""
                    PATIENT_ID = -1
                    PATIENT_PASSWORD = " "
                    navController.popBackStack()
                    loggingViewModel.saveRememberState(isChecked = false)
                    loggingViewModel.savePatientData(id = -1, name = "", image = "")
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
