package pt.iade.ei.martim.rodrigo.SoundMarket

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import pt.iade.ei.martim.rodrigo.SoundMarket.models.API.AlbumRequestDTO
import pt.iade.ei.martim.rodrigo.SoundMarket.models.ViewModels.AlbumViewModel
import pt.iade.ei.martim.rodrigo.SoundMarket.utils.SessionManager
import pt.iade.ei.martim.rodrigo.SoundMarket.utils.TokenManager
import pt.iade.ei.martim.rodrigo.SoundMarket.network.SpotifyApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Response

class AddToCollectionFromAlbumActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the data passed via the Intent
        val albumSpotifyID = intent.getStringExtra("albumId") ?: ""
        val albumTitle = intent.getStringExtra("albumTitle") ?: ""
        val albumArtist = intent.getStringExtra("albumArtist") ?: ""
        val imageURL = intent.getStringExtra("imageURL") ?: ""

        // Pass the data to the AddToCollectionFromAlbumScreen composable
        setContent {
            AddToCollectionFromAlbumScreen(
                albumSpotifyID = albumSpotifyID,
                albumTitle = albumTitle,
                albumArtist = albumArtist,
                imageURL = imageURL

            )
            Log.d("AddToCollection", "Received Data: $albumSpotifyID, $albumTitle, $albumArtist, $imageURL")

        }
    }
}


@Composable
fun AddToCollectionFromAlbumScreen(
    albumSpotifyID: String,
    albumTitle: String,
    albumArtist: String,
    imageURL: String
) {
    var genre by remember { mutableStateOf("") }
    var format by remember { mutableStateOf("") }
    var condition by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    val context = LocalContext.current
    val albumViewModel: AlbumViewModel = viewModel()
    val sessionManager = SessionManager(context)

    val authToken = TokenManager.getToken(context) ?: ""
    val userId = sessionManager.getUserId()

    val formatOptions = listOf("Vinyl", "CD")
    val conditionOptions = listOf("New", "Very Good", "Good", "Used", "Damaged")

    var isFormatDropdownExpanded by remember { mutableStateOf(false) }
    var isConditionDropdownExpanded by remember { mutableStateOf(false) }
    var formatTextFieldSize by remember { mutableStateOf(Size.Zero) }
    var conditionTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val formatIcon = if (isFormatDropdownExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    val conditionIcon = if (isConditionDropdownExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    // Fetch album details from Spotify API (optional, as the data is already passed)
    val spotifyApiService = remember { SpotifyApiService.create() }

    LaunchedEffect(albumSpotifyID) {
        try {
            val response = spotifyApiService.getAlbumDetails(albumSpotifyID, "Bearer $authToken")
            if (response.isSuccessful) {
                val albumDetails = response.body()
                genre = albumDetails?.genres?.firstOrNull() ?: "Unknown Genre"
                Log.d("AddToCollection", "Album Details: $albumTitle, $albumArtist, $genre")
            } else {
                Log.e("AddToCollection", "Failed to fetch album details: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("AddToCollection", "Error fetching album details: ${e.message}")
        }
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Display the album's image if available
        if (imageURL.isNotEmpty()) {
            Image(painter = rememberImagePainter(imageURL), contentDescription = "Album Image", modifier = Modifier.size(200.dp))
            Log.d("AddToCollection", "Received Data: $albumSpotifyID, $albumTitle, $albumArtist, $imageURL")

        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            Text("Album: $albumTitle")
            Text("Artist: $albumArtist")

            Spacer(modifier = Modifier.height(16.dp))

            // Format Dropdown
            OutlinedTextField(
                value = format,
                onValueChange = { format = it },
                label = { Text("Format") },
                trailingIcon = {
                    Icon(
                        imageVector = formatIcon,
                        contentDescription = "Dropdown Icon",
                        Modifier.clickable { isFormatDropdownExpanded = !isFormatDropdownExpanded }
                    )
                },
                readOnly = true,
                modifier = Modifier.fillMaxWidth().onGloballyPositioned {
                    formatTextFieldSize = it.size.toSize()
                }
            )

            DropdownMenu(
                expanded = isFormatDropdownExpanded,
                onDismissRequest = { isFormatDropdownExpanded = false },
                modifier = Modifier.width(with(LocalDensity.current) { formatTextFieldSize.width.toDp() })
            ) {
                formatOptions.forEach { option ->
                    DropdownMenuItem(onClick = {
                        format = option
                        isFormatDropdownExpanded = false
                    }) {
                        Text(option)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Condition Dropdown
            OutlinedTextField(
                value = condition,
                onValueChange = { condition = it },
                label = { Text("Condition") },
                trailingIcon = {
                    Icon(
                        imageVector = conditionIcon,
                        contentDescription = "Dropdown Icon",
                        Modifier.clickable { isConditionDropdownExpanded = !isConditionDropdownExpanded }
                    )
                },
                readOnly = true,
                modifier = Modifier.fillMaxWidth().onGloballyPositioned {
                    conditionTextFieldSize = it.size.toSize()
                }
            )

            DropdownMenu(
                expanded = isConditionDropdownExpanded,
                onDismissRequest = { isConditionDropdownExpanded = false },
                modifier = Modifier.width(with(LocalDensity.current) { conditionTextFieldSize.width.toDp() })
            ) {
                conditionOptions.forEach { label ->
                    DropdownMenuItem(onClick = {
                        condition = label
                        isConditionDropdownExpanded = false
                    }) {
                        Text(label)
                    }
                }
            }

            Spacer(modifier = Modifier.height(250.dp))

            Button(
                onClick = {
                    if (userId != null) {
                        val albumDTO = AlbumRequestDTO(
                            albumSpotifyID = albumSpotifyID,
                            title = albumTitle,
                            artist = albumArtist,
                            genre = genre,
                            format = format,
                            condition = condition,
                            utilizadorId = userId,
                            imageURL = imageURL

                        )
                        albumViewModel.addAlbumToCollection(albumDTO, authToken)
                    } else {
                        Log.e("AddToCollection", "User ID is null, cannot add album to collection")
                    }
                },
                modifier = Modifier.width(200.dp).height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
            ) {
                Text("Add To Collection", color = Color.White)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAddToCollectionFromAlbum() {
    // Provide dummy values for the missing parameters
    AddToCollectionFromAlbumScreen(
        albumSpotifyID = "1a2b3c4d5e6f7g8h9i0j",
        albumTitle = "Sample Album",
        albumArtist = "Sample Artist",
        imageURL = "https://example.com/sample_image.jpg"
    )
}
