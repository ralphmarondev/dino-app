package com.ralphmarondev

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello there, Ralph Maron Eda is here!")
        }
        get("/hello"){
            call.respondText("Hello there! I am working with Ktor REST API.")
        }
    }
}
