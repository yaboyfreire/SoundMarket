package pt.iade.ei.martim.rodrigo.SoundMarket.APIStuff

import pt.iade.ei.martim.rodrigo.SoundMarket.models.API.LoginRequestDTO
import pt.iade.ei.martim.rodrigo.SoundMarket.models.API.RegisterRequestDTO
import pt.iade.ei.martim.rodrigo.SoundMarket.models.API.ResponseDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/auth/login")
    fun login(@Body loginRequest: LoginRequestDTO): Call<ResponseDTO>

    @POST("/auth/register")
    fun register(@Body registerRequest: RegisterRequestDTO): Call<ResponseDTO>
}