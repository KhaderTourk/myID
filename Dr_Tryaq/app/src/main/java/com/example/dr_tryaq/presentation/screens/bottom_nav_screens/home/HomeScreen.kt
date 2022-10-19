package com.example.dr_tryaq.presentation.screens.bottom_nav_screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.example.dr_tryaq.domain.model.models.Appointment
import com.example.dr_tryaq.presentation.components.AutoSlidingAds
import com.example.dr_tryaq.presentation.components.home_flib_card.*
import com.example.dr_tryaq.presentation.screens.splash.SplashViewModel
import com.example.dr_tryaq.util.handleHomeResult
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
// this for get data from Remote
    val allAds = homeViewModel.getAllAds.collectAsLazyPagingItems()
    val departments = homeViewModel.getAllDepartments.collectAsLazyPagingItems()
    // this for get data from data store
    val departmentId by splashViewModel.departmentId.collectAsState()
    val doctorId by splashViewModel.doctorId.collectAsState()

    val appointments = homeViewModel.getAllAppointments(departmentId).collectAsLazyPagingItems()
    homeViewModel.getAllDiagnosis(doctorId = doctorId).collectAsLazyPagingItems()

    val refreshResult = handleHomeResult(ads = allAds, departments = departments)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (!refreshResult) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 8.dp, 16.dp, 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Surface(
                        shape = RoundedCornerShape(size = 10.dp),
                        color = Color.LightGray,
                        border = BorderStroke(width = 0.5.dp, color = Color.Gray)
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            text = "جاري التحديث!!",
                            fontFamily = FontFamily.Serif,
                            fontSize = 14.sp
                        )
                    }

                }
            }
        }
        item {
            AutoSlidingAds(ads = allAds)
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp, 16.dp, 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "الحجوزات",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    fontSize = 18.sp
                )
            }
        }
        items(
            items = appointments,
            key = { appointment ->
                appointment.id
            }
        ) { appointment ->
            appointment?.let {
                FlippableHomeCard(appointment = it, navController = navController)
            }
        }
    }
}

@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun FlippableHomeCard(appointment: Appointment, navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val duration: Int by remember { mutableStateOf(400) }
            val flipOnTouchEnabled: Boolean by remember { mutableStateOf(true) }
            val flipEnabled: Boolean by remember { mutableStateOf(true) }
            val autoFlipEnabled: Boolean by remember { mutableStateOf(false) }
            val selectedAnimType: FlipAnimationType by remember {
                mutableStateOf(
                    FlipAnimationType.VERTICAL_ANTI_CLOCKWISE
                )
            }
            val flipController = rememberFlipController()

            Flippable(
                frontSide = { EnglishWordFrontSide(appointment = appointment) },
                backSide = {
                    EnglishWordBackSide(
                        appointment = appointment,
                        navController = navController
                    )
                },
                flipController = flipController,
                flipDurationMs = duration,
                flipOnTouch = flipOnTouchEnabled,
                flipEnabled = flipEnabled,
                autoFlip = autoFlipEnabled,
                autoFlipDurationMs = 2000,
                flipAnimationType = selectedAnimType
            )
        }
    }
}



