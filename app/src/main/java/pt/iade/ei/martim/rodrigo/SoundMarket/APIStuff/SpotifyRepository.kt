package pt.iade.ei.martim.rodrigo.SoundMarket.repository

import android.util.Log
import pt.iade.ei.martim.rodrigo.SoundMarket.APIStuff.RetrofitClient
import pt.iade.ei.martim.rodrigo.SoundMarket.models.NewReleasesResponse
import retrofit2.Response

class SpotifyRepository {

    // Fetch new releases from Spotify API
    suspend fun getNewReleases(token: String, limit: Int): Response<NewReleasesResponse> {
        val response = RetrofitClient.api.getNewReleases(authorization = token, limit = limit)

        // Log the response to check if releaseDate is populated
        Log.d("SpotifyRepository", "API Response: ${response.body()}")

        return response
    }
}

