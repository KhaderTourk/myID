package com.example.tryaq.presentation.screens.bottom_nav_screens.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.tryaq.presentation.components.ResultCard
import com.example.tryaq.presentation.screens.splash.SplashViewModel
import com.example.tryaq.util.handleDiagnosisResult
import com.google.accompanist.flowlayout.FlowRow

@ExperimentalCoilApi
@Composable
fun ResultsScreen(
    navController: NavHostController,
    resultsViewModel: ResultsViewModel = hiltViewModel(),
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val patientId by splashViewModel.patientId.collectAsState()
    val diagnosis = resultsViewModel.getAllDiagnosis(patientId = patientId).collectAsLazyPagingItems()
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
                    for (item in 0 until diagnosis.itemCount){

                        ResultCard(
                            diagnosis =  diagnosis[item]!!,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}