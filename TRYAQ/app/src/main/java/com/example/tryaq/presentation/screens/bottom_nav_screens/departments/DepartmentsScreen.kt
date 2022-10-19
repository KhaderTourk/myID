package com.example.tryaq.presentation.screens.bottom_nav_screens.departments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.tryaq.R
import com.example.tryaq.domain.model.models.Department
import com.example.tryaq.navigation.Screen
import com.example.tryaq.ui.theme.*
import com.example.tryaq.util.Constants.newAppointmentDepartmentId
import com.example.tryaq.util.Constants.newAppointmentDepartmentName
import com.example.tryaq.util.handleDepartmentsResult
import com.google.accompanist.flowlayout.FlowRow

@ExperimentalCoilApi
@Composable
fun DepartmentsScreen (
    navController: NavHostController,
    departmentViewModel: DepartmentViewModel = hiltViewModel()
){
    val departments = departmentViewModel.getAllDepartments.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            val result = handleDepartmentsResult(departments = departments)
            if (result) {
                    FlowRow(modifier = Modifier.fillMaxWidth()) {
                       for (item in 0 until departments.itemSnapshotList.size){
                           DepartmentItem(
                               department =  departments[item]!!,
                               navController = navController
                           )
                       }
                    }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun DepartmentItem(
    department: Department,
    navController: NavHostController
    ) {
        val painter = rememberImagePainter(data = department.image) {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_placeholder)
        }
        val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp

        Box(
            modifier = Modifier
                .width(itemSize / 2f)
                .height(238.dp)
                .padding(8.dp, 16.dp, 8.dp, 0.dp)
                .clickable {
                    newAppointmentDepartmentId = department.id
                    newAppointmentDepartmentName = department.name
                    navController.navigate(Screen.Services.passDepartmentId(departmentId = department.id))
                },
            contentAlignment = Alignment.BottomStart,
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
                            .height(140.dp)
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
                            .fillMaxWidth(),
                        text = department.name,
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                        color = Color.Black,
                        thickness = 0.4.dp
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(32.dp)
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "مواعيد العمل: ",
                            fontSize = MaterialTheme.typography.subtitle2.fontSize,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = department.timesOfWork,
                            fontSize = MaterialTheme.typography.subtitle2.fontSize,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                    }

                }
            }
        }
}