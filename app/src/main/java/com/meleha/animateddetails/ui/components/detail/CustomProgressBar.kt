package com.meleha.animateddetails.ui.components.detail

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import kotlin.math.roundToInt

/**
 * CustomProgressBar is a customizable progress bar with animation that allows the user to interact with
 * the indicator by dragging it horizontally. The bar displays two colors (for the left and right parts)
 * and supports dragging the indicator with automatic progress value updates.
 *
 * @param modifier The modifier to customize the appearance and position of the component.
 * @param heightInDp The height of the progress bar in dp. Defaults to 10.dp.
 * @param heightMultiplier The factor by which the height of the progress bar increases when pressed.
 * This is a multiplier that ranges from 1 to 5. Default is 1.
 * @param heightAnimationDurationMillis The duration of the animation when the height changes due to
 * pressing, in milliseconds. The value is clamped between 0 and 2000 milliseconds. Default is 500ms.
 * @param progress The current progress value ranging from 0.0 to 1.0. Defaults to 0f.
 * @param leftColor The color of the left part of the progress bar. Defaults to yellow.
 * @param rightColor The color of the right part of the progress bar. Defaults to blue.
 * @param indicatorColor The color of the movable indicator. Defaults to white.
 * @param cornerRadius The corner radius for the left and right progress parts. Defaults to 0.dp.
 * @param onDragEnd A callback function that triggers when dragging the indicator ends.
 * @param onDrag A callback function that triggers on each drag movement of the indicator.
 */
@Composable
fun CustomProgressBar(
    modifier: Modifier,
    heightInDp: Dp = 10.dp,
    heightMultiplier: Int = 1.coerceIn(1, 5),
    heightAnimationDurationMillis: Int = 500.coerceIn(0, 2000),
    progress: Float = 0f.coerceIn(0f, 1f),
    leftColor: Color = Color.Yellow,
    rightColor: Color = Color.Blue,
    indicatorColor: Color = Color.White,
    cornerRadius: Dp = 0.dp,
    onDragEnd: (Float) -> Unit,
    onDrag: (Float) -> Unit,
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var progressBarWidthInDp by remember { mutableStateOf(Dp(0f)) }
    var isPressed by remember { mutableStateOf(false) }

    // Animated height change when the progress bar is pressed
    val animatedHeight by animateDpAsState(
        targetValue = if (isPressed) heightInDp * heightMultiplier else heightInDp,
        animationSpec = tween(durationMillis = heightAnimationDurationMillis)
    )

    // Guideline percentage based on the position of the indicator
    val guidelinePercentage by remember {
        derivedStateOf {
            Dp(offsetX) / progressBarWidthInDp
        }
    }

    // Synchronize the position of the indicator with the progress value
    LaunchedEffect(progress) {
        offsetX = progress.coerceIn(0f, 1f).times(progressBarWidthInDp.value)
    }

    Box(
        modifier = modifier
            .height(animatedHeight)
            .clip(
                RoundedCornerShape(0.dp)
            )
            .onSizeChanged {
                progressBarWidthInDp = it.width.dp
            }
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()

                        when (event.type) {
                            PointerEventType.Press -> {
                                isPressed = true
                            }

                            PointerEventType.Release -> {
                                isPressed = false
                            }
                        }
                    }
                }
            }
    ) {

        // Constraint layout for positioning the progress and indicator
        val constraints = ConstraintSet {
            val leftBox = createRefFor("leftBox")
            val rightBox = createRefFor("rightBox")
            val indicator = createRefFor("indicator")

            val guideLine = createGuidelineFromStart(guidelinePercentage)

            constrain(leftBox) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(guideLine, margin = 5.dp)
                width = Dimension.fillToConstraints
                height = Dimension.preferredValue(40.dp)
            }

            constrain(rightBox) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(guideLine, margin = 10.dp)
                width = Dimension.fillToConstraints
                height = Dimension.preferredValue(40.dp)
            }

            constrain(indicator) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                height = Dimension.preferredValue(40.dp)
            }
        }

        ConstraintLayout(
            constraints,
            modifier = Modifier.fillMaxSize()
        ) {

            // Left progress part
            Box(
                modifier = Modifier
                    .layoutId("leftBox")
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 5.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 5.dp
                        )
                    )
                    .background(shape = RoundedCornerShape(cornerRadius), color = leftColor)
            )

            // Right progress part
            Box(
                modifier = Modifier
                    .layoutId("rightBox")
                    .clip(
                        RoundedCornerShape(
                            topStart = 5.dp,
                            topEnd = 0.dp,
                            bottomStart = 5.dp,
                            bottomEnd = 0.dp
                        )
                    )
                    .background(shape = RoundedCornerShape(cornerRadius), color = rightColor)
            )

            // Movable indicator
            Box(modifier = Modifier
                .layoutId("indicator")
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .width(5.dp)
                .background(shape = RoundedCornerShape(5.dp), color = indicatorColor)
                .pointerInput(Unit) {
                    detectDragGestures(onDragEnd = { onDragEnd(guidelinePercentage) }) { change, dragAmount ->
                        change.consume()
                        offsetX = (offsetX + dragAmount.x).coerceIn(0f, progressBarWidthInDp.value)
                        onDrag(guidelinePercentage)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomProgressBarPreview() {
    CustomProgressBar(
        Modifier.wrapContentSize(),
        heightMultiplier = 2,
        onDragEnd = { },
        onDrag = { }
    )
}