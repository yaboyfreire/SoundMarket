package pt.iade.ei.martim.rodrigo.SoundMarket.models.ViewModels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album
import pt.iade.ei.martim.rodrigo.SoundMarket.models.PlaylistResponse
import pt.iade.ei.martim.rodrigo.SoundMarket.repository.SpotifyRepository

class AlbumViewModel : ViewModel() {

    private val spotifyRepository = SpotifyRepository()  // Assuming you have this

    // State to hold the albums list
    private val _albums = mutableStateOf<List<Album>>(emptyList())
    val albums: State<List<Album>> = _albums

    // State to hold playlist details
    private val _playlist = mutableStateOf<PlaylistResponse?>(null)
    val playlist: State<PlaylistResponse?> = _playlist

    // Fetch new releases
    fun fetchNewReleases(token: String, limit: Int = 3) {
        viewModelScope.launch {
            try {
                val response = spotifyRepository.getNewReleases(token, limit)
                if (response.isSuccessful) {
                    val newReleases = response.body()?.albums?.items ?: emptyList()
                    _albums.value = newReleases
                } else {
                    // Handle error
                }
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }

    // Fetch albums by genre
    fun fetchAlbumsByGenre(token: String, genreName: String) {
        viewModelScope.launch {
            try {
                val response = spotifyRepository.searchAlbums(token, genreName)
                if (response.isSuccessful) {
                    val albumsByGenre = response.body()?.albums?.items ?: emptyList()
                    _albums.value = albumsByGenre
                } else {
                    // Handle error
                }
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }

    // Fetch playlist details
    fun fetchPlaylistDetails(token: String, playlistId: String) {
        viewModelScope.launch {
            try {
                val response = spotifyRepository.getPlaylistDetails(token, playlistId)
                if (response.isSuccessful) {
                    _playlist.value = response.body()
                } else {
                    // Handle error
                }
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }
}
