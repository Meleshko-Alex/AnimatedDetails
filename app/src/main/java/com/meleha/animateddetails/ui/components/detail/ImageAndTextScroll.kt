package com.meleha.animateddetails.ui.components.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.meleha.animateddetails.domain.models.FlickrItem
import com.meleha.animateddetails.domain.models.FlickrMedia

@Composable
fun ImageAndTextScroll(
    modifier: Modifier = Modifier,
    item: FlickrItem
) {
    val scrollState = rememberScrollState()
    val imageAlpha = (scrollState.value / 1000f).coerceAtMost(0.7f)
    val placeHolder = rememberVectorPainter(image = Icons.Outlined.Clear)

    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = item.media.m,
            contentDescription = null,
            placeholder = placeHolder,
            contentScale = ContentScale.Crop,
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
                //repeat(3) To see the text scrolling in action
                text = prepareText(item).repeat(3),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

fun prepareText(item: FlickrItem): String {
    return "Title: ${item.title} \n" +
            "Link: ${item.link} \n " +
            "Published: : ${item.published} \n " +
            "Author: ${item.author} \n " +
            "Tags: ${item.tags} \n "
}

@Composable
@Preview(showBackground = true)
private fun ImageAndTextScrollPreview() {
    ImageAndTextScroll(
        item = FlickrItem(
            title = "",
            link = "",
            media = FlickrMedia(""),
            published = "",
            author = "",
            tags = ""
        )
    )
}