package com.ralphmarondev.data.database

import com.ralphmarondev.data.model.Dino
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object AppDatabase {

    object Dinos : Table() {
        val id = integer("id").autoIncrement()
        val name = varchar("name", 255)
        val description = text("description")
        val imageUrl = varchar("imageUrl", 255)
        val isFavorite = bool("isFavorite")
        val isDeleted = bool("isDeleted")

        override val primaryKey = PrimaryKey(id)
    }

    fun initDatabase() {
        try {
            Database.connect(
                url = "jdbc:postgresql://localhost:5432/dino_db",
                driver = "org.postgresql.Driver",
                user = "postgres",
                password = "postgres"
            )
            transaction {
                SchemaUtils.create(Dinos)
            }
        } catch (e: Exception) {
            println("Database initialization failed: ${e.message}")
        }
    }

    fun insertDino(dino: Dino) {
        try {
            transaction {
                Dinos.insert {
                    it[name] = dino.name
                    it[description] = dino.description
                    it[imageUrl] = dino.imageUrl
                    it[isFavorite] = dino.isFavorite
                    it[isDeleted] = dino.isDeleted
                }
            }
        } catch (e: Exception) {
            println("Failed to insert dino: ${e.message}")
        }
    }

    fun updateDino(id: Int, updatedDino: Dino): Int {
        return transaction {
            Dinos.update({ Dinos.id eq id }) {
                it[name] = updatedDino.name
                it[description] = updatedDino.description
                it[imageUrl] = updatedDino.imageUrl
                it[isFavorite] = updatedDino.isFavorite
                it[isDeleted] = updatedDino.isDeleted
            }
        }
    }

    fun getAllDinos(): List<Dino> {
        return transaction {
            Dinos.selectAll().map {
                Dino(
                    id = it[Dinos.id],
                    name = it[Dinos.name],
                    description = it[Dinos.description],
                    imageUrl = it[Dinos.imageUrl],
                    isFavorite = it[Dinos.isFavorite],
                    isDeleted = it[Dinos.isDeleted]
                )
            }
        }
    }

    fun getRandomDino(): Dino? {
        return transaction {
            Dinos.selectAll()
//                .orderBy(Dinos.id to SortOrder.ASC)
                .orderBy(Random())
                .limit(1)
                .map {
                    Dino(
                        id = it[Dinos.id],
                        name = it[Dinos.name],
                        description = it[Dinos.description],
                        imageUrl = it[Dinos.imageUrl],
                        isFavorite = it[Dinos.isFavorite],
                        isDeleted = it[Dinos.isDeleted]
                    )
                }
                .singleOrNull()
        }
    }

    fun getFavoriteDinos(): List<Dino> {
        return transaction {
            Dinos.select { Dinos.isFavorite eq true }
                .map {
                    Dino(
                        id = it[Dinos.id],
                        name = it[Dinos.name],
                        description = it[Dinos.description],
                        imageUrl = it[Dinos.imageUrl],
                        isFavorite = it[Dinos.isFavorite],
                        isDeleted = it[Dinos.isDeleted]
                    )
                }
        }
    }
}