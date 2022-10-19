package com.example.tryaq.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tryaq.R
import com.example.tryaq.ui.theme.homeTopIconBG
import com.example.tryaq.ui.theme.primeColor
import com.example.tryaq.ui.theme.topAppBarC

@Composable
fun HomeTopBar(
    onNotificationClicked: () -> Unit,
    onMenuClicked: () -> Unit
) {

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        navigationIcon = {
            IconButton(onClick = onMenuClicked) {
                Surface(
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp),
                    color = homeTopIconBG,
                    shape = RoundedCornerShape(10.dp),
                    elevation = 4.dp
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(4.dp),
                        imageVector = Icons.Default.Menu,
                        contentDescription = stringResource(R.string.search_icon),
                        tint = MaterialTheme.colors.primeColor
                    )
                }
        }
                         },
        title = {},
        backgroundColor = MaterialTheme.colors.topAppBarC,
        actions = {
            IconButton(onClick = onNotificationClicked) {
                Surface(
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp),
                    color = homeTopIconBG,
                    shape = RoundedCornerShape(10.dp),
                    elevation = 4.dp
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(4.dp),
                        imageVector = Icons.Default.Notifications,
                        contentDescription = stringResource(R.string.search_icon),
                        tint = MaterialTheme.colors.primeColor
                    )
                }
            }
        }
    )
}

@Composable
@Preview
fun HomeTopBarPreview() {
    HomeTopBar({}) {}
}
