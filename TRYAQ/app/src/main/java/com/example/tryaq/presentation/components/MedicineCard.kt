package com.example.tryaq.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.tryaq.domain.model.models.Medicine
import com.example.tryaq.ui.theme.*

@Composable
fun MedicineCard (
    medicine: Medicine
) {
    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp

    Box(
    modifier = Modifier
    .width(itemSize / 2f)
    .height(108.dp)
    .padding(8.dp, 0.dp, 8.dp, 16.dp),
    contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            shape = RoundedCornerShape(size = LARGE_PADDING),
            color = MaterialTheme.colors.medicineCardBG
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    modifier = Modifier.padding(16.dp,0.dp,0.dp,0.dp),
                    text = medicine.name,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp, top = 4.dp),
                    shape = RoundedCornerShape(50.dp),
                    color = MaterialTheme.colors.primeColor
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "₪" + medicine.price,
                            fontSize = MaterialTheme.typography.subtitle2.fontSize,
                            maxLines = 1,
                            color = StableWhite,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}