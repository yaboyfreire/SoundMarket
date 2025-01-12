package pt.iade.ei.martim.rodrigo.SoundMarket.models

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    @SerializedName("name") val user_name: String?,  // Maps JSON "name" to user_name
    val userName: String?,
    val gender: String?,
    val birthdate: String?,
    val email: String?,
    val country: String?,
    val password: String?,
    val userImage: String?,  // Base64 string
    val aboutMe: String?,
    val albums: List<UserAlbum>? = null
)

data class UserAlbum(
    val id: Int,
    val name: String,
    val condition: String,
    val format: String,
    val artist: String,
    val genre: String,
    @SerializedName("album_SpotifyID") val albumSpotifyID: String,  // Maps JSON "album_SpotifyID" to albumSpotifyID
    @SerializedName("album_added_date") val albumAddedDate: String,  // Maps JSON "album_added_date" to albumAddedDate
    val imageURL: String,
    val userId: Int  // Associated user
)