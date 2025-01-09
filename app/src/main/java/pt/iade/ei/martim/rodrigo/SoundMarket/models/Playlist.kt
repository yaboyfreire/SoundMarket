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
            val album: Album
        )

        data class Album(
            val name: String,
            val images: List<Image>
        )

        data class Image(
            val url: String
        )
    }
}
