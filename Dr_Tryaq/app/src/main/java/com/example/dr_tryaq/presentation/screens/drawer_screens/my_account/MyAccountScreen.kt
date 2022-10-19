package com.example.dr_tryaq.presentation.screens.drawer_screens.my_account

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.dr_tryaq.R
import com.example.dr_tryaq.navigation.Screen
import com.example.dr_tryaq.presentation.screens.splash.SplashViewModel
import com.example.dr_tryaq.ui.theme.*

@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalCoilApi
@Composable
fun MyAccountScreen (
    navController: NavHostController,
    myAccountViewModel: SplashViewModel = hiltViewModel()
) {
    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp
val painter = rememberImagePainter(data = myAccountViewModel.doctorImage.value) {
    placeholder(R.drawable.ic_placeholder)
    error(R.drawable.ic_placeholder)
}
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
                            text = "حسابي",
                            color = MaterialTheme.colors.primeColor
                        )
                    }

                },
                backgroundColor = MaterialTheme.colors.topAppBarC,
            actions = {
                IconButton(onClick = {
                    // onLightbulbClicked
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
                            painter = painterResource(id = R.drawable.ic_lightbulb),
                            contentDescription = stringResource(R.string.search_icon),
                            tint = MaterialTheme.colors.primeColor
                        )
                    }
                }
            }
            )
        }
    , backgroundColor = MaterialTheme.colors.topAppBarC
    ) {
        Box(modifier = Modifier.fillMaxSize(),contentAlignment= Alignment.TopCenter
        ) {
            Surface(modifier = Modifier.fillMaxWidth()) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.ic_profile_bg_shape),
                    contentDescription = "bg_shape",
                )
            }

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 48.dp),
                color = Color(0x00FFFFFF)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Surface(
                        modifier = Modifier
                            .width(150.dp)
                            .height(150.dp),
                        shape = RoundedCornerShape(100.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colors.primeColor)
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    Text(text = myAccountViewModel.doctorName.value)

                    Spacer(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .fillMaxWidth()
                    )

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Surface(
                            modifier = Modifier
                                .width(itemSize / 2f)
                                .height(120.dp)
                                .padding(8.dp, 0.dp, 8.dp, 16.dp)
                                .clickable {
//                navController.navigate(Screen.Details.passAdId(heroId = ad.id))
                                },
                            shape = RoundedCornerShape(10.dp),
                            elevation = 4.dp,
                            color = MaterialTheme.colors.generalCardBG
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Surface(
                                    modifier = Modifier
                                        .width(52.dp)
                                        .height(52.dp)
                                        .padding(8.dp),
                                    color = MaterialTheme.colors.secondColor,
                                    shape = RoundedCornerShape(10.dp),
                                    elevation = 4.dp
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(6.dp),
                                        painter = painterResource(id = R.drawable.ic_outline_person_24),
                                        contentDescription = stringResource(R.string.search_icon),
                                        tint = MaterialTheme.colors.primeColor
                                    )
                                }
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp),
                                    text = "بيانات شخصية",
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        Surface(
                            modifier = Modifier
                                .width(itemSize / 2f)
                                .height(120.dp)
                                .padding(8.dp, 0.dp, 8.dp, 16.dp)
                                .clickable {
//                navController.navigate(Screen.Details.passAdId(heroId = ad.id))
                                },
                            shape = RoundedCornerShape(10.dp),
                            elevation = 4.dp,
                            color = MaterialTheme.colors.generalCardBG
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Surface(
                                    modifier = Modifier
                                        .width(52.dp)
                                        .height(52.dp)
                                        .padding(8.dp),
                                    shape = RoundedCornerShape(10.dp),
                                    elevation = 4.dp,
                                    color = MaterialTheme.colors.secondColor
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(6.dp),
                                        painter = painterResource(id = R.drawable.ic_record),
                                        contentDescription = stringResource(R.string.search_icon),
                                        tint = MaterialTheme.colors.primeColor
                                    )
                                }
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp),
                                    text = "سجل طبي",
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

