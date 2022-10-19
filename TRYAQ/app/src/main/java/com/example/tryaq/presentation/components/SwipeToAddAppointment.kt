package com.example.tryaq.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tryaq.R
import com.example.tryaq.navigation.Screen
import com.example.tryaq.ui.theme.DoneGreen
import com.example.tryaq.ui.theme.generalCardBG
import com.example.tryaq.ui.theme.primeColor
import com.example.tryaq.ui.theme.titleColor
import com.example.tryaq.util.Constants.TIME_A
import com.example.tryaq.util.Constants.newAppointmentId
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun SwipeToAddAppointment(navController: NavHostController, newId:Int, time: String) {
    val add = SwipeAction(
        icon = { Icon(modifier = Modifier.size(0.dp), painter = painterResource(R.drawable.ic_clock), contentDescription = "")},
        background = DoneGreen,
        onSwipe = {
            newAppointmentId = newId
            TIME_A = time
            navController.popBackStack()
            navController.navigate(Screen.LoadingAddAppointment.route)
        }
    )

    SwipeableActionsBox(
        modifier = Modifier
            .height(72.dp)
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 16.dp),
        endActions = listOf(add),
        backgroundUntilSwipeThreshold = MaterialTheme.colors.primeColor
    ) {
       Surface(
           modifier =
           Modifier.fillMaxSize(),
           color = MaterialTheme.colors.generalCardBG,
           elevation = 4.dp
       ) {
           Row(
               modifier =
               Modifier.fillMaxSize(),
               verticalAlignment = Alignment.CenterVertically
           ) {
               Text(
                   modifier = Modifier
                       .padding(start = 24.dp),
                   text = time,
                   textAlign = TextAlign.Start,
                   fontSize = MaterialTheme.typography.h6.fontSize,
                   color = MaterialTheme.colors.titleColor
               )
               Row(
                   modifier =
                   Modifier.fillMaxSize().padding(end = 16.dp),
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.End
               ) {
                   Image(
                       modifier = Modifier.fillMaxHeight(),
                       alignment = Alignment.CenterEnd,
                       painter = painterResource(id = R.drawable.ic_new_appointment_arrow),
                       contentDescription = ""
                   )
               }
           }
       }
    }
}