package com.example.tryaq.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tryaq.navigation.Screen
import com.example.tryaq.ui.theme.primeColor
import com.example.tryaq.ui.theme.topAppBarC
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun HomeBottomBar(pages:  List<Screen>, pageState: PagerState) {
    val scope = rememberCoroutineScope()
    BottomAppBar(
        modifier = Modifier.height(56.dp),
        backgroundColor = MaterialTheme.colors.topAppBarC,
        contentPadding = PaddingValues(0.dp),
        elevation = 4.dp,
        content = {
            for (page in pages.indices) {
                BottomNavigationItem(
                    selected = page == pageState.currentPage,
                    onClick = {
                        scope.launch {
                            pageState.animateScrollToPage(page = page)
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = pages[page].icon),
                            contentDescription = pages[page].title,
                            tint = MaterialTheme.colors.primeColor
                        )
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = Color(0xFFD8D8D8),
                    label = {
                        Text(
                            text = pages[page].title,
                            color = MaterialTheme.colors.primeColor
                        )
                    },
                    alwaysShowLabel = false
                )
            }
        }
    )
}