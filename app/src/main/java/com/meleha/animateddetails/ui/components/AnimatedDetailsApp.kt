package com.meleha.animateddetails.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.meleha.animateddetails.domain.models.FlickrItem
import com.meleha.animateddetails.ui.components.detail.DetailsScreen
import com.meleha.animateddetails.ui.components.home.HomeScreen
import com.meleha.animateddetails.ui.components.home.HomeViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AnimatedDetailsApp(modifier: Modifier, homeViewModel: HomeViewModel) {
    var showDetails by remember { mutableStateOf(false) }
    var selectedItem: FlickrItem? by remember { mutableStateOf(null) }

    SharedTransitionLayout {
        AnimatedContent(
            targetState = showDetails
        ) { targetState ->
            if (targetState) {
                BackHandler { showDetails = false }
                DetailsScreen(
                    modifier = modifier,
                    item = selectedItem!!,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this
                )
            } else {
                HomeScreen(
                    modifier = modifier,
                    homeViewModel,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this,
                    onItemClicked = { item ->
                        selectedItem = item
                        showDetails = true
                    }
                )
            }
        }
    }
}