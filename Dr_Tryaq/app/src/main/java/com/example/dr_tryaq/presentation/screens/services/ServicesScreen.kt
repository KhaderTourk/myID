package com.example.dr_tryaq.presentation.screens.services

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.dr_tryaq.R
import com.example.dr_tryaq.domain.model.models.Service
import com.example.dr_tryaq.navigation.Screen
import com.example.dr_tryaq.presentation.components.home_flib_card.FlipAnimationType
import com.example.dr_tryaq.presentation.components.home_flib_card.Flippable
import com.example.dr_tryaq.presentation.components.home_flib_card.rememberFlipController
import com.example.dr_tryaq.presentation.components.service_flib_card.*
import com.example.dr_tryaq.ui.theme.primeColor
import com.example.dr_tryaq.ui.theme.secondColor
import com.example.dr_tryaq.ui.theme.topAppBarC
import com.example.dr_tryaq.util.handleServicesResult
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
                            text = "الخدمات",
                            color = MaterialTheme.colors.primeColor
                        )
                    }

                },
                backgroundColor = MaterialTheme.colors.topAppBarC
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddNewService.route) },
                backgroundColor = MaterialTheme.colors.primeColor
            ) {
                Icon(Icons.Filled.Add,"")
        }}
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                val result = handleServicesResult(services = services)
                if (result) {
                    FlowRow(modifier = Modifier.fillMaxWidth()) {
                        for (item in 0 until services.itemCount){
                            if (services[item]!!.status == 1)
                                FlippableActiveServiceCard(
                                    service = services[item]!!,
                                    navController = navController
                                )
                            else
                                FlippableDeActiveServiceCard(
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
fun FlippableActiveServiceCard(service: Service,
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
                frontSide = { ActiveServiceFrontSide(service = service, navController = navController) },
                backSide = { ActiveServiceBackSide(service = service, navController = navController) },
                flipController = flipController,
                flipDurationMs = duration,
                flipOnTouch = flipOnTouchEnabled,
                flipEnabled = flipEnabled,
                autoFlip = autoFlipEnabled,
                autoFlipDurationMs = 2000,
                flipAnimationType = selectedAnimType
            )
}

@ExperimentalCoilApi
@Composable
fun FlippableDeActiveServiceCard(service: Service,
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
        frontSide = { DeActiveServiceFrontSide(service = service, navController = navController) },
        backSide = { DeActiveServiceBackSide(service = service, navController = navController) },
        flipController = flipController,
        flipDurationMs = duration,
        flipOnTouch = flipOnTouchEnabled,
        flipEnabled = flipEnabled,
        autoFlip = autoFlipEnabled,
        autoFlipDurationMs = 2000,
        flipAnimationType = selectedAnimType
    )
}
