package pt.iade.ei.martim.rodrigo.SoundMarket

import HorizontalCarousel
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import pt.iade.ei.martim.rodrigo.SoundMarket.APIStuff.AuthService
import pt.iade.ei.martim.rodrigo.SoundMarket.APIStuff.RetrofitClientSoundMarket
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album
import pt.iade.ei.martim.rodrigo.SoundMarket.models.User
import pt.iade.ei.martim.rodrigo.SoundMarket.models.ViewModels.ProfileViewModel
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.LoadingSpinner
import pt.iade.ei.martim.rodrigo.SoundMarket.utils.SessionManager
import pt.iade.ei.martim.rodrigo.SoundMarket.utils.decodeBase64ToBitmap


class ProfileViewActivity : ComponentActivity() {

    private val profileViewModel: ProfileViewModel by viewModels {
        ProfileViewModel.Factory(
            authService = RetrofitClientSoundMarket.instance.create(AuthService::class.java),
            sessionManager = SessionManager(this)
        )
    }

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize session manager
        sessionManager = SessionManager(this)

        // Load user profile in a coroutine scope
        lifecycleScope.launch {
            // Get the userId from sessionManager and ensure it's a Long
            val userId = sessionManager.getUserId()
            Log.d("ProfileViewActivity", "User ID retrieved: $userId")
            if (userId != null) {
                profileViewModel.loadUserProfile(userId.toLong())
            } else {
                Toast.makeText(this@ProfileViewActivity, "User not logged in", Toast.LENGTH_LONG).show()
            }
        }

        // Observe the user profile and loading state to update the UI
        lifecycleScope.launchWhenStarted {
            profileViewModel.loading.collect { isLoading ->
                if (isLoading) {
                    // Show loading spinner
                    setContent {
                        LoadingSpinner(isLoading = true)
                    }
                } else {
                    // Once loading is complete, display the profile screen
                    profileViewModel.user.collect { userProfile ->
                        userProfile?.let {
                            val dummyAlbums = listOf(
                                Album(
                                    albumType = "album",
                                    artists = listOf(Album.Artist(name = "Artist 1")),
                                    images = listOf(Album.Image(url = "https://via.placeholder.com/300")),
                                    name = "Album 1",
                                    release_date = "2024-01-01"
                                ),
                                Album(
                                    albumType = "album",
                                    artists = listOf(Album.Artist(name = "Artist 2")),
                                    images = listOf(Album.Image(url = "https://via.placeholder.com/300")),
                                    name = "Album 2",
                                    release_date = "2024-02-01"
                                )
                            )
                            setContent {
                                ProfileScreen(
                                    onClick = { navigateToEditProfileActivity() },
                                    albums = dummyAlbums,
                                    userProfile = it
                                )
                            }
                        } ?: run {
                            Toast.makeText(this@ProfileViewActivity, "User profile not found.", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun navigateToEditProfileActivity() {
        val intent = Intent(this, EditProfileActivity::class.java)
        startActivity(intent)
    }
}



@Composable
fun ProfileScreen(onClick: () -> Unit, albums: List<Album>, userProfile: User?) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // Profile Picture, Name, and Email
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clickable { onClick() },
                contentAlignment = Alignment.BottomEnd
            ) {
                // Use default placeholder image for profile picture
                Image(
                    painter = painterResource(id = R.drawable.account_circle), // Placeholder image
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                // Edit icon
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Profile Picture",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Use userProfile data or fallback to static data
            Text(userProfile?.userName ?: "Rodrigo Freire", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(userProfile?.email ?: "rfreire750@gmail.com", fontSize = 16.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // About Me Section
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Country: ", fontWeight = FontWeight.Bold)
                Text(userProfile?.country ?: "Edit to change your country") // Fallback country
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("About me", fontWeight = FontWeight.Bold)
            Text(userProfile?.aboutMe ?: "Edit to add your description")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Carousel Sections
        HorizontalCarousel(
            albums = albums,
            text = "Collection",
            onButtonClick = {
                val intent = Intent(context, CollectionActivity::class.java)
                context.startActivity(intent)
            }
        )

        HorizontalCarousel(
            albums = albums.take(1), // Example for Wishlist
            text = "Wishlist",
            onButtonClick = {

            }
        )

        HorizontalCarousel(
            albums = albums.take(1), // Example for Selling
            text = "Selling",
            onButtonClick = {

            }
        )
    }
}




@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    // Create dummy albums for the preview
    val UserProfile = User(
        id = 1,
        user_name = "Rodrigo Freire",
        gender = "Male",
        userName = "Rodrigo",
        birthdate = "11-07-2004",
        email = "rfreire@gmail.com",
        country = "Edit to add your country",
        userImage = null,
        aboutMe = "Ah e tal",
        password = "As"
    )
    val dummyAlbums = listOf(
        Album(
            albumType = "album",
            artists = listOf(Album.Artist(name = "Artist 1")),
            images = listOf(Album.Image(url = "https://via.placeholder.com/300")),
            name = "Album 1",
            release_date = "2024-01-01"
        ),
        Album(
            albumType = "album",
            artists = listOf(Album.Artist(name = "Artist 2")),
            images = listOf(Album.Image(url = "https://via.placeholder.com/300")),
            name = "Album 2",
            release_date = "2024-02-01"
        )
    )

    // Pass the dummy albums to ProfileScreen
    ProfileScreen(albums = dummyAlbums, userProfile = UserProfile, onClick = { /* Preview click handler */ })
}