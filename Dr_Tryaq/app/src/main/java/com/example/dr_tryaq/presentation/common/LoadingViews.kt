package com.example.dr_tryaq.presentation.common

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.dr_tryaq.R
import com.example.dr_tryaq.navigation.Screen
import com.example.dr_tryaq.presentation.screens.bottom_nav_screens.home.HomeViewModel
import com.example.dr_tryaq.presentation.screens.bottom_nav_screens.new_diagnosis.NewDiagnosisViewModel
import com.example.dr_tryaq.presentation.screens.logging.LoggingViewModel
import com.example.dr_tryaq.presentation.screens.new_service.NewServiceViewModel
import com.example.dr_tryaq.presentation.screens.splash.SplashViewModel
import com.example.dr_tryaq.ui.theme.StableWhite
import com.example.dr_tryaq.ui.theme.primeColor
import com.example.dr_tryaq.ui.theme.secondColor
import com.example.dr_tryaq.ui.theme.topAppBarC
import com.example.dr_tryaq.util.Constants.DAY_D
import com.example.dr_tryaq.util.Constants.MONTH_A
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
import com.example.dr_tryaq.util.Constants.New_Service_Name
import com.example.dr_tryaq.util.Constants.New_Service_Price
import com.example.dr_tryaq.util.Constants.SERVICE_ID
import com.example.dr_tryaq.util.Constants.TIME_D
import com.example.dr_tryaq.util.handleBaseResponseResult
import com.example.dr_tryaq.util.handleDoctorsResult
import com.example.dr_tryaq.util.hash
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import java.time.LocalDate


@ExperimentalCoilApi
@Composable
fun LoadingViews (navController: NavHostController, loadingViewModel: LoadingViewModel = hiltViewModel()) {
    val appointmentId = loadingViewModel.appointmentId!!
        CancelAppointmentLoading(appointmentId = appointmentId, navController = navController)
}

@ExperimentalCoilApi
@Composable
fun LoadingAddAppointment (homeViewModel: HomeViewModel = hiltViewModel(), navController: NavHostController) {
    AddAppointmentLoading(
        navController = navController,
        homeViewModel = homeViewModel
    )
}

@Composable
fun LoadingLogin (navController: NavHostController) {
    LoginLoading(navController = navController)
}

@ExperimentalCoilApi
@Composable
fun LoadingChangeServiceState (navController: NavHostController) {
    ChangeServiceStateLoading(navController = navController)
}

@ExperimentalCoilApi
@Composable
fun LoadingNewService (splashViewModel:SplashViewModel = hiltViewModel(),newServiceViewModel: NewServiceViewModel = hiltViewModel(), navController: NavHostController) {
    val departmentId by splashViewModel.departmentId.collectAsState()
    NewServiceLoading(navController = navController, newServiceViewModel = newServiceViewModel, departmentId = departmentId)
}

@ExperimentalCoilApi
@Composable
fun LoadingNewDiagnosis (
    splashViewModel:SplashViewModel = hiltViewModel(),
    newDiagnosisViewModel: NewDiagnosisViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val doctorName by splashViewModel.doctorName.collectAsState()
    val doctorId by splashViewModel.doctorId.collectAsState()
    NewDiagnosisLoading(
        homeViewModel = homeViewModel,
        navController = navController,
        newDiagnosisViewModel = newDiagnosisViewModel,
        doctorId = doctorId,
        doctorName = doctorName
    )
}

@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalCoilApi
@Composable
fun AddAppointmentLoading(homeViewModel: HomeViewModel, navController: NavHostController) {
    val response = homeViewModel.addAppointment(
        id = "${LocalDate.now().monthValue}${LocalDate.now().dayOfMonth + D_NEW_A_DAY}04${D_SERVICE_ID}".toInt() ,
        day = LocalDate.now().plusDays(D_NEW_A_DAY.toLong()).dayOfWeek.toString(),
        date = (LocalDate.now().dayOfMonth + D_NEW_A_DAY).toString(),
        time = "9:30-10:00",
        month = MONTH_A,
        doctorName= D_DOCTOR_NAME,
        doctorImage= D_DOCTOR_IMAGE,
        serviceName= D_SERVICE_NAME,
        patientId= D_PATIENT_ID,
        patientName = D_PATIENT_NAME,
        patientImage = D_PATIENT_IMAGE,
        serviceId=D_SERVICE_ID,
        departmentId=D_DEPARTMENT_ID
    ).collectAsLazyPagingItems()
    val painter = rememberImagePainter(data = R.drawable.success_add) {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.BottomNavigationWithSwipeScreen.route)
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp),
                        color = MaterialTheme.colors.secondColor,
                        shape = RoundedCornerShape(10.dp),
                        elevation = 4.dp
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(4.dp),
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.search_icon),
                            tint = Color.Red
                        )
                    }

                }
            },
                title = {},
                backgroundColor = MaterialTheme.colors.topAppBarC
            )
        }
    ) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val result = handleBaseResponseResult(baseResponse = response)

        for (resp in response.itemSnapshotList){
            if (result && resp!!.success!!){
                Spacer(modifier = Modifier.size(64.dp))
                Text(text = "تم حجز الموعد", fontSize = 18.sp)
                Spacer(modifier = Modifier.size(64.dp))

                Image(modifier = Modifier.size(300.dp), painter = painter, contentDescription = "")
                Spacer(modifier = Modifier.size(64.dp))
                Button(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.BottomNavigationWithSwipeScreen.route)
                              } ,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(48.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primeColor)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        textAlign = TextAlign.Center,
                        text = "حسناً",
                        style = MaterialTheme.typography.h6,
                        color = StableWhite,
                        maxLines = 1
                    )
                }
            }else
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    textAlign = TextAlign.Center,
                    text = "جاري العمل...",
                    style = MaterialTheme.typography.h6,
                    maxLines = 1
                )
        }


    }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalCoilApi
@Composable
fun NewServiceLoading(departmentId: Int ,newServiceViewModel: NewServiceViewModel, navController: NavHostController) {
    val response = newServiceViewModel.newService(
        departmentId = departmentId,
        name = New_Service_Name,
        price = New_Service_Price
    ).collectAsLazyPagingItems()

    val painter = rememberImagePainter(data = R.drawable.success_add) {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp),
                        color = MaterialTheme.colors.secondColor,
                        shape = RoundedCornerShape(10.dp),
                        elevation = 4.dp
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(4.dp),
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.search_icon),
                            tint = Color.Red
                        )
                    }

                }
            },
                title = {},
                backgroundColor = MaterialTheme.colors.topAppBarC
            )
        }
    ) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val result = handleBaseResponseResult(baseResponse = response)

        for (resp in response.itemSnapshotList){
            if (result && resp!!.success!!){
                Spacer(modifier = Modifier.size(64.dp))
                Text(text = "تمت إضافة الخدمة", fontSize = 18.sp)
                Spacer(modifier = Modifier.size(64.dp))

                Image(modifier = Modifier.size(300.dp), painter = painter, contentDescription = "")
                Spacer(modifier = Modifier.size(64.dp))
                Button(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.BottomNavigationWithSwipeScreen.route)
                              } ,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(48.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primeColor)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        textAlign = TextAlign.Center,
                        text = "حسناً",
                        style = MaterialTheme.typography.h6,
                        color = StableWhite,
                        maxLines = 1
                    )
                }
            }else
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    textAlign = TextAlign.Center,
                    text = "جاري العمل...",
                    style = MaterialTheme.typography.h6,
                    maxLines = 1
                )
        }


    }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalCoilApi
@Composable
fun NewDiagnosisLoading(
    homeViewModel: HomeViewModel,
    newDiagnosisViewModel: NewDiagnosisViewModel,
    navController: NavHostController,
    doctorId: Int,
    doctorName: String,
) {
    val pros1 = remember { mutableStateOf(false) }
    val pros2 = remember { mutableStateOf(false) }
    val pros3 = remember { mutableStateOf(false) }
    Log.e("Diagnosis Data :",
        "$D_APPOINT_ID:$doctorName:$D_NOTE:$D_SERVICE_ID:$D_SERVICE_IMAGE:$D_NOTE:$D_ANALYSIS_RESULT:$D_IS_ANALYSIS:$D_APPOINT_ID:$DAY_D:$TIME_D:$D_ANALYSIS_REQ:$D_MEDICINE_REQ:$D_SERVICE_NAME:$D_PATIENT_NAME:$D_PATIENT_IMAGE")
    val response = newDiagnosisViewModel.newDiagnosis(
        doctorId = doctorId,
        doctorName = doctorName,
        appointmentId = D_APPOINT_ID,
        date = DAY_D,
        time = TIME_D,
        analysisRequired = D_ANALYSIS_REQ,
        medicineRequired = D_MEDICINE_REQ,
        serviceName = D_SERVICE_NAME,
        patientName = D_PATIENT_NAME,
        patientImage = D_PATIENT_IMAGE,
        patientId = D_PATIENT_ID,
        serviceId = D_SERVICE_ID,
        serviceImage = D_SERVICE_IMAGE,
        note = D_NOTE,
        analyzesResult = D_ANALYSIS_RESULT,
        isAnalysis = D_IS_ANALYSIS
    ).collectAsLazyPagingItems()

    val response2 = homeViewModel.addAppointment(
        id = "${LocalDate.now().monthValue}${LocalDate.now().dayOfMonth + D_NEW_A_DAY}04${D_SERVICE_ID}".toInt() ,
        day = LocalDate.now().plusDays(D_NEW_A_DAY.toLong()).dayOfWeek.toString(),
        date = (LocalDate.now().dayOfMonth + D_NEW_A_DAY).toString(),
        time = "9:30-10:00",
        month = MONTH_A,
        doctorName= D_DOCTOR_NAME,
        doctorImage= D_DOCTOR_IMAGE,
        serviceName= D_SERVICE_NAME,
        patientId= D_PATIENT_ID,
        patientName = D_PATIENT_NAME,
        patientImage = D_PATIENT_IMAGE,
        serviceId=D_SERVICE_ID,
        departmentId=D_DEPARTMENT_ID
    ).collectAsLazyPagingItems()

    val response3 = homeViewModel.deleteAppointment(appointmentId = D_APPOINT_ID)
        .collectAsLazyPagingItems()

    val painter = rememberImagePainter(data = R.drawable.success_add) {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp),
                        color = MaterialTheme.colors.secondColor,
                        shape = RoundedCornerShape(10.dp),
                        elevation = 4.dp
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(4.dp),
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.search_icon),
                            tint = Color.Red
                        )
                    }

                }
            },
                title = {},
                backgroundColor = MaterialTheme.colors.topAppBarC
            )
        }
    ) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val result = handleBaseResponseResult(baseResponse = response)
        val result2 = handleBaseResponseResult(baseResponse = response2)
        val result3 = handleBaseResponseResult(baseResponse = response3)

        for (resp in response.itemSnapshotList) {
            pros1.value = result && resp!!.success!!
        }
        for (resp in response2.itemSnapshotList) {
            pros2.value = result2 && resp!!.success!!
        }
        for (resp in response3.itemSnapshotList) {
            pros3.value = result3 && resp!!.success!!
        }
        if(pros1.value && pros2.value && pros3.value){
                Spacer(modifier = Modifier.size(64.dp))
                Text(text = "تمت إضافة الخدمة", fontSize = 18.sp)
                Spacer(modifier = Modifier.size(64.dp))

                Image(modifier = Modifier.size(300.dp), painter = painter, contentDescription = "")
                Spacer(modifier = Modifier.size(64.dp))
                Button(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.BottomNavigationWithSwipeScreen.route)
                              } ,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(48.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primeColor)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        textAlign = TextAlign.Center,
                        text = "حسناً",
                        style = MaterialTheme.typography.h6,
                        color = StableWhite,
                        maxLines = 1
                    )
                }
            }else
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    textAlign = TextAlign.Center,
                    text = "جاري العمل...",
                    style = MaterialTheme.typography.h6,
                    maxLines = 1
                )
        }

    }
}

@SuppressLint("NewApi")
@Composable
fun LoginLoading(loggingViewModel: LoggingViewModel = hiltViewModel(), navController: NavHostController) {
    FirebaseMessaging.getInstance().token
        .addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e("Fetching", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result

            Log.e("Fetching  token:", token)
            // fSaqtQ_zSYysDie_aOYy-c:APA91bF2G6D8BY3ep8kQqPoAzET955UdgK_WnU-T46nwx7HeBoI8Powpz2_6iVFBpCH6mryEp_mRjQpKd6TDcm2ZziWf5772Q0IljOfQhkvNM8TLSdD4RcOR3NvrI2nln7fqjnX803FN

        })

    val loginResult = loggingViewModel.login(doctorId = DOCTOR_ID, password = DOCTOR_PASSWORD).collectAsLazyPagingItems()
    var message by remember { mutableStateOf("جاري التحقق من البيانات") }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.Login.route)
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp),
                        color = MaterialTheme.colors.secondColor,
                        shape = RoundedCornerShape(10.dp),
                        elevation = 4.dp
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(4.dp),
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = stringResource(R.string.search_icon),
                            tint = MaterialTheme.colors.primeColor
                        )
                    }

                }
            },
                title = {},
                backgroundColor = MaterialTheme.colors.topAppBarC
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            items(
                items = loginResult,
                key = { doctor ->
                    doctor.id
                }
                // 33712e16f351657fffe7a29b8264bef7384a8167
            ) { doctor ->
                val result = handleDoctorsResult(doctor = loginResult)
                doctor?.let {
                    if (
                        result &&
                        doctor.id == DOCTOR_ID &&
                        doctor.password == hash(DOCTOR_PASSWORD)
                    ) {
                        navController.popBackStack()
                        navController.navigate(Screen.BottomNavigationWithSwipeScreen.route)
                        loggingViewModel.saveDoctorData(
                            id = doctor.id,
                            name = doctor.name,
                            image = doctor.image
                        )
                        loggingViewModel.saveDoctorDepartment(department = doctor.departmentId)
                        message = "مرحباً " + doctor.name
                    }
                }
                    if (!result) {
                        message = "خطأ في البيانات المدخلة"
                    }
            }
            item {
                Text(text = message)
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CancelAppointmentLoading(
    homeViewModel: HomeViewModel = hiltViewModel(),
    appointmentId: Int,
    navController: NavHostController
) {
    val response = homeViewModel.deleteAppointment(appointmentId = appointmentId)
        .collectAsLazyPagingItems()

    val painter = rememberImagePainter(data = R.drawable.success_cancel) {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp),
                        color = MaterialTheme.colors.secondColor,
                        shape = RoundedCornerShape(10.dp),
                        elevation = 4.dp
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(4.dp),
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.search_icon),
                            tint = Color.Red
                        )
                    }

                }
            },
                title = {},
                backgroundColor = MaterialTheme.colors.topAppBarC
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            val result = handleBaseResponseResult(baseResponse = response)

                if (result) {

                    Spacer(modifier = Modifier.size(64.dp))
                    Text(text = "تم الغاء الموعد", fontSize = 18.sp)
                    Spacer(modifier = Modifier.size(64.dp))

                    Image(
                        modifier = Modifier.size(300.dp),
                        painter = painter,
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.size(64.dp))
                    Button(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(48.dp),
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primeColor)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxSize(),
                            textAlign = TextAlign.Center,
                            text = "حسناً",
                            style = MaterialTheme.typography.h6,
                            color = StableWhite,
                            maxLines = 1
                        )
                    }
                }else
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        textAlign = TextAlign.Center,
                        text = "جاري العمل...",
                        style = MaterialTheme.typography.h6,
                        maxLines = 1
                    )

        }
    }
}

@ExperimentalCoilApi
@Composable
fun ChangeServiceStateLoading(
    newServiceViewModel: NewServiceViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val response = newServiceViewModel.changeServiceState(serviceId = SERVICE_ID)
        .collectAsLazyPagingItems()

    val painter = rememberImagePainter(data = R.drawable.success_add) {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp),
                        color = MaterialTheme.colors.secondColor,
                        shape = RoundedCornerShape(10.dp),
                        elevation = 4.dp
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(4.dp),
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.search_icon),
                            tint = Color.Red
                        )
                    }

                }
            },
                title = {},
                backgroundColor = MaterialTheme.colors.topAppBarC
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            val result = handleBaseResponseResult(baseResponse = response)

                if (result) {

                    Spacer(modifier = Modifier.size(64.dp))
                    Text(text = "تم تغيير حالة الخدمة", fontSize = 18.sp)
                    Spacer(modifier = Modifier.size(64.dp))

                    Image(
                        modifier = Modifier.size(300.dp),
                        painter = painter,
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.size(64.dp))
                    Button(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(48.dp),
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primeColor)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxSize(),
                            textAlign = TextAlign.Center,
                            text = "حسناً",
                            style = MaterialTheme.typography.h6,
                            color = StableWhite,
                            maxLines = 1
                        )
                    }
                }else
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        textAlign = TextAlign.Center,
                        text = "جاري العمل...",
                        style = MaterialTheme.typography.h6,
                        maxLines = 1
                    )

        }
    }
}