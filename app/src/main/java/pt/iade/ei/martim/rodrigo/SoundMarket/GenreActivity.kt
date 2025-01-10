package pt.iade.ei.martim.rodrigo.SoundMarket

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
    val context = LocalContext.current

    LaunchedEffect(genreId) {
        val token = "Bearer BQB3Vr7urJPhF1TH3ClnhAOVlZj-jA8lR-wG49TSPNJVAY_jLqo1rXTDrr6Q8mYPhllPAf2IOvWUXbzAZxjXHQGBJ5UniFkbtkCUaKlYHcAFH_MHilM" // Replace with your actual token
        if (token.isNotEmpty()) {
            val playlistId = getPlaylistIdForGenre(genreId)
            playlistViewModel.getPlaylist(token, playlistId)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Genre Title
        Text(
            text = genreName,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        // Playlist Tracks Carousel
        playlist?.let {
            Text(
                text = it.name,  // Display the playlist name
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TrackCarousel(
                tracks = it.tracks.items,
                onAlbumClick = { track ->
                    val intent = Intent(context, AlbumActivity::class.java).apply {
                        putExtra("ALBUM_NAME", track.album.name)
                        putExtra("ALBUM_IMAGE_URL", track.album.images.firstOrNull()?.url ?: "")
                        putExtra("ALBUM_ARTIST", track.album.artists.joinToString(", ") { it.name } ?: "")
                        putExtra("ALBUM_ID", track.album.id) // Pass the album ID for fetching tracks
                    }
                    context.startActivity(intent)
                }

            )
        }
            ?: Text("Loading playlist...") // Show loading text until the data is available
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
fun TrackCarousel(
    tracks: List<Playlist.Tracks.Item>,
    onAlbumClick: (Playlist.Tracks.Track) -> Unit // Callback for album clicks
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(tracks.size) { index ->
            val trackItem = tracks[index]
            TrackItem(track = trackItem.track, onClick = { onAlbumClick(trackItem.track) })
        }
    }
}


@Composable
fun TrackItem(track: Playlist.Tracks.Track, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(160.dp) // Set height to match width for a square card
            .clickable { onClick() }, // Trigger the callback when clicked
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp) // Optional: Add shadow for depth
    ) {
        val imageUrl = track.album.images.firstOrNull()?.url
        if (imageUrl != null) {
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = track.album.name, // Better to describe the album
                modifier = Modifier
                    .fillMaxSize(),  // Fill the entire card with the image
                contentScale = ContentScale.Crop  // Crop to fill the card without distortion
            )
        } else {
            // Placeholder if image is null
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No Image",
                    color = Color.White
                )
            }
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
