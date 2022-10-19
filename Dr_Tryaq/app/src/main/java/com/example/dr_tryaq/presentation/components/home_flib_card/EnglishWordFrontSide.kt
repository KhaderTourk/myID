package com.example.dr_tryaq.presentation.components.home_flib_card

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
import com.example.dr_tryaq.R
import com.example.dr_tryaq.domain.model.models.Appointment
import com.example.dr_tryaq.navigation.Screen
import com.example.dr_tryaq.ui.theme.*
import com.example.dr_tryaq.util.Constants.DAY_D
import com.example.dr_tryaq.util.Constants.D_APPOINT_ID
import com.example.dr_tryaq.util.Constants.D_DEPARTMENT_ID
import com.example.dr_tryaq.util.Constants.D_DOCTOR_IMAGE
import com.example.dr_tryaq.util.Constants.D_DOCTOR_NAME
import com.example.dr_tryaq.util.Constants.D_IS_ANALYSIS
import com.example.dr_tryaq.util.Constants.D_PATIENT_ID
import com.example.dr_tryaq.util.Constants.D_PATIENT_IMAGE
import com.example.dr_tryaq.util.Constants.D_PATIENT_NAME
import com.example.dr_tryaq.util.Constants.D_SERVICE_ID
import com.example.dr_tryaq.util.Constants.D_SERVICE_IMAGE
import com.example.dr_tryaq.util.Constants.D_SERVICE_NAME
import com.example.dr_tryaq.util.Constants.TIME_D
import java.util.*

@ExperimentalCoilApi
@Composable
fun EnglishWordFrontSide(
    appointment: Appointment
) {
    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp

    val painter =rememberImagePainter(data = appointment.patientImage) {
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
                        text = appointment.patientName,
                        style = MaterialTheme.typography.subtitle1,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .constrainAs(txtWord) {
                                start.linkTo(imgDoctor.end)
                                top.linkTo(imgDoctor.top)
                            }
                            .padding(top = 12.dp)
                    )

                    Text(
                        text = appointment.serviceName,
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
                            text = "بالانتظار.",
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

@ExperimentalCoilApi
@Composable
fun EnglishWordBackSide(
    appointment: Appointment,
    navController: NavHostController
) {
    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp

    val painter =rememberImagePainter(data = appointment.patientImage) {
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(78.dp)
                        .padding(8.dp)
                ) {
                    val (imgDoctor, IblTapToFlip, txtWord) = createRefs()

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
                        text = appointment.patientName,
                        style = MaterialTheme.typography.subtitle1,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .constrainAs(txtWord) {
                                start.linkTo(imgDoctor.end)
                                top.linkTo(imgDoctor.top)
                            }
                            .padding(top = 12.dp)
                    )

                    Text(
                        text = appointment.serviceName,
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
                }
                Divider(thickness = 0.2.dp, modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp), color = Color.Black)
                    Button(
                        onClick = {
                             D_APPOINT_ID = appointment.id
                             DAY_D = appointment.day
                             TIME_D = appointment.time
                             D_SERVICE_NAME = appointment.serviceName
                             D_PATIENT_NAME = appointment.patientName
                             D_PATIENT_IMAGE = appointment.patientImage
                             D_PATIENT_ID = appointment.patientId
                             D_SERVICE_ID = appointment.serviceId
                            D_SERVICE_IMAGE = appointment.doctorImage
                            D_DOCTOR_NAME = appointment.doctorName
                            D_DOCTOR_IMAGE = appointment.doctorImage
                            D_DEPARTMENT_ID = appointment.departmentId

                            D_IS_ANALYSIS = if (appointment.departmentId == 5) 1
                            else -1

                            navController.navigate(Screen.NewDiagnosisScreen.route)
                        } ,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(34.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primeColor)
                    ){
                        Text(
                            modifier = Modifier
                                .fillMaxSize(),
                            textAlign = TextAlign.Center,
                            text = "تشخيص الحالة",
                            style = MaterialTheme.typography.subtitle2,
                            color = StableWhite,
                            maxLines = 1
                        )
                    }
            }
        }
    }
}
