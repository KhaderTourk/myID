package com.example.tryaq.presentation.components.service_flib_card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.tryaq.domain.model.models.Service
import com.example.tryaq.navigation.Screen
import com.example.tryaq.ui.theme.StableWhite
import com.example.tryaq.ui.theme.generalCardBG
import com.example.tryaq.ui.theme.primeColor
import com.example.tryaq.util.Constants.appointmentsIds
import com.example.tryaq.util.Constants.appointsIdAndName

@ExperimentalCoilApi
@Composable
fun ServiceBackSide(
    service: Service,
    navController: NavHostController
) {
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
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .height(172.dp)
                        .padding(8.dp),
                    text = "- هل أنت متأكد من إلغاء هذا الحجز ؟",
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )

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
                    .padding(top = 8.dp, bottom = 16.dp),
                    color = Color.Black,
                    thickness = 0.6.dp
                )
                Button(
                    onClick = {
                        for(sName in 0 until appointsIdAndName.size){
                            if (appointsIdAndName[sName] == service.name){
                                navController.popBackStack()
                                navController.navigate(Screen.Loading.passAppointmentId(appointmentsIds[sName]))
                            }
                        }
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
                        text = "نعم",
                        style = MaterialTheme.typography.subtitle2,
                        color = StableWhite,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun ServiceBackPreview(){
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
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .height(172.dp)
                        .padding(8.dp),
                    text = "- هل أنت متأكد من إلغاء هذا الحجز ؟",
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    text = "service.name",
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 24.dp),
                    color = Color.Black,
                    thickness = 0.6.dp
                )
                Button(
                    onClick = {
                    } ,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(34.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        textAlign = TextAlign.Center,
                        text = "نعم",
                        style = MaterialTheme.typography.subtitle2,
                        color = StableWhite,
                        maxLines = 1
                    )
                }
            }
        }
    }
}