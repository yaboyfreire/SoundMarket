package pt.iade.ei.martim.rodrigo.SoundMarket.models

// PlaylistResponse is a data class that maps the API response for a Playlist
data class PlaylistResponse(
    val name: String,        // Name of the playlist
    val tracks: Tracks      // Tracks inside the playlist
) {
    // Tracks object that contains a list of track items
    data class Tracks(
        val items: List<Item>  // A list of track items
    ) {
        // Item object for each track
        data class Item(
            val track: Track  // A single track
        )

        // Track object that contains track info
        data class Track(
            val id: String,     // ID of the track
            val name: String,   // Name of the track
            val album: Album    // Album information of the track
        )

        // Album object that contains the album info
        data class Album(
            val name: String,        // Album name
            val images: List<Image>  // List of images (for album cover)
        )

        // Image object for each album's image
        data class Image(
            val url: String   // URL of the image (album cover)
        )
    }
}
