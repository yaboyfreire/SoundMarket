package pt.iade.ei.martim.rodrigo.SoundMarket.repository

import android.util.Log
import pt.iade.ei.martim.rodrigo.SoundMarket.APIStuff.RetrofitClient
import pt.iade.ei.martim.rodrigo.SoundMarket.models.NewReleasesResponse
import pt.iade.ei.martim.rodrigo.SoundMarket.models.PlaylistResponse
import retrofit2.Response

class SpotifyRepository {

    // Fetch new releases from Spotify API
    suspend fun getNewReleases(token: String, limit: Int): Response<NewReleasesResponse> {
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

    // Fetch playlist details
    suspend fun getPlaylistDetails(token: String, playlistId: String): Response<PlaylistResponse> {
        return RetrofitClient.api.getPlaylistDetails(authorization = token, playlistId = playlistId)
    }
}
