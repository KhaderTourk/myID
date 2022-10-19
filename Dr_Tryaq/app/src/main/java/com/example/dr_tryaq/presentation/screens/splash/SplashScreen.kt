package com.example.dr_tryaq.presentation.screens.splash

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import android.net.Uri
import android.widget.VideoView
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dr_tryaq.R
import com.example.dr_tryaq.navigation.Screen
import com.example.dr_tryaq.ui.theme.StableWhite

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {

    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()
    val isRememberChecked by splashViewModel.isRememberChecked.collectAsState()

    val degrees = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 4000,
                delayMillis = 1
            )
        )
        navController.popBackStack()
        if (onBoardingCompleted && isRememberChecked) {
            navController.navigate(Screen.BottomNavigationWithSwipeScreen.route)
        } else if(onBoardingCompleted && !isRememberChecked) {
            navController.navigate(Screen.Login.route)
        }else{
            navController.navigate(Screen.Welcome.route)
        }
    }
    ExoPlayer()
}


@SuppressLint("RememberReturnType")
@Composable
fun ExoPlayer() {
    val context = LocalContext.current
    val uri =
        Uri.parse("android.resource://" + context.packageName + "/" + R.raw.motion)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = StableWhite),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
            AndroidView(
                factory = { localContext ->
                    val listener = MediaPlayer.OnInfoListener { mp, what, _ ->
                        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                            mp.playbackParams = mp.playbackParams.setSpeed(0.7f)
                        }
                         false
                    }

                    val videoView = VideoView(localContext)
                    videoView.setVideoURI(uri)
                    videoView.setOnPreparedListener { mp ->
                        mp.setOnInfoListener(listener)
                    }
                    videoView.setOnCompletionListener {

                    }
                    videoView.start()
                    videoView
                }
            )
    }
}
