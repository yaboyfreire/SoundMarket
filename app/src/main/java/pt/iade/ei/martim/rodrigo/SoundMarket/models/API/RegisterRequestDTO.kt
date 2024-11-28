package pt.iade.ei.martim.rodrigo.SoundMarket.models.API

data class RegisterRequestDTO(
    val email: String,
    val password: String,
    val name: String,
    val gender: String,
    val userName: String,
    val country: String
)