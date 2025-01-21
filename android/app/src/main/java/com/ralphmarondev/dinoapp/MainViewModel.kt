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
    private val _randomDino = MutableStateFlow<Dino>(
        Dino(
            name = "",
            description = "",
            imageUrl = ""
        )
    )
    val randomDino: StateFlow<Dino> = _randomDino

    init {
        getRandomDino()
    }

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