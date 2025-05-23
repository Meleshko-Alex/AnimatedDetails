package com.meleha.animateddetails.ui.components.detail

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.meleha.animateddetails.R
import com.meleha.animateddetails.domain.models.FlickrItem

@OptIn(ExperimentalMotionApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsScreen(
    modifier: Modifier,
    item: FlickrItem,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    var isVisible by remember { mutableStateOf(false) }
    var progress by remember { mutableFloatStateOf(0f) }
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    with(sharedTransitionScope) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            ImageAndTextScroll(
                item = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = (progress * 0.5f).coerceIn(0f, 0.5f)))
                    .weight(1f)
                    .sharedElement(
                        state = rememberSharedContentState(key = item.published),
                        animatedVisibilityScope = animatedVisibilityScope,
                    )
            )

            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight },
                    animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight },
                    animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
                )
            ) {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {

                    MotionLayout(
                        modifier = modifier
                            .background(Color.LightGray)
                            .pointerInput(Unit) {
                                detectVerticalDragGestures { _, dragAmount ->
                                    progress = (progress - dragAmount / 1000f).coerceIn(0f, 1f)
                                }
                            },
                        motionScene = MotionScene(content = motionScene),
                        progress = progress
                    ) {
                        FullPlayerHeader(
                            modifier
                                .layoutId("header"),
                            onCloseClick = {
                                progress = 0f
                                isVisible = false
                            },
                            title = stringResource(R.string.an_audio_title),
                            description = stringResource(R.string.description)
                        )
                        AudioPlayerButtons(
                            modifier = modifier
                                .layoutId("player_btns")
                                .background(Color.LightGray)
                        )
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                                .background(Color.LightGray)
                                .layoutId("box")
                        )
                    }

                    CustomProgressBar(
                        modifier = modifier
                            .background(Color.LightGray)
                            .fillMaxWidth(),
                        heightInDp = 5.dp,
                        heightMultiplier = 2,
                        leftColor = Color.Black,
                        rightColor = Color.DarkGray,
                        onDragEnd = {},
                        onDrag = {}
                    )
                }
            }

            TwoClickableText(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray),
                onAudioClick = {
                    progress = 0f
                    isVisible = !isVisible
                },
                onHomeClick = { backDispatcher?.onBackPressed() }
            )
        }
    }
}