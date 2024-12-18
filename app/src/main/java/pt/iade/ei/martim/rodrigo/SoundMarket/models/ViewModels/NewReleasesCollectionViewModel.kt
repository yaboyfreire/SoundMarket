package pt.iade.ei.martim.rodrigo.SoundMarket.models.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album
import pt.iade.ei.martim.rodrigo.SoundMarket.repository.SpotifyRepository

class NewReleasesCollectionViewModel : ViewModel() {

    private val spotifyRepository = SpotifyRepository()

    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums: StateFlow<List<Album>> = _albums

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchNewReleases(token: String, limit: Int = 10) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null  // Reset error message on new request
            try {
                val response = spotifyRepository.getNewReleases(token, limit)
                if (response.isSuccessful) {
                    val newReleases = response.body()?.albums?.items ?: emptyList()

                    // Log the new releases to check for releaseDate
                    Log.d("NewReleasesViewModel", "New Releases: $newReleases")

                    _albums.value = newReleases  // Update the albums list
                } else {
                    _errorMessage.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Exception: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

