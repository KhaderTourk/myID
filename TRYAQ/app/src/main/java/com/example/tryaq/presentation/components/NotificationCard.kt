package com.example.tryaq.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.tryaq.ui.theme.generalCardBG

@Composable
fun NotificationCard(
){

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp,8.dp,16.dp,0.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(0.2.dp, Color.Black),
        color = MaterialTheme.colors.generalCardBG
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            val (txtShuffleWord, IblTapToFlip, txtWord) = createRefs()


            Text(
                text = "Culminate",
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .constrainAs(txtWord) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .padding(8.dp,2.dp,0.dp,8.dp)
            )

            Text(
                text = "TAP TO SEE THE MEANING",
                style = MaterialTheme.typography.subtitle2,
                color = Color(0xFFA5A5A5),
                maxLines = 1,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .constrainAs(IblTapToFlip) {
                        start.linkTo(txtWord.start)
                        top.linkTo(txtWord.bottom)
                    }
                    .padding(start = 8.dp)
            )

            Text(
                modifier =  Modifier
                    .constrainAs(txtShuffleWord) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                text = "30/5/2022",
                style = MaterialTheme.typography.subtitle2,
                maxLines = 1
            )
        }

    }
}