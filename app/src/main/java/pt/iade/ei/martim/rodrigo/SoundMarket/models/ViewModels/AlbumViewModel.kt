package pt.iade.ei.martim.rodrigo.SoundMarket.models.ViewModels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album
import pt.iade.ei.martim.rodrigo.SoundMarket.repository.SpotifyRepository

class AlbumViewModel : ViewModel() {

    private val spotifyRepository = SpotifyRepository()  // Assuming you have this

    // State to hold the albums list
    private val _albums = mutableStateOf<List<Album>>(emptyList())
    val albums: State<List<Album>> = _albums

    // Fetch new releases from Spotify using the repository
    fun fetchNewReleases(token: String, limit: Int = 3) {
        viewModelScope.launch {
            try {
                val response = spotifyRepository.getNewReleases(token, limit)  // Call to the repository
                if (response.isSuccessful) {
                    val newReleases = response.body()?.albums?.items ?: emptyList()
                    _albums.value = newReleases  // Update the albums list
                } else {
                    // Handle unsuccessful response (e.g., show error)
                }
            } catch (e: Exception) {
                // Handle network or other errors
            }
        }
    }

    // Fetch albums based on a genre
    fun fetchAlbumsByGenre(token: String, genreName: String) {
        viewModelScope.launch {
            try {
                val response = spotifyRepository.searchAlbums(token, genreName)  // Call to the repository
                if (response.isSuccessful) {
                    val albumsByGenre = response.body()?.albums?.items ?: emptyList()
                    _albums.value = albumsByGenre  // Update the albums list
                } else {
                    // Handle unsuccessful response (e.g., show error)
                }
            } catch (e: Exception) {
                // Handle network or other errors
            }
        }
    }
}
