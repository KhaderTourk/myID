package com.example.tryaq.presentation.screens.drawer_screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.example.tryaq.R
import com.example.tryaq.navigation.Screen
import com.example.tryaq.presentation.screens.bottom_nav_screens.home.FlippableHomeCard
import com.example.tryaq.presentation.screens.bottom_nav_screens.home.HomeViewModel
import com.example.tryaq.presentation.screens.splash.SplashViewModel
import com.example.tryaq.ui.theme.*
import com.example.tryaq.util.handleAppointmentsResult
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun MyAppointmentsScreen (
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val patientId by splashViewModel.patientId.collectAsState()
    val appointments = homeViewModel.getAllAppointments(patientId).collectAsLazyPagingItems()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.height(80.dp),
                navigationIcon = {
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
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = stringResource(R.string.search_icon),
                                tint = MaterialTheme.colors.primeColor
                            )
                        }

                    }
                },
                title = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "حجوزاتي",
                            color = MaterialTheme.colors.primeColor
                        )
                    }

                },
                backgroundColor = MaterialTheme.colors.topAppBarC,
            )
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize().padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item{
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp, 16.dp, 16.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "المواعيد",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        fontSize = 18.sp,
                        color = MaterialTheme.colors.stableBlack
                    )
                }
            }
            items(
                items = appointments,
                key = { appointment ->
                    appointment.id
                }
            ) { appointment ->
                val result = handleAppointmentsResult(appointments = appointments)
                if (result || appointments.itemCount > 0) {
                    appointment?.let {
                        FlippableHomeCard(appointment = it, navController = navController)
                    }
                }
            }
        }
    }
}