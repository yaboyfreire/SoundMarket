package pt.iade.ei.martim.rodrigo.SoundMarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.*

class AlbumActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val albumName = intent.getStringExtra("ALBUM_NAME") ?: "Unknown Album"
        val albumArtist = intent.getStringExtra("ALBUM_ARTIST") ?: "Unknown Artist"
        val albumImageUrl = intent.getStringExtra("ALBUM_IMAGE_URL") ?: ""
        val albumTracks = intent.getStringExtra("ALBUM_TRACKS")?.split(",") ?: emptyList()
        val albumReleaseDate = intent.getStringExtra("ALBUM_RELEASE_DATE") ?: "Unknown Date"
        val albumGenre = intent.getStringExtra("ALBUM_GENRE") ?: "Unknown Genre"

        setContent {
            AlbumScreen(
                albumName = albumName,
                albumArtist = albumArtist,
                albumImageUrl = albumImageUrl,
                albumTracks = albumTracks,
                albumReleaseDate = albumReleaseDate,
                albumGenre = albumGenre
            )
        }
    }
}

@Composable
fun AlbumScreen(
    albumName: String,
    albumArtist: String,
    albumImageUrl: String,
    albumTracks: List<String>,
    albumReleaseDate: String,
    albumGenre: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AlbumTopBar()
        Spacer(modifier = Modifier.height(16.dp))

        AlbumDetailsSection(
            albumName = albumName,
            artistName = albumArtist,
            albumImageUrl = albumImageUrl
        )

        Spacer(modifier = Modifier.height(16.dp))
        AlbumActions()
        Spacer(modifier = Modifier.height(16.dp))

        TrackListSection(albumTracks = albumTracks)
        Spacer(modifier = Modifier.height(16.dp))

        AlbumMetadata(releaseDate = albumReleaseDate, genre = albumGenre)
        Spacer(modifier = Modifier.height(16.dp))

        AlbumCopiesButton()
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumScreenPreview() {
    AlbumScreen(
        albumName = "Sample Album",
        albumArtist = "Sample Artist",
        albumImageUrl = "https://example.com/sample.jpg",
        albumTracks = listOf("Track 1", "Track 2", "Track 3", "Track 4"),
        albumReleaseDate = "January 1, 2025",
        albumGenre = "Hip Hop"
    )
}
