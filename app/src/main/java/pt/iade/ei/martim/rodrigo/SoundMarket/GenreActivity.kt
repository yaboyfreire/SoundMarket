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
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Playlist

class GenreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
fun GenreScreen(genreId: String, genreName: String) {}
 /*  val playlistViewModel: PlaylistViewModel = viewModel()
   // val playlist = playlistViewModel.playlist.value
    val context = LocalContext.current

    LaunchedEffect(genreId) {
        val token = "Bearer BQAAKN2h3CVZbKV-BtfgqRxpK6olO2NlkuamUG3LC607IXzu3YFTuqefqnHlfEQ9d6GW0yzcPPMSBj2YsJyz9_hXwj_CyNI55aBOHs5_kJjOaVVPuk0"
        if (token.isNotEmpty()) {
            val playlistId = getPlaylistIdForGenre(genreId)
          //  playlistViewModel.getPlaylist(token, playlistId)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Genre Title Section
        Text(
            text = genreName,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        playlist?.let {
            // Playlist Name
            Text(
                text = it.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            // Tracks Carousel Section
            TrackCarousel(
                tracks = it.tracks.items,
                onAlbumClick = { track ->
                    val intent = Intent(context, AlbumActivity::class.java).apply {
                        putExtra("ALBUM_NAME", track.album.name)
                        putExtra("ALBUM_IMAGE_URL", track.album.images.firstOrNull()?.url ?: "")
                        putExtra("ALBUM_ARTIST", track.album.artists.joinToString(", ") { it.name })
                        putExtra("ALBUM_ID", track.album.id)
                    }
                    context.startActivity(intent)
                }
            )
        } ?: Text("Loading playlist...")
    }
}

fun getPlaylistIdForGenre(genreId: String): String {
    return when (genreId) {
        "1" -> "0NZnOJB6zenH9Q3CBcIhCD"
        "2" -> "0ZqUav1B3JM0PNEJBJaxQI"
        "3" -> "HipHopPlaylistId"
        "4" -> "EDMPlaylistId"
        "5" -> "JazzPlaylistId"
        "6" -> "ClassicalPlaylistId"
        "7" -> "CountryPlaylistId"
        "8" -> "RnBPlaylistId"
        "9" -> "ReggaePlaylistId"
        "10" -> "BluesPlaylistId"
        "11" -> "MetalPlaylistId"
        "12" -> "LatinPlaylistId"
        else -> "DefaultPlaylistId"
    }
}

@Composable
fun TrackCarousel(
    tracks: List<Playlist.Tracks.Item>,
    onAlbumClick: (Playlist.Tracks.Track) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
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
            .height(160.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        val imageUrl = track.album.images.firstOrNull()?.url
        if (imageUrl != null) {
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = track.album.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
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

@Preview(showBackground = true)
@Composable
fun PreviewGenreScreen() {
    GenreScreen(
        genreId = "1",
        genreName = "Rock"
    )
}
*/