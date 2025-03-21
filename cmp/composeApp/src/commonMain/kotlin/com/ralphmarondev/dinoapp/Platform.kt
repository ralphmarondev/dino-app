package com.ralphmarondev.dinoapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform