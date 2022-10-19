package com.example.dr_tryaq.presentation.components


import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.dr_tryaq.R
import com.example.dr_tryaq.domain.model.models.Ad
import com.example.dr_tryaq.ui.theme.AD_ITEM_HEIGHT
import com.example.dr_tryaq.ui.theme.primeColor
import com.example.dr_tryaq.util.handleAdsResult
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue


@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun AutoSlidingAds(ads: LazyPagingItems<Ad>) {
    val result = handleAdsResult(ads = ads)
    if (result && ads.itemCount > 0) {
        var pagesCount = ads.itemCount
        if (pagesCount > 5) pagesCount = 5

        val pagerState = rememberPagerState(
            pageCount = pagesCount,
            initialOffscreenLimit = 6
        )

        LaunchedEffect(Unit) {
            while (true) {
                yield()
                delay(3000)
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                    animationSpec = tween(2000)
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .height(AD_ITEM_HEIGHT)
                .fillMaxWidth()
        ) { page ->
            Card(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    }
                    .fillMaxWidth()
            ) {
                val natural = ads[page]
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = rememberImagePainter(data = natural!!.image) {
                            placeholder(R.drawable.ic_placeholder)
                            error(R.drawable.ic_placeholder)
                        },
                        contentDescription = "Image",
                        contentScale = ContentScale.FillBounds,
                    )
            }


        }
        Column (
            modifier = Modifier.fillMaxWidth().padding(0.dp,8.dp,0.dp,0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
                ){
            //Horizontal dot indicator
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = MaterialTheme.colors.primeColor
            )
        }

    }
}

