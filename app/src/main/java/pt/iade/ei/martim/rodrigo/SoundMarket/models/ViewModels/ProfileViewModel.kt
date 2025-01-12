package pt.iade.ei.martim.rodrigo.SoundMarket.models.ViewModels

import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.martim.rodrigo.SoundMarket.APIStuff.AuthService
import pt.iade.ei.martim.rodrigo.SoundMarket.utils.SessionManager
import pt.iade.ei.martim.rodrigo.SoundMarket.models.User

class ProfileViewModel(
    private val authService: AuthService,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    /**
     * Loads the user profile for the given userId.
     */
    fun loadUserProfile(userId: Long) {
        Log.d("ProfileViewModel", "Loading profile for userId: $userId")
        _loading.value = true  // Show loading indicator

        viewModelScope.launch {
            try {
                val authToken = sessionManager.getAuthToken()
                if (authToken != null) {
                    val tokenWithBearer = "Bearer $authToken"
                    Log.d("ProfileViewModel", "Authorization token: $tokenWithBearer")

                    // Make sure userId is passed as expected by your API
                    val response = authService.getUserProfile(tokenWithBearer, userId.toString())
                    Log.d("ProfileViewModel", "Response: $response")
                    if (response != null) {
                        _user.value = response // No need to decode here, leave Base64 string as is
                    } else {
                        _error.value = "Failed to load profile: No data received."
                    }
                } else {
                    _error.value = "Authorization token is missing."
                    Log.d("ProfileViewModel", "Authorization token missing")
                }
            } catch (e: Exception) {
                _error.value = "Failed to load profile: ${e.message}"
                Log.d("ProfileViewModel", "Error loading profile: ${e.message}")
            } finally {
                _loading.value = false  // Hide loading indicator
            }
        }
    }

    // Factory for creating the ViewModel
    class Factory(
        private val authService: AuthService,
        private val sessionManager: SessionManager
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProfileViewModel(authService, sessionManager) as T
        }
    }
}