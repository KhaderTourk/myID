package com.example.tryaq.presentation.screens.logging

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.tryaq.R
import com.example.tryaq.navigation.Screen
import com.example.tryaq.ui.theme.*
import com.example.tryaq.util.Constants.PATIENT_ID
import com.example.tryaq.util.Constants.PATIENT_PASSWORD

@ExperimentalCoilApi
@Composable
fun SignUpScreen (
    navController: NavHostController,
) {
    val focusManager =  LocalFocusManager.current
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .weight(3f))
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
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = StableWhite, textColor = MaterialTheme.colors.stableBlack, cursorColor = PrimeColor, focusedIndicatorColor = PrimeColor,disabledTextColor = MaterialTheme.colors.stableBlack, placeholderColor = MaterialTheme.colors.stableBlack, disabledPlaceholderColor = MaterialTheme.colors.stableBlack ),
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
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = StableWhite, textColor = MaterialTheme.colors.stableBlack, cursorColor = PrimeColor, focusedIndicatorColor = PrimeColor,disabledTextColor = MaterialTheme.colors.stableBlack, placeholderColor = MaterialTheme.colors.stableBlack, disabledPlaceholderColor = MaterialTheme.colors.stableBlack ),
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
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    value = confPassword,
                    onValueChange = { confPassword = it },
                    maxLines = 1,
                    placeholder = {
                        Text(
                            modifier =
                            Modifier.fillMaxWidth(),
                            text = "تأكيد كلمة المرور",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.stableBlack
                        )
                    },
                    textStyle = TextStyle(textAlign = TextAlign.Center, color = MaterialTheme.colors.stableBlack),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = StableWhite, textColor = MaterialTheme.colors.stableBlack, cursorColor = PrimeColor, focusedIndicatorColor = PrimeColor,disabledTextColor = MaterialTheme.colors.stableBlack, placeholderColor = MaterialTheme.colors.stableBlack, disabledPlaceholderColor = MaterialTheme.colors.stableBlack ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus()}),
                    visualTransformation = PasswordVisualTransformation()
                )
            }

        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .weight(3f))
        Text(text = errorMessage, color = Color.Red)
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(27f)) {

            Button(
                onClick = {
                    if (password == confPassword){
                        errorMessage = ""

                        PATIENT_ID = if (id != "") id.toInt() else 54
                        PATIENT_PASSWORD = password

                        navController.popBackStack()
                        navController.navigate(Screen.LoadingSignUp.route)
                    }else{
                        errorMessage = "كلمة المرور غير متطابقة"
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
                    text = "إنشاء",
                    style = MaterialTheme.typography.h6,
                    color = StableWhite,
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(8.dp))

            Button(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.Login.route)
                } ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondColor)
            ){
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    textAlign = TextAlign.Center,
                    text = "تسجيل الدخول",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.stableBlack,
                    maxLines = 1
                )
            }

        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .weight(5f))
    }

}

