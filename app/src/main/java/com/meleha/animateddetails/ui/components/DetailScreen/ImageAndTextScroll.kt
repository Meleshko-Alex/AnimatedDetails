package com.meleha.animateddetails.ui.components.DetailScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meleha.animateddetails.R

@Composable
fun ImageAndTextScroll(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    val imageAlpha = (scrollState.value / 1000f).coerceAtMost(0.7f)

    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.play_circle),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .graphicsLayer(alpha = 1 - imageAlpha)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = 270.dp)
        ) {
            Text(
                text = "This is a long custom text for the scrolling effect."
                    .repeat(100),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ImageAndTextScrollPreview() {
    ImageAndTextScroll()
}