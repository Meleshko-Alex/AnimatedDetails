package com.meleha.animateddetails.ui.components.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meleha.animateddetails.domain.models.FlickrItem
import com.meleha.animateddetails.domain.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RemoteRepository,
) : ViewModel() {

    private val _items = MutableStateFlow<Map<String, List<FlickrItem>>>(emptyMap())
    val items: StateFlow<Map<String, List<FlickrItem>>> = _items

    private val categories = listOf("dogs", "cats", "birds", "flowers")

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        categories.forEach { category ->
            viewModelScope.launch {
                repository.getFlickrItems(category)
                    .collect { list ->
                        _items.update { currentMap ->
                            currentMap + (category to list)
                        }
                    }
            }
        }
    }
}
