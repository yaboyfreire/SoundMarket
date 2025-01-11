package pt.iade.ei.martim.rodrigo.SoundMarket.models.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    fun fetchNewReleases(token: String, limit: Int = 10) {
        viewModelScope.launch {
            try {
                val response = spotifyRepository.getNewReleases(token, limit)  // Call to the repository
                if (response.isSuccessful) {
                    val newReleases = response.body()?.albums?.items ?: emptyList()
                    _albums.value = newReleases  // Update the albums list
                } else {
                    // Log or handle unsuccessful response
                    Log.e("AlbumViewModel", "Error fetching new releases: ${response.message()}")
                }
            } catch (e: Exception) {
                // Handle network or other errors
                Log.e("AlbumViewModel", "Exception in fetchNewReleases", e)
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
                    // Log or handle unsuccessful response
                    Log.e("AlbumViewModel", "Error fetching albums by genre: ${response.message()}")
                }
            } catch (e: Exception) {
                // Handle network or other errors
                Log.e("AlbumViewModel", "Exception in fetchAlbumsByGenre", e)
            }
        }
    }

    private val _album = MutableLiveData<Album?>()
    val album: MutableLiveData<Album?> get() = _album

    fun fetchAlbum(albumId: String, authToken: String) {
        viewModelScope.launch {
            try {
                val fetchedAlbum = spotifyRepository.getAlbumById(albumId, authToken)
                if (fetchedAlbum != null) {
                    _album.value = fetchedAlbum  // Only assign if the album is non-null
                } else {
                    // Handle the case where the album is null
                    Log.e("AlbumViewModel", "Album not found for ID: $albumId")
                }
            } catch (e: Exception) {
                // Handle errors like network issues or parsing errors
                Log.e("AlbumViewModel", "Exception in fetchAlbum", e)
            }
        }
    }



}
