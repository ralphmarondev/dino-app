package com.ralphmarondev.routes

import com.ralphmarondev.data.model.Dino
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// 10.0.2.2 => emulator
// local_ip => real device
private const val BASE_URL = "http://192.168.68.104:8080"
private val dinosaurs = listOf(
    Dino(
        name = "Tyrannosaurus",
        description = "One of the most famous dinosaurs, Tyrannosaurus rex (T. rex) was a fearsome carnivore with powerful jaws and sharp teeth. It lived during the Late Cretaceous period and could grow up to 40 feet long. Its name means \"Tyrant Lizard.\"",
        imageUrl = "$BASE_URL/dinos/tyrannosaurus.png"
    ),
    Dino(
        name = "Stegosaurus",
        description = "Known for its distinctive back plates and spiked tail (thagomizer), Stegosaurus was a herbivore from the Late Jurassic period. It was about 30 feet long and used its tail spikes for defense against predators.",
        imageUrl = "$BASE_URL/dinos/tyrannosaurus.png"
    ),
    Dino(
        name = "Brachiosaurus",
        description = "A massive herbivorous dinosaur with a long neck and front legs longer than its back legs, giving it a sloped posture. Brachiosaurus lived in the Late Jurassic period and fed on tall vegetation.",
        imageUrl = "$BASE_URL/dinos/tyrannosaurus.png"
    ),
    Dino(
        name = "Allosaurus",
        description = "A large carnivore of the Late Jurassic, Allosaurus was a fast and agile predator. It had sharp teeth and claws, making it a top predator of its time.",
        imageUrl = "$BASE_URL/dinos/tyrannosaurus.png"
    ),
    Dino(
        name = "Apatosaurus",
        description = "A long-necked herbivore from the Late Jurassic, also known as Brontosaurus in some contexts. Apatosaurus weighed up to 40 tons and used its tail for defense against predators.",
        imageUrl = "$BASE_URL/dinos/tyrannosaurus.png"
    ),
    Dino(
        name = "Diplodocus",
        description = "One of the longest dinosaurs, reaching up to 90 feet, Diplodocus lived in the Late Jurassic period. Its long neck and whip-like tail made it a unique herbivore.",
        imageUrl = "$BASE_URL/dinos/tyrannosaurus.png"
    ),
    Dino(
        name = "Ankylosaurus",
        description = "A heavily armored herbivore with a club-like tail for defense. Ankylosaurus lived in the Late Cretaceous period and was covered in bony plates for protection against predators.",
        imageUrl = "$BASE_URL/dinos/tyrannosaurus.png"
    ),
    Dino(
        name = "Camarasaurus",
        description = "A large, long-necked sauropod from the Late Jurassic, Camarasaurus had a relatively short, robust neck and fed on vegetation close to the ground and in trees.",
        imageUrl = "$BASE_URL/dinos/tyrannosaurus.png"
    ),
    Dino(
        name = "Irritatorsaurus",
        description = "A spinosaurid dinosaur with a crocodile-like snout, known for its ability to hunt fish. It lived during the Early Cretaceous period in what is now Brazil.",
        imageUrl = "$BASE_URL/dinos/tyrannosaurus.png"
    ),
    Dino(
        name = "Mamenchisaurus",
        description = "Famous for its incredibly long neck, which made up about half its body length, Mamenchisaurus was a Late Jurassic sauropod that fed on high vegetation in forests.",
        imageUrl = "$BASE_URL/dinos/tyrannosaurus.png"
    )
)

fun Route.randomDino() {
    get("/randomdino") {
        val randomDino = dinosaurs.random()
        call.respond(HttpStatusCode.OK, randomDino)
    }
    get("/dino") {
        call.respond(
            Dino(
                name = "Tyrannosaurus",
                description = "One of the most famous dinosaurs, Tyrannosaurus rex (T. rex) was a fearsome carnivore with powerful jaws and sharp teeth. It lived during the Late Cretaceous period and could grow up to 40 feet long. Its name means \"Tyrant Lizard.\"",
                imageUrl = "$BASE_URL/dinos/tyrannosaurus.png"
            )
        )
    }
}