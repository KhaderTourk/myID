package com.example.dr_tryaq.presentation.screens.notifications

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.dr_tryaq.R
import com.example.dr_tryaq.navigation.Screen
import com.example.dr_tryaq.ui.theme.*

@ExperimentalCoilApi
@Composable
fun NotificationsScreen (
    navController: NavHostController,
) {
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
                    text = "الإشعارات",
                    color = MaterialTheme.colors.primeColor
                )
            }

        },
        backgroundColor = MaterialTheme.colors.topAppBarC
        )}
        ) {
        EmptyNotification()
    }

}

@Composable
fun EmptyNotification() {
    val icon by remember {
        mutableStateOf(R.drawable.ic_network_error)
    }

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 5000
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    ContentNotification(alphaAnim = alphaAnim, icon = icon, message = "قيد التطوير")
}

@Composable
fun ContentNotification(alphaAnim: Float, icon: Int, message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(NETWORK_ERROR_ICON_HEIGHT)
                .alpha(alpha = alphaAnim),
            painter = painterResource(id = icon),
            contentDescription = stringResource(R.string.network_error_icon)
        )
        Text(
            modifier = Modifier
                .padding(top = SMALL_PADDING)
                .alpha(alpha = alphaAnim),
            text = message,
            color = if (isSystemInDarkTheme()) LightGray else DarkGray,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }
}
