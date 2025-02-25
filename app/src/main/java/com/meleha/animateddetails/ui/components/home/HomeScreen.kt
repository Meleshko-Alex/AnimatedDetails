package com.meleha.animateddetails.ui.components.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClicked: (FlickrItem) -> Unit
) {
    val flickrItems by viewModel.items.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {
        flickrItems.forEach { (category, list) ->
            item {
                Text(
                    modifier = modifier.padding(start = 6.dp),
                    text = category.uppercase(), style = MaterialTheme.typography.titleLarge
                )
            }
            item {
                LazyRow(modifier = modifier.padding(vertical = 8.dp)) {
                    items(list.size) { index ->
                        PhotoItem(
                            list[index],
                            sharedTransitionScope = sharedTransitionScope,
                            animatedVisibilityScope = animatedVisibilityScope,
                            onItemClicked
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PhotoItem(
    item: FlickrItem,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClicked: (FlickrItem) -> Unit
) {
    val placeHolder = rememberVectorPainter(image = Icons.Outlined.Clear)

    with(sharedTransitionScope) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .width(120.dp)
                .clickable { onItemClicked(item) },

            ) {
            AsyncImage(
                model = item.media.m,
                contentDescription = null,
                placeholder = placeHolder,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .aspectRatio(1f / 1f)
                    .sharedElement(
                        state = rememberSharedContentState(
                            key = item.published
                        ),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
            )
            Text(
                text = item.title,
                textAlign = TextAlign.Start,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}