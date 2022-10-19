package com.example.dr_tryaq.presentation.screens.bottom_nav_screens.results

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.dr_tryaq.R
import com.example.dr_tryaq.navigation.Screen
import com.example.dr_tryaq.presentation.components.ResultCard
import com.example.dr_tryaq.presentation.screens.splash.SplashViewModel
import com.example.dr_tryaq.ui.theme.primeColor
import com.example.dr_tryaq.ui.theme.secondColor
import com.example.dr_tryaq.ui.theme.topAppBarC
import com.example.dr_tryaq.util.handleDiagnosisResult
import com.google.accompanist.flowlayout.FlowRow

@ExperimentalCoilApi
@Composable
fun ResultsScreen(
    navController: NavHostController,
    resultsViewModel: ResultsViewModel = hiltViewModel(),
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val doctorId by splashViewModel.doctorId.collectAsState()
    val diagnosis = resultsViewModel.getAllDiagnosis(doctorId = doctorId).collectAsLazyPagingItems()

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
                            text = "سجل المرضى",
                            color = MaterialTheme.colors.primeColor
                        )
                    }

                },
                backgroundColor = MaterialTheme.colors.topAppBarC)
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                val result = handleDiagnosisResult(diagnosis = diagnosis)
                if (result) {
                    FlowRow(modifier = Modifier.fillMaxWidth()) {
                        for (item in 0 until diagnosis.itemCount) {
                            ResultCard(
                                diagnosis = diagnosis[item]!!,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
//@ExperimentalCoilApi
//@Composable
//fun ResultsScreen(
//    navController: NavHostController,
//    resultsViewModel: ResultsViewModel = hiltViewModel()
//) {
//    val myPatients by resultsViewModel.myPatients.collectAsState()
//    Log.e("myPatients", myPatients.toString())
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(top = 16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top
//    ) {
//        item {
////            FlowRow(modifier = Modifier.fillMaxWidth()) {
////                for (item in 0 until myPatients.){
////                    ResultCard(
////                        diagnosis =  diagnosis[item]!!,
////                        navController = navController
////                    )
////                }
////            }
//        }
//    }
//}