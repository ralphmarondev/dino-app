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
    private val preferences = MyApp.preferences
    private val _darkTheme = MutableStateFlow(preferences.isDarkTheme())
    val darkTheme: StateFlow<Boolean> get() = _darkTheme

    private val dinoApi = RetrofitInstance.dinoApi
    private val _randomDino = MutableStateFlow(
        Dino(
            id = 1,
            name = "Tyrannosaurus",
            description = "One of the most famous dinosaurs, Tyrannosaurus rex (T. rex) was a fearsome carnivore with powerful jaws and sharp teeth. It lived during the Late Cretaceous period and could grow up to 40 feet long. Its name means \"Tyrant Lizard.\"",
            imageUrl = "https://scitechdaily.com/images/Tyrannosaurus-rex-Dinosaur.jpg"
        )
    )
    val randomDino: StateFlow<Dino> = _randomDino

    private val _fetchedDino = MutableStateFlow(listOf(1))
    private val _count = MutableStateFlow(0)

    fun getRandomDino() {
        try {
            if (_count.value < 4) {
                viewModelScope.launch {
                    val dinosaur = dinoApi.getRandomDino()

                    if (!_fetchedDino.value.contains(dinosaur.id)) {
                        _randomDino.value = dinosaur
                        // add new dino id to list :>
                        _fetchedDino.value += dinosaur.id
                        _count.value++
                    } else {
                        getRandomDino()
                    }
                }
            } else {
                _randomDino.value = Dino(
                    id = -1,
                    name = "Imissyousaurus",
                    description = "This cute dinosaur misses you :< Roar!",
                    imageUrl = "android.resource://com.ralphmarondev.dinoapp/drawable/cute_me"
                )
            }
            Log.d("COUNT", "Count: ${_count.value}")
        } catch (e: Exception) {
            Log.e("MainViewModel", "getRandomDino: ${e.message}")
        }
    }

    fun toggleDarkTheme() {
        preferences.toggleDarkTheme()
        _darkTheme.value = preferences.isDarkTheme()
    }
}