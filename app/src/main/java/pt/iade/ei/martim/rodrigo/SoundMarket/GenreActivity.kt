package pt.iade.ei.martim.rodrigo.SoundMarket

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import pt.iade.ei.martim.rodrigo.SoundMarket.R
import pt.iade.ei.martim.rodrigo.SoundMarket.models.ViewModels.PlaylistViewModel
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Playlist

class GenreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get genreId and genreName from Intent
        val genreId = intent.getStringExtra("GENRE_ID") ?: ""
        val genreName = intent.getStringExtra("GENRE_NAME") ?: ""

        if (genreId.isEmpty() || genreName.isEmpty()) {
            Log.e("GenreActivity", "Genre ID or Genre Name missing!")
            return
        }

        setContent {
            GenreScreen(genreId = genreId, genreName = genreName)
        }
    }
}

@Composable
fun GenreScreen(genreId: String, genreName: String) {
    val playlistViewModel: PlaylistViewModel = viewModel()
    val playlist = playlistViewModel.playlist.value

    // Fetch the playlist when genreId changes
    LaunchedEffect(genreId) {
        val token = "Bearer BQAy5039FuyxfUTQFXV6ZYpgapYEOtDOjSzfkF3xLW0MSfi36Vx_vBu6PJf2y4HdUg3RSQILBdqFPm5opHRuc_obeMz734wUUz5r7Y7zauYbJ19ChYI" // Replace with your actual token

        // Check if the token is valid before making the request
        if (token.isNotEmpty()) {
            val playlistId = getPlaylistIdForGenre(genreId)
            playlistViewModel.getPlaylist(token, playlistId)
        } else {
            Log.e("GenreScreen", "Token is empty or invalid!")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Making the page scrollable
    ) {
        // Genre Name
        Text(
            text = genreName,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        // Display Playlist Data
        playlist?.let {
            Text(
                text = it.name ?: "Playlist",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(16.dp)
            )

            // Display Tracks
            TrackCarousel(tracks = it.tracks.items)
        } ?: Text("Loading playlist...") // Show loading text until the data is available
    }
}

fun getPlaylistIdForGenre(genreId: String): String {
    return when (genreId) {
        "1" -> "0NZnOJB6zenH9Q3CBcIhCD" // Replace with your actual playlist ID for Rock
        "2" -> "0ZqUav1B3JM0PNEJBJaxQI" // Replace with your actual playlist ID for Pop
        "3" -> "HipHopPlaylistId" // Replace with your actual playlist ID for Hip Hop
        "4" -> "EDMPlaylistId" // Replace with your actual playlist ID for EDM
        "5" -> "JazzPlaylistId" // Replace with your actual playlist ID for Jazz
        "6" -> "ClassicalPlaylistId" // Replace with your actual playlist ID for Classical
        "7" -> "CountryPlaylistId" // Replace with your actual playlist ID for Country
        "8" -> "RnBPlaylistId" // Replace with your actual playlist ID for R&B
        "9" -> "ReggaePlaylistId" // Replace with your actual playlist ID for Reggae
        "10" -> "BluesPlaylistId" // Replace with your actual playlist ID for Blues
        "11" -> "MetalPlaylistId" // Replace with your actual playlist ID for Metal
        "12" -> "LatinPlaylistId" // Replace with your actual playlist ID for Latin
        else -> "DefaultPlaylistId" // Default playlist if genreId doesn't match
    }
}

@Composable
fun TrackCarousel(tracks: List<Playlist.Tracks.Item>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(tracks.size) { index ->
            val trackItem = tracks[index]
            TrackItem(track = trackItem.track)
        }
    }
}

@Composable
fun TrackItem(track: Playlist.Tracks.Track) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(4.dp),
        shape = CircleShape
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val imageUrl = track.album.images.firstOrNull()?.url
            if (imageUrl != null) {
                Image(
                    painter = rememberImagePainter(imageUrl),
                    contentDescription = track.name,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.latina),
                    contentDescription = "Default image",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Text(
                text = track.name,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
        }
    }
}

// Preview Composable
@Preview(showBackground = true)
@Composable
fun PreviewGenreScreen() {
    GenreScreen(
        genreId = "1",
        genreName = "Rock" // Sample genre name
    )
}
