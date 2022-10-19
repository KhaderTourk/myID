package com.example.tryaq.presentation.screens.drawer_screens

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
import com.example.tryaq.R
import com.example.tryaq.navigation.Screen
import com.example.tryaq.ui.theme.primeColor
import com.example.tryaq.ui.theme.secondColor
import com.example.tryaq.ui.theme.topAppBarC

@ExperimentalCoilApi
@Composable
fun ImportanceScreen (
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
                            text = "أهمية الدائرة الطبية",
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
                text = "مقدمة:\n" +
                        "الدائرة الطبية بالجامعة الإسلامية هي إحدى مراكز الجامعة التي تقدم خدماتها للطلاب والعاملين والخريجين بشكل خاص وللمجتمع المحلي بشكل عام.\n" +
                        "\n" +
                        ":النشأة:\n" +
                        "أنشأت الدائرة الطبية بالجامعة الإسلامية منذ العام 1982 م أسوة بباقي الجامعات العريقة بالعالم تحت وطأة الظروف الصحية الصعبة زمن الاحتلال الإسرائيلي وكانت تقدم خدمات الطوارئ والرعاية الصحية الأولية\n" +
                        "        وقد تطورت العيادة الطبية لتواكب الزيادة في عدد الطلاب والعاملين والمباني الجامعية من خلال تحسين الجودة النوعية للخدمات الصحية بما يتناسب مع التطور العلمي والتقني في هذا المجال مع الأخذ في الاعتبار الظروف \n" +
                        "\n" +
                        "\n",
                style = MaterialTheme.typography.subtitle2
            )

        }
    }
}