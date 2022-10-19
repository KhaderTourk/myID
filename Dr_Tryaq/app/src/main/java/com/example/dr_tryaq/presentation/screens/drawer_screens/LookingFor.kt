package com.example.dr_tryaq.presentation.screens.drawer_screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.dr_tryaq.R
import com.example.dr_tryaq.navigation.Screen
import com.example.dr_tryaq.ui.theme.primeColor
import com.example.dr_tryaq.ui.theme.secondColor
import com.example.dr_tryaq.ui.theme.topAppBarC

@ExperimentalCoilApi
@Composable
fun LookingForScreen (
    navController: NavHostController
) {

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
                            text = "نظرة مستقبلية",
                            color = MaterialTheme.colors.primeColor
                        )
                    }

                },
                backgroundColor = MaterialTheme.colors.topAppBarC,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start

        ) {
            Text(
                text = "تطمح الجامعة الى تطوير الدائرة الطبية إلى مستشفى ليخدم اقسام الجامعة  المختلفة من طلاب وعاملين وخريجين بشكل خاص ويرقى بالخدمات الصحية في  المجتمع المحلي بشكل عام  .\n" +
                        "\n" +
                        "الرؤية:\n" +
                        "تطوير بيئة صحية جامعية مجتمعية سليمة من خلال المحافظة على صحة الطلاب والعاملين والبيئة الجامعية .\n" +
                        "\n" +
                        "الرسالة:\n" +
                        "        الدائرة الطبية بالجامعة الإسلامية تقدم خدمات الرعاية الصحية الأولية  والتخصصات الطبية المتعددة على أسس علمية متطورة للطلاب والعاملين في الجامعة من أجل الحفاظ على بيئة صحية سليمة في الجامعة بشكل خاص والمجتمع المحلي بشكل عام.\n" +
                        "\n" +
                        " ",
                style = MaterialTheme.typography.subtitle2
            )

        }
    }
}