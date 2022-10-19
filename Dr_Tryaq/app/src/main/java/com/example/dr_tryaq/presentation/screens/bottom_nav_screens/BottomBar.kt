package com.example.dr_tryaq.presentation.screens.bottom_nav_screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.dr_tryaq.R
import com.example.dr_tryaq.navigation.Screen
import com.example.dr_tryaq.presentation.components.HomeTopBar
import com.example.dr_tryaq.presentation.screens.bottom_nav_screens.home.HomeScreen
import com.example.dr_tryaq.presentation.screens.drawer_screens.NavigationDrawer
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun BottomNavigationWithSwipeScreen(
    navController: NavHostController
) {
    var navigateClick by remember { mutableStateOf(false) }
    val offSetAnim by animateDpAsState(targetValue = if (navigateClick) 253.dp else 0.dp)
    val scaleAnim by animateFloatAsState(targetValue = if (navigateClick) 0.6f else 1.0f)

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NavigationDrawer(navController)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .scale(scaleAnim)
                .offset(x = offSetAnim)
                .clip(if (navigateClick) RoundedCornerShape(20.dp) else RoundedCornerShape(0.dp))
                .background(MaterialTheme.colors.background)
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (topBar, content, logo) = createRefs()
                // CONTENT
                Box(
                    modifier = Modifier
                        .size(
                            width = LocalConfiguration.current.screenWidthDp.dp * 1f,
                            height = (LocalConfiguration.current.screenHeightDp.dp - 80.dp)
                        )
                        .constrainAs(content) {
                            top.linkTo(topBar.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                ) {
                    HomeScreen(navController = navController)
                }
                // top
                Box(
                    modifier = Modifier.constrainAs(topBar) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                ) {
                    HomeTopBar(
                        onNotificationClicked = { navController.navigate(Screen.Notifications.route) },
                        onMenuClicked = { navigateClick = !navigateClick }
                    )
                }
                // logo
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.constrainAs(logo) {
                        top.linkTo(topBar.top)
                        start.linkTo(topBar.start, margin = 62.dp)
                        bottom.linkTo(topBar.bottom)
                    }
                ) {
                    Image(
                        modifier = Modifier.height(48.dp).width(42.dp),
                        painter = rememberImagePainter(data = R.drawable.img_logo_1) {
                        placeholder(R.drawable.ic_placeholder)
                        error(R.drawable.ic_placeholder) }, contentDescription = "j")
                    Image(
                        modifier = Modifier.height(32.dp).width(48.dp),
                        painter = rememberImagePainter(data = R.drawable.img_logo_2) {
                            placeholder(R.drawable.ic_placeholder)
                            error(R.drawable.ic_placeholder) }, contentDescription = "j")
                }

                }
            }
        }
    }
