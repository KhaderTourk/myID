package com.example.dr_tryaq.presentation.screens.logging

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.dr_tryaq.R
import com.example.dr_tryaq.navigation.Screen
import com.example.dr_tryaq.ui.theme.*
import com.example.dr_tryaq.util.Constants.DOCTOR_ID
import com.example.dr_tryaq.util.Constants.DOCTOR_PASSWORD


@ExperimentalCoilApi
@Composable
fun LoginScreen (
    navController: NavHostController,
    loggingViewModel: LoggingViewModel = hiltViewModel()
) {
    val focusManager =  LocalFocusManager.current
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isRememberChecked by remember { mutableStateOf(false) }


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .weight(4f))
        Image(
            modifier = Modifier.weight(12f),
            painter = rememberImagePainter(data = R.drawable.img_logo_1) {
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_placeholder) }, contentDescription = "")
        Image(
            modifier = Modifier.weight(10f),
            painter = rememberImagePainter(data = R.drawable.img_logo_2) {
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_placeholder) }, contentDescription = "")

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .weight(3f))

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(212.dp),
            shape = RoundedCornerShape(size = 20.dp),
            color = MaterialTheme.colors.secondColor
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(24.dp, 24.dp, 24.dp, 8.dp)){
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    value = id,
                    onValueChange = { id = it },
                    maxLines = 1,
                    placeholder = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "رقم الهوية",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.stableBlack
                        )
                    },
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = StableWhite, textColor = MaterialTheme.colors.stableBlack, cursorColor = PrimeColor, focusedIndicatorColor = PrimeColor,disabledTextColor = MaterialTheme.colors.stableBlack, placeholderColor = MaterialTheme.colors.stableBlack, disabledPlaceholderColor =MaterialTheme.colors.stableBlack ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp))
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    value = password,
                    onValueChange = { password = it },
                    maxLines = 1,
                    placeholder = {
                        Text(
                            modifier =
                            Modifier.fillMaxWidth(),
                            text = "كلمة المرور",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.stableBlack
                        )
                    },
                    textStyle = TextStyle(textAlign = TextAlign.Center, color = MaterialTheme.colors.stableBlack),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = StableWhite, textColor = MaterialTheme.colors.stableBlack, cursorColor = PrimeColor, focusedIndicatorColor = PrimeColor,disabledTextColor = MaterialTheme.colors.stableBlack, placeholderColor = MaterialTheme.colors.stableBlack, disabledPlaceholderColor =MaterialTheme.colors.stableBlack ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus()}),
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isRememberChecked,
                        onCheckedChange ={isRememberChecked = it},
                        colors = CheckboxDefaults.colors(uncheckedColor = MaterialTheme.colors.stableBlack,checkedColor = PrimeColor,checkmarkColor = StableWhite,disabledIndeterminateColor = MaterialTheme.colors.stableBlack)
                    )
                    Text(text = "تذكرني", style = TextStyle(color = MaterialTheme.colors.stableBlack))
                }
            }

        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .weight(7f))
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(22f)) {

            Button(
                onClick = {
                    DOCTOR_ID = if (id != "") id.toInt() else 54
                    DOCTOR_PASSWORD = password

                    navController.popBackStack()
                    loggingViewModel.saveRememberState(isChecked = isRememberChecked)
                    navController.navigate(Screen.LoadingLogin.route)
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
                    text = "تسجيل الدخول",
                    style = MaterialTheme.typography.h6,
                    color = StableWhite,
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(8.dp))
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .weight(5f))
    }

}
