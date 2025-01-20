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
        imageUrl = "https://scitechdaily.com/images/Tyrannosaurus-rex-Dinosaur.jpg"
    ),
    Dino(
        name = "Stegosaurus",
        description = "Known for its distinctive back plates and spiked tail (thagomizer), Stegosaurus was a herbivore from the Late Jurassic period. It was about 30 feet long and used its tail spikes for defense against predators.",
        imageUrl = "https://wallpaperaccess.com/full/4802592.jpg"
    ),
    Dino(
        name = "Brachiosaurus",
        description = "A massive herbivorous dinosaur with a long neck and front legs longer than its back legs, giving it a sloped posture. Brachiosaurus lived in the Late Jurassic period and fed on tall vegetation.",
        imageUrl = "https://i.pinimg.com/originals/8d/fe/52/8dfe520e865036e8431e02cc32d2301d.jpg"
    ),
    Dino(
        name = "Allosaurus",
        description = "A large carnivore of the Late Jurassic, Allosaurus was a fast and agile predator. It had sharp teeth and claws, making it a top predator of its time.",
        imageUrl = "https://vignette.wikia.nocookie.net/jurassicworld-evolution/images/7/77/Allosaurus.png/revision/latest?cb=20180701003327"
    ),
    Dino(
        name = "Apatosaurus",
        description = "A long-necked herbivore from the Late Jurassic, also known as Brontosaurus in some contexts. Apatosaurus weighed up to 40 tons and used its tail for defense against predators.",
        imageUrl = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/8b551cf7-d364-410e-93b4-f8a607cc9467/ddek1ik-0032b6b1-cea9-46f8-b415-59c39c138c75.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzhiNTUxY2Y3LWQzNjQtNDEwZS05M2I0LWY4YTYwN2NjOTQ2N1wvZGRlazFpay0wMDMyYjZiMS1jZWE5LTQ2ZjgtYjQxNS01OWMzOWMxMzhjNzUuanBnIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.j3BMyI5oGMCi5Tita7siuRPgi1fwYgZxU9b-d2OzJEg"
    ),
    Dino(
        name = "Diplodocus",
        description = "One of the longest dinosaurs, reaching up to 90 feet, Diplodocus lived in the Late Jurassic period. Its long neck and whip-like tail made it a unique herbivore.",
        imageUrl = "https://a-z-animals.com/media/2022/06/diplodocus-dinosaur.jpg"
    ),
    Dino(
        name = "Ankylosaurus",
        description = "A heavily armored herbivore with a club-like tail for defense. Ankylosaurus lived in the Late Cretaceous period and was covered in bony plates for protection against predators.",
        imageUrl = "https://www.thoughtco.com/thmb/_bC8KJG-KRbEjeNs2e_dsUEdUig=/3000x1697/filters:no_upscale():max_bytes(150000):strip_icc()/ankylosaurusWC-58b9abf45f9b58af5c907812.JPG"
    ),
    Dino(
        name = "Camarasaurus",
        description = "A large, long-necked sauropod from the Late Jurassic, Camarasaurus had a relatively short, robust neck and fed on vegetation close to the ground and in trees.",
        imageUrl = "https://cdn.britannica.com/83/141183-050-0E8DFCB7/Camarasaurus.jpg"
    ),
    Dino(
        name = "Irritatorsaurus",
        description = "A spinosaurid dinosaur with a crocodile-like snout, known for its ability to hunt fish. It lived during the Early Cretaceous period in what is now Brazil.",
        imageUrl = "https://mir-s3-cdn-cf.behance.net/project_modules/fs/f670f8115637533.605232e807991.jpg"
    ),
    Dino(
        name = "Mamenchisaurus",
        description = "Famous for its incredibly long neck, which made up about half its body length, Mamenchisaurus was a Late Jurassic sauropod that fed on high vegetation in forests.",
        imageUrl = "https://www.thoughtco.com/thmb/le6KbP0WooalvDYwyDOWUd7wCzo=/1500x1032/filters:fill(auto,1)/SKmamenchisaurus-56a2547b5f9b58b7d0c91d09.jpg"
    ),
    Dino(
        name = "Imissyousaurus",
        description = "A rare and endearing dinosaur that roams the land in search of affection. Known for its large, loving eyes and soft, comforting roar, Imissyousaurus is the perfect companion for those who feel a little lonely. It thrives on companionship and always manages to brighten up the day of anyone it meets.",
        imageUrl = "https://static.vecteezy.com/system/resources/previews/017/048/066/original/cute-chibi-dinosaur-illustration-dinosaur-kawaii-drawing-style-dinosaur-cartoon-vector.jpg"
    )
)

fun Route.randomDino() {
    get("/random-dino") {
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