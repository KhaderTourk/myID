package com.example.tryaq.presentation.common

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
import com.example.tryaq.R
import com.example.tryaq.navigation.Screen
import com.example.tryaq.presentation.screens.bottom_nav_screens.home.HomeViewModel
import com.example.tryaq.presentation.screens.logging.LoggingViewModel
import com.example.tryaq.presentation.screens.splash.SplashViewModel
import com.example.tryaq.ui.theme.*
import com.example.tryaq.util.*
import com.example.tryaq.util.Constants.DATE_A
import com.example.tryaq.util.Constants.DAY_A
import com.example.tryaq.util.Constants.MONTH_A
import com.example.tryaq.util.Constants.PATIENT_ID
import com.example.tryaq.util.Constants.PATIENT_PASSWORD
import com.example.tryaq.util.Constants.TIME_A
import com.example.tryaq.util.Constants.newAppointmentDepartmentId
import com.example.tryaq.util.Constants.newAppointmentDoctorImage
import com.example.tryaq.util.Constants.newAppointmentDoctorName
import com.example.tryaq.util.Constants.newAppointmentId
import com.example.tryaq.util.Constants.newAppointmentServiceId
import com.example.tryaq.util.Constants.newAppointmentServiceName
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging


@ExperimentalCoilApi
@Composable
fun LoadingViews (navController: NavHostController, loadingViewModel: LoadingViewModel = hiltViewModel()) {
    val appointmentId = loadingViewModel.appointmentId!!
        CancelAppointmentLoading(appointmentId = appointmentId, navController = navController)
}

@ExperimentalCoilApi
@Composable
fun LoadingAddAppointment (homeViewModel: HomeViewModel = hiltViewModel(),splashViewModel: SplashViewModel = hiltViewModel(),navController: NavHostController) {
    val patientId by splashViewModel.patientId.collectAsState()
    AddAppointmentLoading(navController = navController, homeViewModel = homeViewModel, patientId = patientId)
}

@ExperimentalCoilApi
@Composable
fun LoadingSignUp (navController: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()) {
    SignUpLoading(navController = navController, homeViewModel = homeViewModel)
}
@Composable
fun LoadingLogin (navController: NavHostController) {
    LoginLoading(navController = navController)
}

@ExperimentalCoilApi
@Composable
fun SignUpLoading(homeViewModel: HomeViewModel, navController: NavHostController) {
    val response = homeViewModel.signUp(
        patientId= PATIENT_ID,
        password = PATIENT_PASSWORD
    ).collectAsLazyPagingItems()

    val painter = rememberImagePainter(data = R.drawable.success_add) {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
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
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.search_icon),
                            tint = Color.Red
                        )
                    }

                }
            },
                title = {},
                backgroundColor = MaterialTheme.colors.topAppBarC)
        }
    ) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val result = handleBaseResponseResult(baseResponse = response)
        if (result)
Log.e("response count",response.itemCount.toString())

        for (resp in response.itemSnapshotList){
            if (result && resp!!.success!!){
                Spacer(modifier = Modifier.size(64.dp))
                Text(text = "تم التسجيل", fontSize = 18.sp)
                Spacer(modifier = Modifier.size(64.dp))

                Image(modifier = Modifier.size(300.dp), painter = painter, contentDescription = "")
                Spacer(modifier = Modifier.size(64.dp))
                Button(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.Login.route)
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
fun AddAppointmentLoading(homeViewModel: HomeViewModel, myAccountViewModel:SplashViewModel = hiltViewModel(), patientId: Int, navController: NavHostController) {
    val response = homeViewModel.addAppointment(
        id = newAppointmentId ,
        day = DAY_A,
        date = DATE_A,
        time = TIME_A,
        month = MONTH_A,
        doctorName=newAppointmentDoctorName,
        doctorImage=newAppointmentDoctorImage,
        serviceName=newAppointmentServiceName,
        patientId= patientId,
        patientName = myAccountViewModel.patientName.value,
        patientImage = myAccountViewModel.patientImage.value,
        serviceId=newAppointmentServiceId,
        departmentId=newAppointmentDepartmentId
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

    val loginResult = loggingViewModel.getLoginResult(patientId = PATIENT_ID, password = PATIENT_PASSWORD).collectAsLazyPagingItems()
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
            key = { patient ->
                patient.id
            }
        ) { patient ->
            val result = handlePatientsResult(patient = loginResult)
            patient?.let {
                if (
                    result &&
                    patient.id == PATIENT_ID &&
                    patient.password == hash(PATIENT_PASSWORD)
                ) {
                    navController.popBackStack()
                    navController.navigate(Screen.BottomNavigationWithSwipeScreen.route)
                    loggingViewModel.savePatientData(
                        id = patient.id,
                        name = patient.name,
                        image = patient.image
                    )
                    message = "مرحباً " + patient.name
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
                        Constants.servicesIds.clear()
                        Constants.appointsIdAndName.clear()
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
                            Constants.servicesIds.clear()
                            Constants.appointsIdAndName.clear()
                            navController.popBackStack()
                            navController.navigate(Screen.BottomNavigationWithSwipeScreen.route)
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