package pt.iade.ei.martim.rodrigo.SoundMarket.repository

import pt.iade.ei.martim.rodrigo.SoundMarket.APIStuff.RetrofitClient
import pt.iade.ei.martim.rodrigo.SoundMarket.models.NewReleasesResponse
import retrofit2.Response

class SpotifyRepository {

    // Fetch new releases from Spotify API
    suspend fun getNewReleases(token: String, limit: Int): Response<NewReleasesResponse> {
        return RetrofitClient.api.getNewReleases(authorization = token, limit = limit)
    }
}
