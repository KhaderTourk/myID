package com.example.tryaq.presentation.components.service_flib_card

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.tryaq.R
import com.example.tryaq.domain.model.models.Service
import com.example.tryaq.navigation.Screen
import com.example.tryaq.ui.theme.*
import com.example.tryaq.util.Constants.newAppointmentDoctorImage
import com.example.tryaq.util.Constants.newAppointmentDoctorName
import com.example.tryaq.util.Constants.newAppointmentServiceId
import com.example.tryaq.util.Constants.newAppointmentServiceName

@ExperimentalCoilApi
@Composable
fun ServiceFrontSide(
    service: Service
) {
    val painter = rememberImagePainter(data = service.image) {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }
    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = Modifier
            .width(itemSize / 2f)
            .height(312.dp)
            .padding(8.dp, 16.dp, 8.dp, 0.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(0.2.dp, DoneGreen),
            color = MaterialTheme.colors.generalCardBG
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(162.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colors.secondColor
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 24.dp, horizontal = 34.dp),
                        painter = painter,
                        contentDescription = stringResource(R.string.hero_image),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    text = service.name,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                    color = DoneGreen,
                    thickness = 0.6.dp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "متاح: ",
                        fontSize = MaterialTheme.typography.subtitle2.fontSize,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = service.timesOfWork,
                        fontSize = MaterialTheme.typography.subtitle2.fontSize,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(34.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.doneGreen)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        textAlign = TextAlign.Center,
                        text = "محجوز",
                        style = MaterialTheme.typography.subtitle2,
                        color = StableWhite,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
@Preview
fun CCardPreview(){
    val painter = rememberImagePainter(data = R.drawable.success_add) {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }
    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = Modifier
            .width(itemSize / 2f)
            .height(312.dp)
            .padding(8.dp, 16.dp, 8.dp, 0.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(0.2.dp, DoneGreen),
            color = MaterialTheme.colors.generalCardBG
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(162.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colors.secondColor
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painter,
                        contentDescription = stringResource(R.string.hero_image),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    text =" service.name",
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                    color = DoneGreen,
                    thickness = 0.6.dp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "متاح: ",
                        fontSize = MaterialTheme.typography.subtitle2.fontSize,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "service.timesOfWork",
                        fontSize = MaterialTheme.typography.subtitle2.fontSize,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(34.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.doneGreen)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        textAlign = TextAlign.Center,
                        text = "محجوز",
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.White,
                        maxLines = 1
                    )
                }
            }
        }
    }
}


@ExperimentalCoilApi
@Composable
fun ServiceCard(
    service: Service,
    navController: NavHostController
) {
    val painter = rememberImagePainter(data = service.image) {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }
    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp
    Box(
        modifier = Modifier
            .width(itemSize / 2f)
            .height(312.dp)
            .padding(8.dp, 16.dp, 8.dp, 0.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(0.2.dp, Color.Black),
            color = MaterialTheme.colors.generalCardBG
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(152.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colors.secondColor
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 24.dp, horizontal = 34.dp),
                        painter = painter,
                        contentDescription = stringResource(R.string.hero_image),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Surface(

                    shape = RoundedCornerShape(20.dp),
                    color = MaterialTheme.colors.primeColor
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        text = "₪"+service.price.toString(),
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        maxLines = 2
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth().padding(top = 4.dp),
                    text = service.name,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 2.dp),
                    color = Color.Black,
                    thickness = 0.4.dp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "متاح: ",
                        fontSize = MaterialTheme.typography.subtitle2.fontSize,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = service.timesOfWork,
                        fontSize = MaterialTheme.typography.subtitle2.fontSize,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
                Button(
                    onClick = {
                        newAppointmentServiceId = service.id
                        newAppointmentServiceName = service.name
                        newAppointmentDoctorImage = service.doctorImage
                        newAppointmentDoctorName = service.doctorName
                        navController.navigate(Screen.NewAppointment.passServiceId(serviceId = service.id))
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
                        text = "المواعيد المتاحة",
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.White,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
