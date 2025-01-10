package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album

@Composable
fun TrackListSection(tracks: List<Album.Track>) {
    Column(
        modifier = Modifier.padding(16.dp)  // Add padding around the entire section
    ) {
        Text(
            text = "TRACKLIST",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)  // Add spacing below the title
        )

        // Loop through the provided tracks list and display them
        tracks.forEach { track ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),  // Space between each track
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = track.name, modifier = Modifier.weight(1f))  // Track title
                Text(text = formatDuration(track.duration_ms))  // Track duration
            }
        }
    }
}

// Helper function to format track duration from milliseconds to minutes and seconds
fun formatDuration(ms: Int): String {
    val minutes = ms / 1000 / 60
    val seconds = (ms / 1000 % 60).toString().padStart(2, '0')
    return "$minutes:$seconds"
}

@Preview(showBackground = true)
@Composable
fun TrackListSectionPreview() {
    // Sample tracks for preview
    val tracks = listOf(
        Album.Track(name = "Come Back To Earth", duration_ms = 161000),
        Album.Track(name = "Hurt Feelings", duration_ms = 245000),
        Album.Track(name = "What's the Use?", duration_ms = 289000)
    )

    TrackListSection(tracks = tracks)
}
