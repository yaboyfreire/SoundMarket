package pt.iade.ei.martim.rodrigo.SoundwMarket.models

import pt.iade.ei.martim.rodrigo.SoundMarket.models.Tracks
import java.net.URI
import java.time.LocalDateTime

data class Item(
    var id: Int?,
    val title: String,
    val description: String,
    val cover: URI,
    val genre: String,
    val releaseDate: LocalDateTime,
    val duration: Int,
    val rating : Float,
    val tracks: List<Tracks>
)

