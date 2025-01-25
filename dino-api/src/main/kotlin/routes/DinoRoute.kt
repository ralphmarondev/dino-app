package com.ralphmarondev.routes

import com.ralphmarondev.data.database.AppDatabase
import com.ralphmarondev.data.model.Dino
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.randomDino() {
    get("/random-dino") {
        val randomDino = AppDatabase.getRandomDino()
        if (randomDino != null) {
            call.respond(HttpStatusCode.OK, randomDino)
        } else {
            call.respond(HttpStatusCode.NotFound, "No dino found")
        }
    }
    get("/dinosaurs") {
        val dinosaurs = AppDatabase.getAllDinos()
        call.respond(HttpStatusCode.OK, dinosaurs)
    }
    get("/favorite-dinos") {
        val favoriteDinos = AppDatabase.getFavoriteDinos()
        call.respond(HttpStatusCode.OK, favoriteDinos)
    }
    put("/update-dino/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()

        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Invalid ID")
            return@put
        }

        val updatedDino = call.receive<Dino>()
        val result = AppDatabase.updateDino(id, updatedDino)

        if (result > 0) {
            call.respond(HttpStatusCode.OK, "Dino updated successfully")
        } else {
            call.respond(HttpStatusCode.NotFound, "Dino not found.")
        }
    }
    post("/new-dino") {
        val dino = call.receive<Dino>()

        if (dino.name.isBlank()) {
            call.respond(HttpStatusCode.BadRequest, "Dino name cannot be empty.")
            return@post
        }

        AppDatabase.insertDino(dino)
        call.respond(HttpStatusCode.Created, "Dino created successfully!")
    }
}