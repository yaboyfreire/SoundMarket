package pt.iade.ei.martim.rodrigo.SoundMarket

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album

@Composable
fun AlbumMetadata(album: Album) {
    // Displaying the release date and genre from the album
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Release Date", fontWeight = FontWeight.Bold)
            Text(
                text = album.release_date,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Genre", fontWeight = FontWeight.Bold)
            Text(
                text = album.genres.joinToString(", "),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumMetadataPreview() {
    // Sample album data for preview
    val album = Album(
        release_date = "August 3, 2018",
        genres = listOf("Hip Hop")
    )

    AlbumMetadata(album = album)
}
