package pt.iade.ei.martim.rodrigo.SoundMarket.network


import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album
import pt.iade.ei.martim.rodrigo.SoundMarket.models.NewReleasesResponse
import pt.iade.ei.martim.rodrigo.SoundMarket.models.PlaylistResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyApiService {

    @GET("v1/browse/new-releases")
    suspend fun getNewReleases(
        @Header("Authorization") authorization: String,
        @Query("limit") limit: Int = 4
    ): Response<NewReleasesResponse>

    @GET("v1/search")
    suspend fun searchAlbums(
        @Header("Authorization") authorization: String,
        @Query("q") query: String,
        @Query("type") type: String = "album",
        @Query("limit") limit: Int = 30,
        @Query("offset") offset: Int = 5
    ): Response<NewReleasesResponse>

    // Fetch albums by genre
    @GET("v1/browse/categories/{genre_id}/albums")
    suspend fun getAlbumsByGenre(
        @Header("Authorization") authorization: String,
        @Path("genre_id") genreId: String,
        @Query("limit") limit: Int = 5
    ): Response<NewReleasesResponse>


    @GET("v1/albums/{id}")
    suspend fun getAlbum(
        @Path("id") albumId: String,
        @Header("Authorization") authToken: String
    ): Response<Album>


    @GET("v1/playlists/{playlistId}")
    suspend fun getPlaylist(
        @Header("Authorization") authorization: String,
        @Path("playlistId") playlistId: String
    ): Response<PlaylistResponse>


    companion object {
        private const val BASE_URL = "https://api.spotify.com/v1/"

        fun create(): SpotifyApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(SpotifyApiService::class.java)
        }
    }

}
