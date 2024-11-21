package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TrackListSection() {
    Column {
        Text(text = "TRACKLIST", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        val tracks = listOf(
            "Come Back To Earth" to "2:41",
            "Hurt Feelings" to "4:05",
            "What's the Use?" to "4:49",
            "Perfecto" to "3:35",
            "Self Care" to "5:45",
            "Wings" to "4:10",
            "Ladders" to "4:47",
            "Small Worlds" to "4:31",
            "Conversation Pt. 1" to "3:31",
            "Dunno" to "3:57",
            "Jet Fuel" to "5:45",
            "2009" to "5:47",
            "So It Goes" to "5:31"
        )
        tracks.forEach { (title, time) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = title)
                Text(text = time)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrackListSectionPreview() {
    TrackListSection()
}
