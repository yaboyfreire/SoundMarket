package pt.iade.ei.martim.rodrigo.SoundMarket.APIStuff


import pt.iade.ei.martim.rodrigo.SoundMarket.network.SpotifyApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.spotify.com/"

    val retrofitInstance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Gson for JSON to object conversion
            .build()
    }

    val api: SpotifyApiService by lazy {
        retrofitInstance.create(SpotifyApiService::class.java)
    }
}