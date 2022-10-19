package com.example.dr_tryaq.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.dr_tryaq.R
import com.example.dr_tryaq.domain.model.models.Diagnosis
import com.example.dr_tryaq.navigation.Screen
import com.example.dr_tryaq.ui.theme.generalCardBG

@ExperimentalCoilApi
@Composable
fun ResultCard(
    diagnosis: Diagnosis,
    navController: NavHostController
){
    val painter = rememberImagePainter(data = diagnosis.patientImage) {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp, 8.dp, 16.dp, 0.dp)
            .clickable {
                navController.navigate(Screen.ResultDetail.passDiagnosisId(diagnosisId = diagnosis.id))
            },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(0.2.dp, Color.Black),
        color = MaterialTheme.colors.generalCardBG
    ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                val (txtShuffleWord, imgDoctor, IblTapToFlip, txtWord) = createRefs()

                Surface(
                    modifier = Modifier
                        .width(54.dp)
                        .height(54.dp)
                        .constrainAs(imgDoctor) {
                            start.linkTo(parent.start)
                            linkTo(
                                top = parent.top,
                                bottom = parent.bottom
                            )
                        },
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painter,
                        contentDescription = stringResource(R.string.hero_image),
                        contentScale = ContentScale.Crop
                    )
                }

                Text(
                    text = diagnosis.patientName,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .constrainAs(txtWord) {
                            start.linkTo(imgDoctor.end)
                            top.linkTo(imgDoctor.top)
                        }
                        .padding(8.dp, 2.dp, 0.dp, 8.dp)
                )

                Text(
                    text = diagnosis.serviceName,
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
                        text = diagnosis.date,
                        style = MaterialTheme.typography.subtitle2,
                        maxLines = 1
                    )
            }

    }
}


@Composable
@Preview
fun ResultCardPre(){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp, 8.dp, 16.dp, 0.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(0.2.dp, Color.Black),
        color = MaterialTheme.colors.generalCardBG
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            val (txtShuffleWord, imgDoctor, IblTapToFlip, txtWord) = createRefs()
            Surface(
                modifier = Modifier
                    .width(54.dp)
                    .height(54.dp)
                    .constrainAs(imgDoctor) {
                        start.linkTo(parent.start)
                        linkTo(
                            top = parent.top,
                            bottom = parent.bottom
                        )
                    },
                shape = RoundedCornerShape(10.dp)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.ic_placeholder),
                    contentDescription = stringResource(R.string.hero_image),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text =" diagnosis.serviceName",
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .constrainAs(txtWord) {
                        start.linkTo(imgDoctor.end)
                        top.linkTo(imgDoctor.top)
                    }
                    .padding(8.dp, 2.dp, 0.dp, 8.dp)
            )

            Text(
                text = "diagnosis.note",
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
                text = "diagnosis.date",
                style = MaterialTheme.typography.subtitle2,
                maxLines = 1
            )
        }

    }
}