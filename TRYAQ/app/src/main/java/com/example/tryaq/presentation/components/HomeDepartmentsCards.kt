package com.example.tryaq.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.tryaq.R
import com.example.tryaq.domain.model.models.Department
import com.example.tryaq.navigation.Screen
import com.example.tryaq.ui.theme.LARGE_PADDING
import com.example.tryaq.ui.theme.MEDIUM_PADDING
import com.example.tryaq.util.Constants.newAppointmentDepartmentId
import com.example.tryaq.util.Constants.newAppointmentDepartmentName

@ExperimentalCoilApi
@Composable
fun HomeDepartmentsCard(
    department: Department,
    navController: NavHostController,
    cardColor: Color
) {
    val painter = rememberImagePainter(data = department.image) {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }
    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = Modifier
            .width(itemSize / 2f)
            .height(92.dp)
            .padding(8.dp, 0.dp, 8.dp, 16.dp)
            .clickable {
                newAppointmentDepartmentId = department.id
                newAppointmentDepartmentName = department.name
                navController.navigate(Screen.Services.passDepartmentId(departmentId = department.id))
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            shape = RoundedCornerShape(size = LARGE_PADDING),
            color = cardColor
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp),
                    painter = painter,
                    contentDescription = stringResource(R.string.hero_image),
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    modifier = Modifier.padding(16.dp,0.dp,0.dp,0.dp),
                    text = department.name,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF000000)
                )
            }
        }
    }
}