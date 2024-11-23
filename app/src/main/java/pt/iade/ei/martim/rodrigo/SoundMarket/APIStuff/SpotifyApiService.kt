package pt.iade.ei.martim.rodrigo.SoundMarket.network

import pt.iade.ei.martim.rodrigo.SoundMarket.models.NewReleasesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SpotifyApiService {

    @GET("v1/browse/new-releases")
    suspend fun getNewReleases(
        @Header("Authorization") authorization: String,  // Pass Authorization header
        @Query("limit") limit: Int = 3
    ): Response<NewReleasesResponse>
}
