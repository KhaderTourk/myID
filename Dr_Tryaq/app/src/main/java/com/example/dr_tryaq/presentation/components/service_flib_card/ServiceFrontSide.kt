package com.example.dr_tryaq.presentation.components.service_flib_card

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.dr_tryaq.R
import com.example.dr_tryaq.domain.model.models.Service
import com.example.dr_tryaq.navigation.Screen
import com.example.dr_tryaq.ui.theme.*
import com.example.dr_tryaq.util.Constants.SERVICE_ID

@ExperimentalCoilApi
@Composable
fun ActiveServiceFrontSide(
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
            .height(248.dp)
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
                Text(
                    modifier = Modifier
                        .fillMaxWidth().padding(bottom = 8.dp),
                    text = service.name,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
                Divider(modifier = Modifier
                    .fillMaxWidth(),
                    color = Color.Black,
                    thickness = 0.4.dp
                )

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primeColor)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        textAlign = TextAlign.Center,
                        text = "فعّال",
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
fun ActiveServiceBackSide(
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
            .height(248.dp)
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
                Text(
                    modifier = Modifier
                        .fillMaxWidth().padding(bottom = 8.dp),
                    text = service.name,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
                Divider(modifier = Modifier
                    .fillMaxWidth(),
                    color = Color.Red,
                    thickness = 0.4.dp
                )

                Button(
                    onClick = {
                        SERVICE_ID = service.id
                        navController.navigate(Screen.LoadingChangeServiceState.route)
                    } ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        textAlign = TextAlign.Center,
                        text = "تعطيل",
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
fun DeActiveServiceFrontSide(
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
            .height(248.dp)
            .padding(8.dp, 16.dp, 8.dp, 0.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(0.2.dp, Color.Red),
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
                Text(
                    modifier = Modifier
                        .fillMaxWidth().padding(bottom = 8.dp),
                    text = service.name,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
                Divider(modifier = Modifier
                    .fillMaxWidth(),
                    color = Color.Red,
                    thickness = 0.4.dp
                )

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        textAlign = TextAlign.Center,
                        text = "معطّل",
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
fun DeActiveServiceBackSide(
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
            .height(248.dp)
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
                Text(
                    modifier = Modifier
                        .fillMaxWidth().padding(bottom = 8.dp),
                    text = service.name,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
                Divider(modifier = Modifier
                    .fillMaxWidth(),
                    color = Color.Black,
                    thickness = 0.4.dp
                )

                Button(
                    onClick = {
                        SERVICE_ID = service.id
                        navController.navigate(Screen.LoadingChangeServiceState.route)
                    } ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primeColor)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        textAlign = TextAlign.Center,
                        text = "تفعيل",
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
@Preview
fun CardPreview(){
    val painter = rememberImagePainter(data = "service.image") {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }
    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp
    Box(
        modifier = Modifier
            .width(itemSize / 2f)
            .height(248.dp)
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
                Text(
                    modifier = Modifier
                        .fillMaxWidth().padding(bottom = 8.dp),
                    text = "service.name",
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
                Divider(modifier = Modifier
                    .fillMaxWidth(),
                    color = Color.Black,
                    thickness = 0.4.dp
                )
                Button(
                    onClick = {
                        //     navController.navigate(Screen.NewAppointment.passServiceId(serviceId = service.id))
                    } ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                        .padding(4.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primeColor)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        textAlign = TextAlign.Center,
                        text = "تفعيل",
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.White,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
