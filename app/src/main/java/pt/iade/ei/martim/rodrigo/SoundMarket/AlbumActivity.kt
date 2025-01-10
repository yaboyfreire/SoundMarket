package pt.iade.ei.martim.rodrigo.SoundMarket

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.AlbumActions
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.AlbumCopiesButton
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.AlbumDetailsSection
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.AlbumTopBar
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.TrackListSection

class AlbumActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val albumName = intent.getStringExtra("ALBUM_NAME") ?: "Unknown Album"
        val albumImageUrl = intent.getStringExtra("ALBUM_IMAGE_URL") ?: ""
        val albumArtist = intent.getStringExtra("ALBUM_ARTIST") ?: "Unknown Artist"
        val albumTracks = intent.getStringExtra("ALBUM_TRACKS")?.split(",") ?: emptyList()

        setContent {
            AlbumScreen(
                albumName = albumName,
                albumImageUrl = albumImageUrl,
                albumArtist = albumArtist,
                albumTracks = albumTracks
            )
        }
    }
}

@Composable
fun AlbumScreen(
    albumName: String,
    albumImageUrl: String,
    albumArtist: String,
    albumTracks: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = albumName,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = rememberImagePainter(albumImageUrl),
            contentDescription = albumName,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Artist: $albumArtist",
            style = MaterialTheme.typography.subtitle1
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Tracks:",
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(8.dp))
        albumTracks.forEach { track ->
            Text(
                text = track,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AlbumScreenPreview() {
    AlbumScreen( albumName = "Sample Album",
        albumArtist = "Sample Artist",
        albumImageUrl = "https://example.com/sample.jpg",  // You can use a sample image URL or local image resource
        albumTracks = listOf("Track 1", "Track 2", "Track 3")
    )  // Provide a list of sample tracks)
}

