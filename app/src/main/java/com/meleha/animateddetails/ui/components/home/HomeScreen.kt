package com.meleha.animateddetails.ui.components.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.meleha.animateddetails.domain.models.FlickrItem
import coil.compose.AsyncImage

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val flickrItems by viewModel.items.collectAsStateWithLifecycle()

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(6.dp)) {
        flickrItems.forEach { (category, list) ->
            item {
                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = category.uppercase(), style = MaterialTheme.typography.titleLarge)
            }
            item {
                LazyRow(modifier = Modifier.padding(vertical = 8.dp)) {
                    items(list.size) { index ->
                        PhotoItem(list[index])
                    }
                }
            }
        }
    }
}

@Composable
fun PhotoItem(item: FlickrItem) {
    val placeHolder = rememberVectorPainter(image = Icons.Outlined.Clear)
    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(120.dp),

    ) {
        AsyncImage(
            model = item.media.m,
            contentDescription = null,
            placeholder = placeHolder,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .aspectRatio(1f / 1f)
        )
        Text(
            text = item.title,
            textAlign = TextAlign.Start,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
    }
}