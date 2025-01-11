package pt.iade.ei.martim.rodrigo.SoundMarket.models.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album
import pt.iade.ei.martim.rodrigo.SoundMarket.repository.SpotifyRepository
import retrofit2.HttpException

class SearchResultsViewModel : ViewModel() {

    private val spotifyRepository = SpotifyRepository()

    private val _searchResults = MutableStateFlow<List<Album>>(emptyList())
    val searchResults: StateFlow<List<Album>> = _searchResults

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun searchSpotify(token: String, title: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null  // Reset error message on new search
            try {
                val response = spotifyRepository.searchAlbumsByTitle(token, title)

                // Log the response body for debugging purposes
                Log.d("SearchResult", response.body().toString())

                if (response.isSuccessful) {
                    response.body()?.albums?.let { albumsResponse ->
                        _searchResults.value = albumsResponse.items ?: emptyList() // Convert API response to Album model
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "No error body"
                    Log.e("SearchResultsViewModel", "Error code: ${response.code()}, message: $errorBody")
                    _errorMessage.value = "Error: ${response.message()}"
                }
            } catch (e: HttpException) {
                _errorMessage.value = "HttpException: ${e.message()}"
                Log.e("SearchResultsViewModel", "HTTP Error: ${e.message()}")
            } catch (e: Exception) {
                _errorMessage.value = "Exception: ${e.message}"
                Log.e("SearchResultsViewModel", "General Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

}
