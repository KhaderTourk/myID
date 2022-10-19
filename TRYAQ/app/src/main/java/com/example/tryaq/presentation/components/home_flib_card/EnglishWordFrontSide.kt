package com.example.tryaq.presentation.components.home_flib_card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.tryaq.R
import com.example.tryaq.domain.model.models.Appointment
import com.example.tryaq.navigation.Screen
import com.example.tryaq.ui.theme.*
import java.util.*

@ExperimentalCoilApi
@Composable
fun EnglishWordFrontSide(
    appointment: Appointment
) {
    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp

    val painter =rememberImagePainter(data = appointment.doctorImage) {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Row(
        modifier = Modifier
            .padding(16.dp, 6.dp)
            .fillMaxWidth()
            .height(122.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp, 8.dp, 8.dp, 8.dp)
                .width(itemSize * 0.15f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = monthToArabic(appointment.month),
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1
            )
            Surface(
                modifier = Modifier
                    .fillMaxHeight(),
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colors.primeColor
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp, 18.dp, 4.dp, 0.dp),
                    text = dayToArabic(appointment.day) +"   "+ appointment.date,
                    style = MaterialTheme.typography.subtitle1,
                    color = StableWhite,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
            }
        }

        Surface(
            modifier = Modifier
                .width(itemSize * 0.85f)
                .fillMaxHeight(),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(0.2.dp, Color.Black),
            color = MaterialTheme.colors.generalCardBG
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f)
                        .padding(8.dp)
                ) {
                    val (txtShuffleWord, imgDoctor, IblTapToFlip, txtWord) = createRefs()

                    Surface(
                        modifier = Modifier
                            .width(70.dp)
                            .height(70.dp)
                            .padding(8.dp)
                            .constrainAs(imgDoctor) {
                                start.linkTo(parent.start)
                                linkTo(
                                    top = parent.top,
                                    bottom = parent.bottom
                                )
                            },
                        shape = RoundedCornerShape(size = 50.dp)
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painter,
                            contentDescription = stringResource(R.string.hero_image),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Text(
                        text = appointment.serviceName,
                        style = MaterialTheme.typography.subtitle1,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .constrainAs(txtWord) {
                                start.linkTo(imgDoctor.end)
                                top.linkTo(imgDoctor.top)
                            }.padding(top = 12.dp)
                    )

                    Text(
                        text = appointment.doctorName,
                        style = MaterialTheme.typography.subtitle2,
                        color = LightGray,
                        maxLines = 1,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .constrainAs(IblTapToFlip) {
                                start.linkTo(txtWord.start)
                                top.linkTo(txtWord.bottom)
                            }
                            .padding(top = 4.dp)
                    )

                    Surface(
                        modifier = Modifier
                            .constrainAs(txtShuffleWord) {
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                            },
                        color = MaterialTheme.colors.secondColor,
                        shape = RoundedCornerShape(size = LARGE_PADDING)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(start = 6.dp, end = 6.dp),
                            text = "قيد الإنتظار.",
                            style = MaterialTheme.typography.subtitle2,
                            color = MaterialTheme.colors.stableBlack,
                            maxLines = 1
                        )
                    }
                }
                Surface(
                    modifier = Modifier
                    .fillMaxSize(),
                    shape = RoundedCornerShape(
                        bottomStart = 10.dp,
                        bottomEnd = 10.dp
                    ),
                    border = BorderStroke(0.2.dp, Color.Black),
                    color = MaterialTheme.colors.generalCardBG
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .width(16.dp)
                                .height(16.dp),
                            painter = painterResource(id = R.drawable.ic_clock),
                            contentDescription = stringResource(R.string.hero_image)
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp),
                            text = appointment.time,
                            fontSize = 12.sp,
                            maxLines = 1
                        )
                    }
                }

            }
        }
    }
}

fun monthToArabic(month: String): String{
    return when(month.lowercase(Locale.getDefault())){
        "jan" -> "يناير"
        "feb" -> "فبراير"
        "mar" -> "مارس"
        "apr" -> "ابريل"
        "may" -> "مايو"
        "jun" -> "يونيو"
        "jul" -> "يوليو"
        "aug" -> "اغسطس"
        "sep" -> "سبتمبر"
        "oct" -> "اكتوبر"
        "nov" -> "نوفمبر"
        "dec" -> "ديسمبر"
        else -> ""
    }
}
fun dayToArabic(day: String): String{
    return when(day.lowercase(Locale.getDefault())){
        "saturday" -> "السبت"
        "sunday" -> "الاحد"
        "monday" -> "الاثنين"
        "tuesday" -> "الثلاثاء"
        "wednesday" -> "الأربعاء"
        "thursday" -> "الخميس"
        "friday" -> "الجمعة"
        else -> ""
    }
}

@Composable
fun EnglishWordBackSide(
    appointment: Appointment,
    navController: NavHostController
) {
    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp
    Row(
        modifier = Modifier
            .padding(16.dp, 6.dp)
            .fillMaxWidth()
            .height(102.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp, 8.dp, 8.dp, 8.dp)
                .width(itemSize * 0.15f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = monthToArabic(appointment.month),
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1
            )
            Surface(
                modifier = Modifier
                    .fillMaxHeight(),
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colors.primeColor
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp, 18.dp, 4.dp, 0.dp),
                    text = dayToArabic(appointment.day) +"   "+ appointment.date,
                    style = MaterialTheme.typography.subtitle1,
                    color = StableWhite,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
            }
        }

        Surface(
            modifier = Modifier
                .width(itemSize * 0.85f)
                .fillMaxHeight(),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(0.2.dp, Color.Black),
            color = MaterialTheme.colors.generalCardBG
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(16.dp),
                    text = "هل انت متاكد من الغاء الموعد؟",
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.stableBlack
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top,
                ) {
                    Button(
                        onClick = {
                            navController.navigate(Screen.Loading.passAppointmentId(appointmentId = appointment.id))
                        } ,
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .height(34.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primeColor)
                    ){

                        Text(
                            modifier = Modifier
                                .fillMaxSize(),
                            textAlign = TextAlign.Center,
                            text = "نعم",
                            style = MaterialTheme.typography.subtitle1,
                            color = StableWhite,
                            maxLines = 1
                        )
                    }

                }
            }
        }
    }
}
