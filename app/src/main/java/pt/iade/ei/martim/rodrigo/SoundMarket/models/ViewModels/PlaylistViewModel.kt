package pt.iade.ei.martim.rodrigo.SoundMarket.models.ViewModels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.iade.ei.martim.rodrigo.SoundMarket.repository.SpotifyRepository
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Playlist
import pt.iade.ei.martim.rodrigo.SoundMarket.models.PlaylistResponse

class PlaylistViewModel : ViewModel() {

    private val spotifyRepository = SpotifyRepository() // Assuming this is your Spotify API repository

    private val _playlist = mutableStateOf<Playlist?>(null)
    val playlist: State<Playlist?> = _playlist

    // Function to map PlaylistResponse to Playlist
    private fun mapToPlaylist(response: PlaylistResponse): Playlist {
        // Map PlaylistResponse to Playlist
        val tracks = response.tracks.items.map {
            Playlist.Tracks.Item(
                track = Playlist.Tracks.Track(
                    id = it.track.id, // Make sure this is non-null and passed correctly
                    name = it.track.name,
                    album = Playlist.Tracks.Album(
                        id = it.track.id, // Pass album id here
                        name = it.track.album.name,
                        artists = it.track.album.artists.map { artist ->
                            Playlist.Tracks.Artist(name = artist.name) // Ensure the artists are mapped correctly
                        },
                        images = it.track.album.images.map { image ->
                            Playlist.Tracks.Image(url = image.url)
                        }
                    )
                )
            )


        }

        return Playlist(
            name = response.name,
            tracks = Playlist.Tracks(items = tracks)
        )
    }

    // Function to fetch playlist data from Spotify
    /*fun getPlaylist(token: String, playlistId: String) {
        viewModelScope.launch {
            try {
                val response = spotifyRepository.getPlaylistDetails(token, playlistId)

                if (response.isSuccessful) {
                    response.body()?.let { playlistResponse ->
                        _playlist.value = mapToPlaylist(playlistResponse)  // Convert PlaylistResponse to Playlist
                    }
                } else {
                    // Handle error if necessary
                }
            } catch (e: Exception) {
                // Handle error if necessary
            }
        }
    } */
}
