package pt.iade.ei.martim.rodrigo.SoundMarket.APIStuff

import pt.iade.ei.martim.rodrigo.SoundMarket.models.API.AlbumRequestDTO
import pt.iade.ei.martim.rodrigo.SoundMarket.models.API.LoginRequestDTO
import pt.iade.ei.martim.rodrigo.SoundMarket.models.API.RegisterRequestDTO
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album
import pt.iade.ei.martim.rodrigo.SoundMarket.models.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import retrofit2.http.Path

class AuthServiceImpl(private val retrofit: Retrofit) : AuthService {

    private val authService = retrofit.create(AuthService::class.java)

    override  fun login(loginRequest: LoginRequestDTO) = authService.login(loginRequest)

    override  fun register(registerRequest: RegisterRequestDTO) = authService.register(registerRequest)

    override suspend fun getUserProfile(authToken: String, userId: String): User {
        return authService.getUserProfile(authToken, userId)
    }

    override suspend fun addAlbumToCollection(album: AlbumRequestDTO, token: String): Response<AlbumRequestDTO> {
        return authService.addAlbumToCollection(album, token)
    }

}

