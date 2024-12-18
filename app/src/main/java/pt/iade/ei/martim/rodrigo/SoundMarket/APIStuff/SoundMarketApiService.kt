package pt.iade.ei.martim.rodrigo.SoundMarket.APIStuff

import pt.iade.ei.martim.rodrigo.SoundMarket.models.API.LoginRequestDTO
import pt.iade.ei.martim.rodrigo.SoundMarket.models.API.RegisterRequestDTO
import pt.iade.ei.martim.rodrigo.SoundMarket.models.API.ResponseDTO
import pt.iade.ei.martim.rodrigo.SoundMarket.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @POST("/auth/login")
    fun login(@Body loginRequest: LoginRequestDTO): Call<ResponseDTO>

    @POST("/auth/register")
    fun register(@Body registerRequest: RegisterRequestDTO): Call<ResponseDTO>

    @GET("user/{id}")
    suspend fun getUserProfile(
        @Header("Authorization") authToken: String,  // Move the header annotation here
        @Path("id") userId: String
    ): User
}

