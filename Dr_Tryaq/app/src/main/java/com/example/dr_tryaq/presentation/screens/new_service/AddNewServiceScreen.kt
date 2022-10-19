package com.example.dr_tryaq.presentation.screens.new_service

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dr_tryaq.R
import com.example.dr_tryaq.navigation.Screen
import com.example.dr_tryaq.presentation.screens.splash.SplashViewModel
import com.example.dr_tryaq.ui.theme.*
import com.example.dr_tryaq.util.Constants.New_Service_Name
import com.example.dr_tryaq.util.Constants.New_Service_Price


@SuppressLint("UnrememberedMutableState")
@Composable
fun AddNewServiceScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val departmentId by splashViewModel.departmentId.collectAsState()
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val focusManager =  LocalFocusManager.current
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.Services.passDepartmentId(departmentId = departmentId))
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
                            text = "إضافة خدمة للقسم",
                            color = MaterialTheme.colors.primeColor
                        )
                    }

                },
                backgroundColor = MaterialTheme.colors.topAppBarC
            )}
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, bottom = 0.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Surface(
            modifier = Modifier
                .size(200.dp),
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colors.secondColor
        ){

        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { btm ->
                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
        }
            Button(
                onClick = {
                    launcher.launch("image/*")
                } ,
                modifier = Modifier
                    .width(200.dp)
                    .height(42.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primeColor)
            ){
                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    textAlign = TextAlign.Center,
                    text = "+",
                    style = MaterialTheme.typography.h6,
                    color = StableWhite,
                    maxLines = 1
                )
            }

        Spacer(modifier = Modifier.height(24.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            value = name,
            onValueChange = { name = it },
            maxLines = 1,
            placeholder = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "اسم الخدمة",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.stableBlack
                )
            },
            textStyle = TextStyle(textAlign = TextAlign.Center),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = StableWhite, textColor = MaterialTheme.colors.stableBlack, cursorColor = PrimeColor, focusedIndicatorColor = PrimeColor,disabledTextColor = MaterialTheme.colors.stableBlack, placeholderColor = MaterialTheme.colors.stableBlack, disabledPlaceholderColor = MaterialTheme.colors.stableBlack ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            value = price,
            onValueChange = { price = it },
            maxLines = 1,
            placeholder = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "سعر الخدمة",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.stableBlack
                )
            },
            textStyle = TextStyle(textAlign = TextAlign.Center),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = StableWhite, textColor = MaterialTheme.colors.stableBlack, cursorColor = PrimeColor, focusedIndicatorColor = PrimeColor,disabledTextColor = MaterialTheme.colors.stableBlack, placeholderColor = MaterialTheme.colors.stableBlack, disabledPlaceholderColor = MaterialTheme.colors.stableBlack ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus()})
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = {
                New_Service_Name = name
                New_Service_Price = price

                navController.popBackStack()
                navController.navigate(Screen.LoadingNewService.route)
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
                text = "إضافة خدمة",
                style = MaterialTheme.typography.h6,
                color = StableWhite,
                maxLines = 1
            )
        }
    }
    }
}