package com.example.dr_tryaq.presentation.screens.bottom_nav_screens.new_diagnosis

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.dr_tryaq.R
import com.example.dr_tryaq.navigation.Screen
import com.example.dr_tryaq.ui.theme.*
import com.example.dr_tryaq.util.Constants.D_ANALYSIS_REQ
import com.example.dr_tryaq.util.Constants.D_ANALYSIS_RESULT
import com.example.dr_tryaq.util.Constants.D_APPOINT_ID
import com.example.dr_tryaq.util.Constants.D_MEDICINE_REQ
import com.example.dr_tryaq.util.Constants.D_NEW_A_DAY
import com.example.dr_tryaq.util.Constants.D_NOTE
import com.example.dr_tryaq.util.Constants.D_PATIENT_NAME
import com.example.dr_tryaq.util.Constants.D_SERVICE_NAME
import com.example.dr_tryaq.util.handleMedicinesResult
import com.example.dr_tryaq.util.handleServicesResult


@ExperimentalMaterialApi
@Composable
fun NewDiagnosisScreen(
    navController: NavHostController,
    newDiagnosisViewModel: NewDiagnosisViewModel = hiltViewModel()
    ) {
    val focusManager =  LocalFocusManager.current
    val diagnosisResult = remember { mutableStateOf("") }
    val note = remember { mutableStateOf("") }

    val analysis = newDiagnosisViewModel.analysis.collectAsLazyPagingItems()
    val expandedState1 = remember { mutableStateOf(false) }

    val analysisState1 = remember { mutableStateOf(false) }
    val analysisState2 = remember { mutableStateOf(false) }
    val analysisState3 = remember { mutableStateOf(false) }
    val analysisState4 = remember { mutableStateOf(false) }

    val namesState1 = remember { mutableStateOf("") }
    val namesState2 = remember { mutableStateOf("") }
    val namesState3 = remember { mutableStateOf("") }
    val namesState4 = remember { mutableStateOf("") }

    val rotationState1 = animateFloatAsState(
        targetValue = if (expandedState1.value) 180f else 0f
    )

    val result1 = handleServicesResult(services = analysis)
    if (result1 && analysis.itemCount > 3) {
        namesState1.value = analysis.itemSnapshotList.items[0].name
        namesState2.value = analysis.itemSnapshotList.items[1].name
        namesState3.value = analysis.itemSnapshotList.items[2].name
        namesState4.value = analysis.itemSnapshotList.items[3].name
    }

    val medicines = newDiagnosisViewModel.medicines.collectAsLazyPagingItems()
    val expandedState2 = remember { mutableStateOf(false) }

    val medicinesState1 = remember { mutableStateOf(false) }
    val medicinesState2 = remember { mutableStateOf(false) }
    val medicinesState3 = remember { mutableStateOf(false) }

    val namesMState1 = remember { mutableStateOf("") }
    val namesMState2 = remember { mutableStateOf("") }
    val namesMState3 = remember { mutableStateOf("") }

    val rotationState2 = animateFloatAsState(
        targetValue = if (expandedState2.value) 180f else 0f
    )

    val result2 = handleMedicinesResult(medicines = medicines)
    if (result2 && medicines.itemCount > 2) {
        namesMState1.value = medicines.itemSnapshotList.items[0].name
        namesMState2.value = medicines.itemSnapshotList.items[1].name
        namesMState3.value = medicines.itemSnapshotList.items[2].name
    }

    val expandedState3 = remember { mutableStateOf(false) }
    val reserveState1 = remember { mutableStateOf(false) }
    val errorDayState = remember { mutableStateOf(false) }

    val daysState = remember { mutableStateOf("0") }

    val rotationState = animateFloatAsState(
        targetValue = if (expandedState3.value) 180f else 0f
    )

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
                            text = "التشخيص",
                            color = MaterialTheme.colors.primeColor
                        )
                    }

                },
                backgroundColor = MaterialTheme.colors.topAppBarC
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                FirstDataCard(navController = navController)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    fontSize = 18.sp,
                    text = "التشخيص",
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(112.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(width = 0.6.dp, color = MaterialTheme.colors.stableBlack),
                    color = MaterialTheme.colors.generalCardBG
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(72.dp)
                            .padding(16.dp),
                        value = diagnosisResult.value,
                        onValueChange = { diagnosisResult.value = it },
                        textStyle = TextStyle(textAlign = TextAlign.Start),
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = StableWhite, textColor = MaterialTheme.colors.stableBlack, cursorColor = PrimeColor, focusedIndicatorColor = PrimeColor,disabledTextColor = MaterialTheme.colors.stableBlack, placeholderColor = MaterialTheme.colors.stableBlack, disabledPlaceholderColor =MaterialTheme.colors.stableBlack ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus()})
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
           item {
               Surface(
                   modifier = Modifier
                       .fillMaxWidth()
                       .animateContentSize(
                           animationSpec = tween(
                               durationMillis = 300,
                               easing = LinearOutSlowInEasing
                           )
                       ),
                   shape = RoundedCornerShape(10.dp),
                   border = BorderStroke(width = 0.6.dp, color = MaterialTheme.colors.stableBlack),
                   onClick = {
                       expandedState1.value = !(expandedState1.value)
                   },
                   color = MaterialTheme.colors.generalCardBG
               ) {
                   Column(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(12.dp)
                   ) {
                       Row(
                           verticalAlignment = Alignment.CenterVertically
                       ) {
                           Text(
                               modifier = Modifier
                                   .weight(6f),
                               text = "التحايل المطلوبة",
                               fontSize = MaterialTheme.typography.subtitle1.fontSize,
                               fontWeight = FontWeight.Bold,
                               maxLines = 1,
                               overflow = TextOverflow.Ellipsis
                           )
                           IconButton(
                               modifier = Modifier
                                   .weight(1f)
                                   .alpha(ContentAlpha.medium)
                                   .rotate(rotationState1.value),
                               onClick = {
                                   expandedState1.value = !(expandedState1.value)
                               }) {
                               Icon(
                                   imageVector = Icons.Default.ArrowDropDown,
                                   contentDescription = "Drop-Down Arrow"
                               )
                           }
                       }

                       if (expandedState1.value) {
                           Row(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .height(42.dp),
                               horizontalArrangement = Arrangement.Start,
                               verticalAlignment = Alignment.CenterVertically
                           ) {
                               Checkbox(
                                   checked = analysisState1.value,
                                   onCheckedChange = { analysisState1.value = it },
                                   colors = CheckboxDefaults.colors(
                                       uncheckedColor = MaterialTheme.colors.stableBlack,
                                       checkedColor = PrimeColor,
                                       checkmarkColor = StableWhite,
                                       disabledIndeterminateColor = MaterialTheme.colors.stableBlack
                                   )
                               )
                               Text(
                                   text = namesState1.value,
                                   style = TextStyle(color = MaterialTheme.colors.stableBlack)
                               )
                           }
                           Row(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .height(42.dp),
                               horizontalArrangement = Arrangement.Start,
                               verticalAlignment = Alignment.CenterVertically
                           ) {
                               Checkbox(
                                   checked = analysisState2.value,
                                   onCheckedChange = { analysisState2.value = it },
                                   colors = CheckboxDefaults.colors(
                                       uncheckedColor = MaterialTheme.colors.stableBlack,
                                       checkedColor = PrimeColor,
                                       checkmarkColor = StableWhite,
                                       disabledIndeterminateColor = MaterialTheme.colors.stableBlack
                                   )
                               )
                               Text(
                                   text = namesState2.value,
                                   style = TextStyle(color = MaterialTheme.colors.stableBlack)
                               )
                           }
                           Row(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .height(42.dp),
                               horizontalArrangement = Arrangement.Start,
                               verticalAlignment = Alignment.CenterVertically
                           ) {
                               Checkbox(
                                   checked = analysisState3.value,
                                   onCheckedChange = { analysisState3.value = it },
                                   colors = CheckboxDefaults.colors(
                                       uncheckedColor = MaterialTheme.colors.stableBlack,
                                       checkedColor = PrimeColor,
                                       checkmarkColor = StableWhite,
                                       disabledIndeterminateColor = MaterialTheme.colors.stableBlack
                                   )
                               )
                               Text(
                                   text = namesState3.value,
                                   style = TextStyle(color = MaterialTheme.colors.stableBlack)
                               )
                           }
                           Row(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .height(42.dp),
                               horizontalArrangement = Arrangement.Start,
                               verticalAlignment = Alignment.CenterVertically
                           ) {
                               Checkbox(
                                   checked = analysisState4.value,
                                   onCheckedChange = { analysisState4.value = it },
                                   colors = CheckboxDefaults.colors(
                                       uncheckedColor = MaterialTheme.colors.stableBlack,
                                       checkedColor = PrimeColor,
                                       checkmarkColor = StableWhite,
                                       disabledIndeterminateColor = MaterialTheme.colors.stableBlack
                                   )
                               )
                               Text(
                                   text = namesState4.value,
                                   style = TextStyle(color = MaterialTheme.colors.stableBlack)
                               )
                           }

                       }
                   }
               }

               Spacer(modifier = Modifier.height(8.dp))
               Surface(
                   modifier = Modifier
                       .fillMaxWidth()
                       .animateContentSize(
                           animationSpec = tween(
                               durationMillis = 300,
                               easing = LinearOutSlowInEasing
                           )
                       ),
                   shape = RoundedCornerShape(10.dp),
                   border = BorderStroke(width = 0.6.dp, color = MaterialTheme.colors.stableBlack),
                   onClick = {
                       expandedState2.value = !(expandedState2.value)
                   },
                   color = MaterialTheme.colors.generalCardBG
               ) {
                   Column(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(12.dp)
                   ) {
                       Row(
                           verticalAlignment = Alignment.CenterVertically
                       ) {
                           Text(
                               modifier = Modifier
                                   .weight(6f),
                               text = "الأدوية المطلوبة",
                               fontSize = MaterialTheme.typography.subtitle1.fontSize,
                               fontWeight = FontWeight.Bold,
                               maxLines = 1,
                               overflow = TextOverflow.Ellipsis
                           )
                           IconButton(
                               modifier = Modifier
                                   .weight(1f)
                                   .alpha(ContentAlpha.medium)
                                   .rotate(rotationState2.value),
                               onClick = {
                                   expandedState2.value = !(expandedState2.value)
                               }) {
                               Icon(
                                   imageVector = Icons.Default.ArrowDropDown,
                                   contentDescription = "Drop-Down Arrow"
                               )
                           }
                       }

                       if (expandedState2.value) {
                           Row(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .height(42.dp),
                               horizontalArrangement = Arrangement.Start,
                               verticalAlignment = Alignment.CenterVertically
                           ) {
                               Checkbox(
                                   checked = medicinesState1.value,
                                   onCheckedChange = { medicinesState1.value = it },
                                   colors = CheckboxDefaults.colors(
                                       uncheckedColor = MaterialTheme.colors.stableBlack,
                                       checkedColor = PrimeColor,
                                       checkmarkColor = StableWhite,
                                       disabledIndeterminateColor = MaterialTheme.colors.stableBlack
                                   )
                               )
                               Text(
                                   text = namesMState1.value,
                                   style = TextStyle(color = MaterialTheme.colors.stableBlack)
                               )
                           }
                           Row(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .height(42.dp),
                               horizontalArrangement = Arrangement.Start,
                               verticalAlignment = Alignment.CenterVertically
                           ) {
                               Checkbox(
                                   checked = medicinesState2.value,
                                   onCheckedChange = { medicinesState2.value = it },
                                   colors = CheckboxDefaults.colors(
                                       uncheckedColor = MaterialTheme.colors.stableBlack,
                                       checkedColor = PrimeColor,
                                       checkmarkColor = StableWhite,
                                       disabledIndeterminateColor = MaterialTheme.colors.stableBlack
                                   )
                               )
                               Text(
                                   text = namesMState2.value,
                                   style = TextStyle(color = MaterialTheme.colors.stableBlack)
                               )
                           }
                           Row(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .height(42.dp),
                               horizontalArrangement = Arrangement.Start,
                               verticalAlignment = Alignment.CenterVertically
                           ) {
                               Checkbox(
                                   checked = medicinesState3.value,
                                   onCheckedChange = { medicinesState3.value = it },
                                   colors = CheckboxDefaults.colors(
                                       uncheckedColor = MaterialTheme.colors.stableBlack,
                                       checkedColor = PrimeColor,
                                       checkmarkColor = StableWhite,
                                       disabledIndeterminateColor = MaterialTheme.colors.stableBlack
                                   )
                               )
                               Text(
                                   text = namesMState3.value,
                                   style = TextStyle(color = MaterialTheme.colors.stableBlack)
                               )
                           }
                       }
                   }
               }
               Spacer(modifier = Modifier.height(8.dp))
               Text(
                   modifier = Modifier.fillMaxWidth(),
                   fontWeight = FontWeight.Bold,
                   fontFamily = FontFamily.Serif,
                   fontSize = 18.sp,
                   text = "ملاحظة",
                   textAlign = TextAlign.Start
               )
               Spacer(modifier = Modifier.height(8.dp))
           }
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(width = 0.6.dp, color = MaterialTheme.colors.stableBlack),
                    color = MaterialTheme.colors.generalCardBG
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(62.dp)
                            .padding(16.dp, 0.dp, 16.dp, 8.dp),
                        value = note.value,
                        onValueChange = { note.value = it },
                        textStyle = TextStyle(textAlign = TextAlign.Start),
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = StableWhite, textColor = MaterialTheme.colors.stableBlack, cursorColor = PrimeColor, focusedIndicatorColor = PrimeColor,disabledTextColor = MaterialTheme.colors.stableBlack, placeholderColor = MaterialTheme.colors.stableBlack, disabledPlaceholderColor =MaterialTheme.colors.stableBlack ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus()})
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    modifier = Modifier.animateContentSize(
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = LinearOutSlowInEasing
                        )
                    ).fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(width = 0.6.dp, color = MaterialTheme.colors.stableBlack),
                    onClick = {
                        expandedState3.value = !(expandedState3.value)
                    },
                    color = MaterialTheme.colors.generalCardBG
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .weight(6f),
                                text = "موعد مراجعة",
                                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            IconButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .alpha(ContentAlpha.medium)
                                    .rotate(rotationState.value),
                                onClick = {
                                    expandedState3.value = !(expandedState3.value)
                                }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Drop-Down Arrow"
                                )
                            }
                        }

                        if (expandedState3.value) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "بعد",
                                    fontSize = 14.sp
                                )
                                TextField(
                                    modifier = Modifier
                                        .width(72.dp)
                                        .height(62.dp)
                                        .padding(horizontal = 12.dp),
                                    value = daysState.value,
                                    maxLines = 1,
                                    onValueChange = { daysState.value = it },
                                    textStyle = TextStyle(textAlign = TextAlign.Start),
                                    colors = TextFieldDefaults.textFieldColors(backgroundColor = StableWhite, textColor = MaterialTheme.colors.stableBlack, cursorColor = PrimeColor, focusedIndicatorColor = PrimeColor,disabledTextColor = MaterialTheme.colors.stableBlack, placeholderColor = MaterialTheme.colors.stableBlack, disabledPlaceholderColor =MaterialTheme.colors.stableBlack ),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number,
                                        imeAction = ImeAction.Done
                                    ),
                                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus()})
                                )
                                Text(
                                    text = "يوم",
                                    fontSize = 14.sp
                                )
                            }
                            if (daysState.value.toInt() > 7) {
                                errorDayState.value = true
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "أقصى عدد مسموح 9 أيام",
                                        fontSize = 14.sp,
                                        color = Color.Red
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(32.dp),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = reserveState1.value,
                                    onCheckedChange = { reserveState1.value = it },
                                    colors = CheckboxDefaults.colors(
                                        uncheckedColor = MaterialTheme.colors.stableBlack,
                                        checkedColor = PrimeColor,
                                        checkmarkColor = StableWhite,
                                        disabledIndeterminateColor = MaterialTheme.colors.stableBlack
                                    )
                                )
                                Text(
                                    text = "حجز",
                                    style = TextStyle(color = MaterialTheme.colors.stableBlack)
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Button(
                    onClick = {
                        if (!errorDayState.value) {
                            if (analysisState1.value) {
                                D_ANALYSIS_REQ += "," + namesState1.value
                            }
                            if (analysisState2.value) {
                                D_ANALYSIS_REQ += "," + namesState2.value
                            }
                            if (analysisState3.value) {
                                D_ANALYSIS_REQ += "," + namesState3.value
                            }
                            if (analysisState4.value) {
                                D_ANALYSIS_REQ += "," + namesState4.value
                            }
                            if (medicinesState1.value) {
                                D_MEDICINE_REQ += "," + namesMState1.value
                            }
                            if (medicinesState2.value) {
                                D_MEDICINE_REQ += "," + namesMState2.value
                            }
                            if (medicinesState3.value) {
                                D_MEDICINE_REQ += "," + namesMState3.value
                            }

                            D_NOTE = note.value
                            D_NEW_A_DAY = daysState.value.toInt()
                            D_ANALYSIS_RESULT = diagnosisResult.value

                            navController.navigate(Screen.LoadingNewDiagnosis.route)
                        }
                    } ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primeColor)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        textAlign = TextAlign.Center,
                        text = "حفظ التشخيص",
                        style = MaterialTheme.typography.h6,
                        color = StableWhite,
                        maxLines = 1
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun FirstDataCard(navController: NavHostController){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 0.6.dp, color = MaterialTheme.colors.stableBlack),
        color = MaterialTheme.colors.generalCardBG
    ) {
       Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(18.dp),
           horizontalAlignment = Alignment.Start,
           verticalArrangement = Arrangement.Top
       ) {
           Row(modifier = Modifier
               .fillMaxWidth()
               .height(34.dp),
           horizontalArrangement = Arrangement.Start) {
               Text(text = D_SERVICE_NAME, textAlign = TextAlign.Start, modifier = Modifier.weight(3f))
               Button(
                   onClick = {
                         navController.navigate(Screen.Loading.passAppointmentId(appointmentId = D_APPOINT_ID))
                   } ,
                   modifier = Modifier
                       .weight(2f)
                       .height(34.dp),
                   shape = RoundedCornerShape(20.dp),
                   colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primeColor)
               ){
                   Text(
                       modifier = Modifier
                           .fillMaxSize(),
                       textAlign = TextAlign.Center,
                       text = "إلغاء الحجز",
                       fontSize = 12.sp,
                       color = StableWhite,
                       maxLines = 1
                   )
               }
           }
           Text(text = D_PATIENT_NAME, textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
       }
    }
}

