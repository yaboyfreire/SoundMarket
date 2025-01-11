package pt.iade.ei.martim.rodrigo.SoundMarket.repository

import android.util.Log
import pt.iade.ei.martim.rodrigo.SoundMarket.APIStuff.RetrofitClient
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album
import pt.iade.ei.martim.rodrigo.SoundMarket.models.NewReleasesResponse
import pt.iade.ei.martim.rodrigo.SoundMarket.models.PlaylistResponse
import pt.iade.ei.martim.rodrigo.SoundMarket.network.SpotifyApiService



import retrofit2.Response

class SpotifyRepository {

    // Fetch new releases from Spotify API
    suspend fun getNewReleases(token : String, limit: Int): Response<NewReleasesResponse> {
        val response = RetrofitClient.api.getNewReleases(authorization = token, limit = limit)

        Log.d("SpotifyRepository", "API Response: ${response.body()}")

        return response
    }

    // Fetch albums based on genre
    suspend fun searchAlbums(token: String, genreName: String): Response<NewReleasesResponse> {
        return RetrofitClient.api.searchAlbums(
            authorization = token,
            query = genreName,
            type = "album",
            limit = 10
        )
    }

    suspend fun searchAlbumsByTitle(token: String, title: String): Response<NewReleasesResponse> {
        return RetrofitClient.api.searchAlbums(
            authorization = token,
            query = title,
            type = "album",
            limit = 10
        )
    }
    
    suspend fun getAlbumById(albumId: String, authToken: String): Album? {
        val response = RetrofitClient.api.getAlbum(albumId, authToken)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    private val apiService = SpotifyApiService.create()

    suspend fun fetchPlaylist(token: String, playlistId: String): PlaylistResponse {
        val response: Response<PlaylistResponse> = apiService.getPlaylist("Bearer $token", playlistId)
        Log.d("SpotifyRepository", "Request Token: Bearer $token")
        if (!response.isSuccessful) {
            val errorBody = response.errorBody()?.string()
            Log.e("SpotifyRepository", "Error response: $errorBody")
            throw Exception("Failed to fetch playlist: $errorBody")
        }
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        } else {
            throw Exception("Failed to fetch playlist: ${response.errorBody()?.string()}")
        }

    }

}
