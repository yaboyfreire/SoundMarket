package pt.iade.ei.martim.rodrigo.SoundMarket.APIStuff

import android.util.Base64
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album
import pt.iade.ei.martim.rodrigo.SoundMarket.models.User
import pt.iade.ei.martim.rodrigo.SoundMarket.models.UserAlbum

class UserRepository(private val soundMarketApiService: AuthService) {

    suspend fun getUserProfile(userId: String, authToken: String): User {
        // Fetch the user profile from the API
        val userProfile = soundMarketApiService.getUserProfile(authToken, userId)

        // Decode the base64 userImage to ByteArray
        val decodedImage = userProfile?.userImage?.let {
            Base64.decode(it, Base64.DEFAULT)
        }

        // Return a new User object with the decoded image
        return userProfile?.copy(userImage = decodedImage?.toString(Charsets.UTF_8)) ?: User(
            id = 1,
            user_name = "Rodrigo",
            userName = "Rodrigo Freire",
            gender = "Male",
            birthdate = "11-07-2004",
            email = "rfreire@gmail.com",
            country = "Portugal",
            password = "hashedPassword",
            userImage = null,
            aboutMe = null
        )
    }

}

