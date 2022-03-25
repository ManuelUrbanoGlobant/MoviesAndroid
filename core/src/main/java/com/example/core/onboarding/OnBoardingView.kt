package com.example.core.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.androidHelpers.compose.views.MovieLottieAnimation
import com.example.core.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(onGettingStartedClick:()->Unit) {

    val pagerState = rememberPagerState(initialPage = 0)

    Column {
        HorizontalPager(
            count = 2, state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            PageUI(page = getPages()[page])
        }

        HorizontalPagerIndicator(pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(dimensionResource(id = R.dimen.horizontal_pager_padding)),
            activeColor = Color.Black
        )

        AnimatedVisibility(visible = pagerState.currentPage == 1 ) {
            OutlinedButton(shape = RoundedCornerShape(dimensionResource(id = R.dimen.outlined_button_corner_shape)) ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.outlined_button_padding)),
                onClick = onGettingStartedClick,
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White)) {
                Text(text = stringResource(R.string.onboarding_continue))
            }
        }
    }
}

@Composable
fun PageUI(page: Page) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        MovieLottieAnimation(page.image,
            size = dimensionResource(id = R.dimen.lottie_animation_size))

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_large)))

        Text(
            text = page.title,
            fontSize = 28.sp, fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))

        Text(
            text = page.description,
            textAlign = TextAlign.Center, fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_medium)))

    }
}

@Preview
@Composable
private fun PreviewOnBoardingScreen() {
        PageUI(page = getPages()[0])
}

@Composable
fun getPages() = listOf(
    Page(
        stringResource(R.string.onboarding_page1_title),
        stringResource(R.string.onboarding_page1_description),
        R.raw.cinema_animation
    ),
    Page(
        stringResource(R.string.onboarding_page2_title),
        stringResource(R.string.onboarding_page2_description),
        R.raw.action_animation
    ),
)