package com.example.tryaq.presentation.screens.new_appointment

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.tryaq.R
import com.example.tryaq.presentation.components.SwipeToAddAppointment
import com.example.tryaq.presentation.components.home_flib_card.dayToArabic
import com.example.tryaq.presentation.components.home_flib_card.monthToArabic
import com.example.tryaq.ui.theme.*
import com.example.tryaq.util.Constants.DATE_A
import com.example.tryaq.util.Constants.DAY_A
import com.example.tryaq.util.Constants.MONTH_A
import com.example.tryaq.util.Constants.newAppointmentDepartmentName
import com.example.tryaq.util.Constants.newAppointmentDoctorImage
import com.example.tryaq.util.Constants.newAppointmentDoctorName
import com.example.tryaq.util.Constants.newAppointmentServiceName
import com.google.accompanist.pager.ExperimentalPagerApi
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

@SuppressLint("NewApi")
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun NewAppointmentScreen(
    navController: NavHostController,
    newAppointmentViewModel: NewAppointmentViewModel = hiltViewModel()
) {

    val appointments = newAppointmentViewModel.getAllAppointment.collectAsLazyPagingItems()

    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp

    val today = LocalDate.now()
    var serviceId = newAppointmentViewModel.getServiceId()
    serviceId = if (serviceId >= 10) {
        (serviceId.toString().substring(0, 2)).toInt()
    } else {
        (serviceId.toString() + "0").toInt()
    }

    val startDate = (LocalDate.now().toString()).replace("-", "").removeRange(0, 5)

    val dayOfMonth = LocalDate.now().dayOfMonth

    LocalDate.now().plusDays(1)
    val card1Color = remember { mutableStateOf(Color.LightGray) }
    val card2Color = remember { mutableStateOf(Color.LightGray) }
    val card3Color = remember { mutableStateOf(Color.LightGray) }
    val card4Color = remember { mutableStateOf(Color.LightGray) }
    val card5Color = remember { mutableStateOf(Color.LightGray) }
    val card6Color = remember { mutableStateOf(Color.LightGray) }
    val primeColor: Color = MaterialTheme.colors.primeColor
    val dayClicked = remember { mutableStateOf(0) }
    val cardsColor = remember {
        mutableListOf(card1Color, card2Color, card3Color, card4Color, card5Color, card6Color)
    }

    val painter = rememberImagePainter(data = newAppointmentDoctorImage) {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.newAppointmentBack,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                            navController.navigateUp()
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
                            text = newAppointmentServiceName,
                            color = MaterialTheme.colors.primeColor
                        )
                    }

                },
                backgroundColor = MaterialTheme.colors.topAppBarC
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                Column(
                    modifier =
                    Modifier
                        .fillMaxHeight()
                        .width(itemSize * 0.6f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = newAppointmentDoctorName,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        color = MaterialTheme.colors.stableBlack,
                        fontSize = MaterialTheme.typography.h6.fontSize
                    )
                    Text(
                        text = newAppointmentDepartmentName,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        color = MaterialTheme.colors.stableBlack,
                        fontSize = MaterialTheme.typography.subtitle2.fontSize
                    )
                }
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(itemSize * 0.4f),
                    painter = painter,
                    contentDescription = stringResource(R.string.hero_image),
                    contentScale = ContentScale.FillBounds
                )
            }
            Surface(
                modifier =
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 60.dp),
                color = MaterialTheme.colors.newAppointmentCard
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
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "المواعيد المتاحة",
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif,
                                fontSize = 18.sp,
                                color = MaterialTheme.colors.titleColor
                            )
                        }
                    }
                    item {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 8.dp, 16.dp, 18.dp),
                            color = MaterialTheme.colors.generalCardBG,
                            shape = RoundedCornerShape(10.dp),
                            elevation = 4.dp,
                        ) {
                            Column(
                                modifier =
                                Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    modifier = Modifier.padding(8.dp),
                                    text = monthToArabic(MONTH_A) + " " + LocalDate.now().year.toString(),
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colors.titleColor
                                )
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    for (item in 0..5) {
                                        Surface(
                                            modifier =
                                            Modifier
                                                .height(68.dp)
                                                .width(itemSize / 7)
                                                .padding(4.dp)
                                                .clickable {
                                                    for (color in cardsColor) {
                                                        color.value = Color.LightGray
                                                    }
                                                    cardsColor[item].value = primeColor
                                                    dayClicked.value = dayOfMonth + item
                                                    DAY_A =
                                                        today.plusDays(item.toLong()).dayOfWeek.toString()
                                                },
                                            shape = RoundedCornerShape(12.dp),
                                            color = cardsColor[item].value
                                        ) {
                                            Column(
                                                modifier = Modifier.fillMaxSize(),
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                var dayShow = today.plusDays(item.toLong()).dayOfWeek.toString()
                                                var dayNumShow = today.dayOfMonth + item

                                                if (dayShow.lowercase(Locale.getDefault()) == "thursday") {
                                                    dayShow = today.plusDays(item+ 2.toLong()).dayOfWeek.toString()
                                                    dayNumShow = today.dayOfMonth + item + 2
                                                }else if (dayShow.lowercase(Locale.getDefault()) == "friday"){
                                                    dayShow = today.plusDays(item+ 1.toLong()).dayOfWeek.toString()
                                                    dayNumShow = today.dayOfMonth + item + 1
                                                }

                                                Text(
                                                    text = dayToArabic(dayShow),
                                                    color = Color.White,
                                                    fontSize = 14.sp
                                                )
                                                Text(
                                                    text = dayNumShow.toString(),
                                                    color = Color.White,
                                                    fontSize = 14.sp
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (dayClicked.value > 0) {
                        item {
                            val aIds = ArrayList<String>()
                            for (iii in appointments.itemSnapshotList) {
                                aIds.add(iii!!.id.toString())
                            }
                            for (i in 1..9) {
                                if (!aIds.contains(startDate + "0" + i + serviceId))
                                    AppointmentsContent(
                                        navController = navController,
                                        cardId = i,
                                        serviceId = serviceId,
                                        date = dayClicked.value
                                    )
                            }
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun AppointmentsContent(navController: NavHostController, cardId: Int, serviceId: Int, date: Int) {
    val time: String = when (cardId) {
        1 -> "8:00-8:30"
        2 -> "8:30-9:00"
        3 -> "9:00-9:30"
        4 -> "9:30-10:00"
        5 -> "10:00-10:30"
        6 -> "10:30-11:00"
        7 -> "11:00-11:30"
        8 -> "11:30-12:00"
        9 -> "12:00-12:30"
        else -> "12:30-1:00"
    }

    DATE_A = date.toString()

    SwipeToAddAppointment(
        navController = navController,
        newId = ((LocalDate.now().monthValue.toString()) + date + "0" + cardId + serviceId).toInt(),
        time = time
    )

}
