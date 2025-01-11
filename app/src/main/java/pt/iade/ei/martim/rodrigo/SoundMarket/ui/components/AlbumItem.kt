package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import pt.iade.ei.martim.rodrigo.SoundMarket.AlbumActivity
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album

@Composable
fun AlbumItem(album: Album, onGoToAlbumClick: () -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(16.dp)) // Add rounded corners
            .height(90.dp)
            .clickable {
                // Launch the AlbumActivity
                val intent = Intent(context, AlbumActivity::class.java).apply {
                    putExtra("ALBUM_ID", album.id) // Pass album details
                    putExtra("ALBUM_NAME", album.name)
                }
                context.startActivity(intent)
            }
    ) {
        Row {
            // Use Coil to load the album image dynamically
            val imageUrl = album.images.firstOrNull()?.url ?: "" // Replace with the correct URL property from your Album model
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = "Album Image",
                modifier = Modifier
                    .size(90.dp)
                    .padding(10.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                // Title with one line and ellipsis if too long
                Text(
                    text = album.name,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1, // Limit to 1 line
                    overflow = TextOverflow.Ellipsis, // "..." when text overflows
                    modifier = Modifier.padding(top = 25.dp)
                )

                // Artist and year on one line, no overflow
                Text(
                    text = "${album.artists.firstOrNull()?.name ?: "Unknown Artist"} • ${album.release_date}",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumItemPreview() {
    AlbumItem(
        album = Album(
            albumType = "album",
            artists = listOf(Album.Artist("spotify_url", "href", "id", "Test Artist", "artist", "uri")),
            availableMarkets = listOf("US", "UK"),
            externalUrls = Album.ExternalUrls("spotify_url"),
            href = "album_href",
            id = "album_id",
            images = listOf(Album.Image(200, "https://via.placeholder.com/90", 200)),
            name = "Test Album",
            release_date = "2023",
            release_date_precision = "day",
            total_tracks = 10,
            type = "album",
            uri = "album_uri"
        ),
        onGoToAlbumClick = {}
    )
}
