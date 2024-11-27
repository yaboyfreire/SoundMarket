package pt.iade.ei.martim.rodrigo.SoundMarket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album
import pt.iade.ei.martim.rodrigo.SoundMarket.repository.SpotifyRepository

class NewReleasesCollectionViewModel : ViewModel() {

    private val spotifyRepository = SpotifyRepository()  // Assuming you have this

    // StateFlow to hold the albums list
    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums: StateFlow<List<Album>> = _albums

    // Fetch new releases from Spotify using the repository
    fun fetchNewReleases(token: String, limit: Int = 10) {
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
}

