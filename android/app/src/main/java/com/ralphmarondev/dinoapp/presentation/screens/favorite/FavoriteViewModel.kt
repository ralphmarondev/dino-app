package com.ralphmarondev.dinoapp.presentation.screens.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.dinoapp.data.RetrofitInstance
import com.ralphmarondev.dinoapp.data.model.Dino
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {
    private val dinoApi = RetrofitInstance.dinoApi

    private val _favoriteDinosaurs = MutableStateFlow<List<Dino>>(emptyList())
    val favoriteDinosaurs: StateFlow<List<Dino>> get() = _favoriteDinosaurs

    init {
        getFavoriteDinosaurs()
    }

    private fun getFavoriteDinosaurs() {
        try {
            viewModelScope.launch {
                val favoriteDinos = dinoApi.getFavoriteDinos()
                _favoriteDinosaurs.value += favoriteDinos
            }
        } catch (e: Exception) {
            Log.d("Favorite", "Error getting favorite dinosaurs: ${e.message}")
        }
    }
}