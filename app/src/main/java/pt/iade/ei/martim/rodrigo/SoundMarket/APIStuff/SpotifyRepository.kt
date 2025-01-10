package pt.iade.ei.martim.rodrigo.SoundMarket.repository

import android.util.Log
import pt.iade.ei.martim.rodrigo.SoundMarket.APIStuff.RetrofitClient
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album
import pt.iade.ei.martim.rodrigo.SoundMarket.models.NewReleasesResponse
import pt.iade.ei.martim.rodrigo.SoundMarket.network.SpotifyApiService
import retrofit2.Response

class SpotifyRepository {

    // Fetch new releases from Spotify API
    suspend fun getNewReleases(token: String, limit: Int): Response<NewReleasesResponse> {
        val response = RetrofitClient.api.getNewReleases(authorization = token, limit = limit)

        // Log the response to check if releaseDate is populated
        Log.d("SpotifyRepository", "API Response: ${response.body()}")

        return response
    }
    // Fetch albums based on genre
    suspend fun searchAlbums(token: String, genreName: String): Response<NewReleasesResponse> {
        val response = RetrofitClient.api.searchAlbums(
            authorization = token,
            query = genreName,
            type = "album",
            limit = 10
        )
        return response
    }

    suspend fun getAlbumById(albumId: String, authToken: String): Album? {
        val response = RetrofitClient.api.getAlbum(albumId, authToken)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }



}
