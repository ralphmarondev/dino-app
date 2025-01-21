package com.ralphmarondev

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
//    io.ktor.server.netty.EngineMain.main(args)
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

@Suppress("unused")
fun Application.module() {
    configureSerialization()
    configureHTTP()
    configureRouting()
}