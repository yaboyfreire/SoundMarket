package pt.iade.ei.martim.rodrigo.SoundMarket.models.API

data class AlbumRequestDTO(
    val albumSpotifyID: String,
    val title: String,
    val artist: String,
    val genre: String?,
    val format: String,
    val condition: String,
    val utilizadorId : String,
    val imageURL: String

)
