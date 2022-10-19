package com.example.dr_tryaq.presentation.screens.bottom_nav_screens.results.result_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.dr_tryaq.R
import com.example.dr_tryaq.navigation.Screen
import com.example.dr_tryaq.ui.theme.primeColor
import com.example.dr_tryaq.ui.theme.secondColor
import com.example.dr_tryaq.ui.theme.topAppBarC

@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalCoilApi
@Composable
fun  ResultDetailScreen(
    navController: NavHostController,
resultDetailViewModel: ResultDetailViewModel = hiltViewModel()
) {
    val selectedDiagnosis by resultDetailViewModel.selectedDiagnosis.collectAsState()
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
                            text = "تفاصيل الخدمة",
                            color = MaterialTheme.colors.primeColor
                        )
                    }

                },
                backgroundColor = MaterialTheme.colors.topAppBarC)
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 24.dp, 16.dp, 0.dp)) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(138.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = 4.dp,
            ) {
                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)) {
                    Column(modifier = Modifier.weight(2f)) {
                        selectedDiagnosis?.let { it1 -> Text(text = it1.serviceName, modifier = Modifier.weight(1f)) }
                        selectedDiagnosis?.let { it1 -> Text(text = it1.patientName, modifier = Modifier.weight(1f)) }
                        selectedDiagnosis?.let { it1 -> Text(text = it1.doctorName, modifier = Modifier.weight(1f)) }
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Row (modifier = Modifier.weight(1f)){
                            Icon(modifier = Modifier.padding(end = 8.dp), painter = painterResource(id = R.drawable.ic_my_appointments), contentDescription ="", tint = MaterialTheme.colors.primeColor )
                            selectedDiagnosis?.let { it1 -> Text(text = it1.date, fontSize = 14.sp) }
                        }
                        Row (modifier = Modifier.weight(1f)){
                            Icon(modifier = Modifier.padding(end = 8.dp), painter = painterResource(id = R.drawable.ic_clock), contentDescription ="", tint = MaterialTheme.colors.primeColor )
                            selectedDiagnosis?.let { it1 -> Text(text = it1.time, fontSize = 14.sp) }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(10.dp),
                elevation = 4.dp,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment =  Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "النتيجة (التشخيص):",
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    selectedDiagnosis?.let { it1 ->
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it1.analyzesResult,
                            textAlign = TextAlign.Start,
                            fontSize = 14.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "التحاليل المطلوبة:",
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    selectedDiagnosis?.let { it1 ->
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it1.analysisRequired,
                            textAlign = TextAlign.Start,
                            fontSize = 14.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "الأدوية المطلوبة:",
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    selectedDiagnosis?.let { it1 ->
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it1.medicineRequired,
                            textAlign = TextAlign.Start,
                            fontSize = 14.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "ملاحظات:",
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    selectedDiagnosis?.let { it1 ->
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it1.note,
                            textAlign = TextAlign.Start,
                            fontSize = 14.sp,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
@Preview
fun DetailPreview(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp, 24.dp, 16.dp, 0.dp)) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 4.dp,
        ) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                Column(modifier = Modifier.weight(2f)) {
                    Text(text = "اسم الخدمة", modifier = Modifier.weight(1f))
                    Text(text = "اسم  المريض", modifier = Modifier.weight(1f))
                    Text(text = "اسم  الطبيب", modifier = Modifier.weight(1f))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Row (modifier = Modifier.weight(1f)){
                        Icon(modifier = Modifier.padding(end = 8.dp), painter = painterResource(id = R.drawable.ic_my_appointments), contentDescription ="", tint = MaterialTheme.colors.primeColor )
                        Text(text = "30/1/2022")
                    }
                    Row (modifier = Modifier.weight(1f)){
                        Icon(modifier = Modifier.padding(end = 8.dp), painter = painterResource(id = R.drawable.ic_clock), contentDescription ="", tint = MaterialTheme.colors.primeColor )
                        Text(text = "8:00-8:30")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(10.dp),
            elevation = 4.dp,
        ) {
            Text(text = "  ", modifier = Modifier
                .padding(22.dp)
                .weight(1f))
        }
    }

}