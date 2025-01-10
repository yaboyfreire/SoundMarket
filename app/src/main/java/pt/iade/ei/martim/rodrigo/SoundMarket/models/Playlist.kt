package pt.iade.ei.martim.rodrigo.SoundMarket.models

data class Playlist(
    val name: String,
    val tracks: Tracks
) {
    data class Tracks(
        val items: List<Item>
    ) {
        data class Item(
            val track: Track
        )

        data class Track(
            val id: String,
            val name: String,
            val album: Album,

        )

        data class Album(
            val name: String,
            val artists: List<Artist>, // Artists should be a list
            val images: List<Image>,
            val id : String
        )

        data class Artist(
            val name: String // Artist's name
        )

        data class Image(
            val url: String // URL of the album image
        )

    }
}
