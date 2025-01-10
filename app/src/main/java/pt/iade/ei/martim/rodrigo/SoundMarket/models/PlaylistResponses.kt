package pt.iade.ei.martim.rodrigo.SoundMarket.models

data class PlaylistResponse(
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
            val album: Album
        )

        data class Album(
            val name: String,
            val artists: List<Artist>,
            val images: List<Image>
        )

        data class Artist(
            val name: String
        )

        data class Image(
            val url: String
        )
    }
}
