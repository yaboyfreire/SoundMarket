package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TrackListSection(albumTracks: List<String>) {
    Column {
        Text(text = "TRACKLIST", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        albumTracks.forEach { track ->
            Text(text = track, modifier = Modifier.padding(vertical = 4.dp))
        }
    }
}


