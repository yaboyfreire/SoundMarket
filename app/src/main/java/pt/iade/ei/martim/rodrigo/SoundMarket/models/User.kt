package pt.iade.ei.martim.rodrigo.SoundMarket.models

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    val user_name: String?,
    val userName: String?,
    val gender: String?,
    val birthdate: String?,
    val email: String?,
    val country: String?,
    val password: String?,
    val userImage: String?, // Keep it as a base64 String
    val aboutMe: String?
)
