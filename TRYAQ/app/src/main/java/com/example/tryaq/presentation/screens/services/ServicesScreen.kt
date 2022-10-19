package com.example.tryaq.presentation.screens.services

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import coil.annotation.ExperimentalCoilApi
import com.example.tryaq.R
import com.example.tryaq.domain.model.models.Service
import com.example.tryaq.navigation.Screen
import com.example.tryaq.presentation.components.home_flib_card.*
import com.example.tryaq.presentation.components.service_flib_card.ServiceBackSide
import com.example.tryaq.presentation.components.service_flib_card.ServiceCard
import com.example.tryaq.presentation.components.service_flib_card.ServiceFrontSide
import com.example.tryaq.ui.theme.*
import com.example.tryaq.util.Constants.appointmentsIds
import com.example.tryaq.util.Constants.servicesIds
import com.example.tryaq.util.Constants.appointsIdAndName
import com.example.tryaq.util.Constants.newAppointmentDepartmentName
import com.example.tryaq.util.handleServicesResult
import com.google.accompanist.flowlayout.FlowRow

@ExperimentalCoilApi
@Composable
fun ServicesScreen (
    navController: NavHostController,
    servicesViewModel: ServicesViewModel = hiltViewModel()
) {
    val departmentId = servicesViewModel.getDepartmentId()
    val services = servicesViewModel.getServices(departmentId = departmentId).collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.height(80.dp),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            servicesIds.clear()
                            appointmentsIds.clear()
                            appointsIdAndName.clear()
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
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = newAppointmentDepartmentName,
                            color = MaterialTheme.colors.primeColor
                        )
                    }

                },
                backgroundColor = MaterialTheme.colors.topAppBarC
            )
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 16.dp, 16.dp, 4.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text ="الخدمات",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        fontSize = 18.sp
                    )
                }
            }

            item {
                val result = handleServicesResult(services = services)
                if (result) {
                    FlowRow(modifier = Modifier.fillMaxWidth()) {
                        for (item in 0 until services.itemCount){
                            if (departmentId == 5 && appointmentsIds.contains(services[item]!!.id) )
                                FlippableServiceCard(
                                    service = services[item]!!,
                                    navController = navController
                                )
                            else if(servicesIds.contains(services[item]!!.id))
                                FlippableServiceCard(
                                    service = services[item]!!,
                                    navController = navController
                                )
                            else
                                ServiceCard(
                                    service = services[item]!!,
                                    navController = navController
                                )
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun FlippableServiceCard(service: Service,
                      navController: NavHostController) {

            val duration: Int by remember { mutableStateOf(400) }
            val flipOnTouchEnabled: Boolean by remember { mutableStateOf(true) }
            val flipEnabled: Boolean by remember { mutableStateOf(true) }
            val autoFlipEnabled: Boolean by remember { mutableStateOf(false) }
            val selectedAnimType: FlipAnimationType by remember {
                mutableStateOf(
                    FlipAnimationType.HORIZONTAL_ANTI_CLOCKWISE
                )
            }
            val flipController = rememberFlipController()

            Flippable(
                frontSide = { ServiceFrontSide(service = service) },
                backSide = { ServiceBackSide(service = service, navController = navController) },
                flipController = flipController,
                flipDurationMs = duration,
                flipOnTouch = flipOnTouchEnabled,
                flipEnabled = flipEnabled,
                autoFlip = autoFlipEnabled,
                autoFlipDurationMs = 2000,
                flipAnimationType = selectedAnimType
            )
}
