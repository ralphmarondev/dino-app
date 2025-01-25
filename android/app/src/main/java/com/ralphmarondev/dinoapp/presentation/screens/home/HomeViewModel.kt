package com.ralphmarondev.dinoapp.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.dinoapp.data.RetrofitInstance
import com.ralphmarondev.dinoapp.data.model.Dino
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val dinoApi = RetrofitInstance.dinoApi
    private val _randomDino = MutableStateFlow(
        Dino(
            id = 1,
            name = "Tyrannosaurus",
            description = "One of the most famous dinosaurs, Tyrannosaurus rex (T. rex) was a fearsome carnivore with powerful jaws and sharp teeth. It lived during the Late Cretaceous period and could grow up to 40 feet long. Its name means \"Tyrant Lizard.\"",
            imageUrl = "https://scitechdaily.com/images/Tyrannosaurus-rex-Dinosaur.jpg",
            isFavorite = false
        )
    )
    val randomDino: StateFlow<Dino> = _randomDino

    private val _fetchedDino = MutableStateFlow(listOf(1))
    fun getRandomDino() {
        try {
            viewModelScope.launch {
                val dinosaur = dinoApi.getRandomDino()

                if (!_fetchedDino.value.contains(dinosaur.id) && !dinosaur.isFavorite) {
                    _randomDino.value = dinosaur
                    // add new dino id to list :>
                    _fetchedDino.value += dinosaur.id
                } else {
                    getRandomDino()
                }
            }
            Log.d("MainViewModel", "Random dino: ${_randomDino.value}")
        } catch (e: Exception) {
            Log.e("MainViewModel", "getRandomDino: ${e.message}")
        }
    }

    fun toggleIsFavorite() {
        val originalDino = _randomDino.value
        _randomDino.value = _randomDino.value.copy(isFavorite = !_randomDino.value.isFavorite)

        viewModelScope.launch {
            try {
                val response = dinoApi.updateDino(
                    _randomDino.value.id,
                    _randomDino.value
                )
                if (!response.isSuccessful) {
                    _randomDino.value = originalDino
                }
                Log.d("HomeViewModel", "toggleIsFavorite Success: ${response.body()}")
            } catch (e: Exception) {
                _randomDino.value = originalDino
                Log.e("HomeViewModel", "toggleIsFavorite Error: ${e.message}")
            }
        }
    }
}