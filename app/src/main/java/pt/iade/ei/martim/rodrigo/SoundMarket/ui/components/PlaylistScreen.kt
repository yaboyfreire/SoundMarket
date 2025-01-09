package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlaylistScreen(
    playlistTitle: String,
    tracks: List<Pair<String, String>> // List of (trackName, trackImageUrl)
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Playlist Title
        Text(
            text = playlistTitle,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // LazyColumn for Track List
        LazyColumn {
            items(tracks.size) { index ->
                val track = tracks[index]
                TrackItem(trackName = track.first, trackImageUrl = track.second)
            }
        }
    }
}


@Preview
@Composable
fun PlaylistScreenPreview() {
    // Example data for the preview
    PlaylistScreen(
        playlistTitle = "Rock Classics",
        tracks = listOf(
            "Bohemian Rhapsody" to "https://link-to-image.com/track_image1.jpg",
            "Stairway to Heaven" to "https://link-to-image.com/track_image2.jpg",
            "Hotel California" to "https://link-to-image.com/track_image3.jpg"
        )
    )
}

