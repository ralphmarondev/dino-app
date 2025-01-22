package com.ralphmarondev.dinoapp.navigation

import kotlinx.serialization.Serializable

object Routes {
    @Serializable
    data object Home

    @Serializable
    data object Favorites

    @Serializable
    data object NewDinosaur
}