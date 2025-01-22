package com.ralphmarondev.dinoapp.presentation.screens.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ralphmarondev.dinoapp.data.model.Dino
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoriteViewModel : ViewModel() {
    private val _favoriteDinosaurs = MutableStateFlow<List<Dino>>(emptyList())
    val favoriteDinosaurs: StateFlow<List<Dino>> get() = _favoriteDinosaurs

    init {
        getFavoriteDinosaurs()
    }

    private fun getFavoriteDinosaurs() {
        try {
            val dino = Dino(
                id = 1,
                name = "Tyrannosaurus",
                description = "One of the most famous dinosaurs, Tyrannosaurus rex (T. rex) was a fearsome carnivore with powerful jaws and sharp teeth. It lived during the Late Cretaceous period and could grow up to 40 feet long. Its name means \"Tyrant Lizard.\"",
                imageUrl = "https://scitechdaily.com/images/Tyrannosaurus-rex-Dinosaur.jpg"
            )
            _favoriteDinosaurs.value += dino
            val dino2 = Dino(
                id = 2,
                name = "Tyrannosaurus 2",
                description = "One of the most famous dinosaurs, Tyrannosaurus rex (T. rex) was a fearsome carnivore with powerful jaws and sharp teeth. It lived during the Late Cretaceous period and could grow up to 40 feet long. Its name means \"Tyrant Lizard.\"",
                imageUrl = "https://scitechdaily.com/images/Tyrannosaurus-rex-Dinosaur.jpg"
            )
            _favoriteDinosaurs.value += dino2
        } catch (e: Exception) {
            Log.d("Favorite", "Error getting favorite dinosaurs: ${e.message}")
        }
    }
}