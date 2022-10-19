package com.example.dr_tryaq.presentation.screens.drawer_screens.medicine

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
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
import com.example.dr_tryaq.presentation.components.MedicineCard
import com.example.dr_tryaq.ui.theme.primeColor
import com.example.dr_tryaq.ui.theme.secondColor
import com.example.dr_tryaq.ui.theme.topAppBarC
import com.example.dr_tryaq.util.handleMedicinesResult
import com.google.accompanist.flowlayout.FlowRow

@ExperimentalCoilApi
@Composable
fun MedicinesScreen (
    navController: NavHostController,
    medicinesViewModel: MedicinesViewModel = hiltViewModel()
) {


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
                            text = "الأدوية",
                            color = MaterialTheme.colors.primeColor
                        )
                    }

                },
                backgroundColor = MaterialTheme.colors.topAppBarC,
                actions = {
                    IconButton(onClick = {  navController.navigate(Screen.Search.route) }) {
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
                                imageVector = Icons.Default.Search,
                                contentDescription = stringResource(R.string.search_icon),
                                tint = MaterialTheme.colors.primeColor
                            )
                        }
                    }
                }
            )
        }
    ) {
        val medicines = medicinesViewModel.getMedicines.collectAsLazyPagingItems()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize().padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                val result = handleMedicinesResult(medicines = medicines)
                if (result) {
                    FlowRow(modifier = Modifier.fillMaxWidth()) {
                        for (medicine in medicines.itemSnapshotList ){
                            MedicineCard(medicine!!)
                        }
                    }
                }
            }
        }
    }
}