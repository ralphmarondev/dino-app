package com.ralphmarondev.dinoapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.dinoapp.data.RetrofitInstance
import com.ralphmarondev.dinoapp.data.model.Dino
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val dinoApi = RetrofitInstance.dinoApi
    private val _randomDino = MutableStateFlow(
        Dino(
            name = "Tyrannosaurus",
            description = "One of the most famous dinosaurs, Tyrannosaurus rex (T. rex) was a fearsome carnivore with powerful jaws and sharp teeth. It lived during the Late Cretaceous period and could grow up to 40 feet long. Its name means \"Tyrant Lizard.\"",
            imageUrl = "https://scitechdaily.com/images/Tyrannosaurus-rex-Dinosaur.jpg"
        )
    )
    val randomDino: StateFlow<Dino> = _randomDino

    fun getRandomDino() {
        try {
            viewModelScope.launch {
                _randomDino.value = dinoApi.getRandomDino()
            }
        } catch (e: Exception) {
            Log.e("MainViewModel", "getRandomDino: ${e.message}")
        }
    }
}