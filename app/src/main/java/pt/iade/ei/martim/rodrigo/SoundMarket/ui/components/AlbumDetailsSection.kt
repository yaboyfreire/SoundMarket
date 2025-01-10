package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import pt.iade.ei.martim.rodrigo.SoundMarket.R
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun AlbumDetailsSection(album: Album) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Display album cover image
        album.images.firstOrNull()?.let { image ->
            Image(
                painter = rememberImagePainter(image.url), // Use Coil for loading image from URL
                contentDescription = "Album Cover",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Display album name, artist, and release year
        Column {
            Text(
                text = album.name,
                fontWeight = FontWeight.Bold,
                maxLines = 1, // Limit to one line
                overflow = TextOverflow.Ellipsis // Add "..." if overflow
            )
            album.artists.firstOrNull()?.let { artist ->
                Text(
                    text = "${artist.name} • ${album.release_date.take(4)}",
                    maxLines = 2, // Limit to one line
                    overflow = TextOverflow.Ellipsis // Add "..." if overflow
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Display album rating and "SoundRating"
        Surface(
            color = Color(0xFF4CAF50),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "8.7/10", color = Color.Black) // You can dynamically update the rating later
                Text(text = "SoundRating", color = Color.Black,maxLines = 1, // Limit to one line
                    overflow = TextOverflow.Ellipsis) // Add "..." if overflow)

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumDetailsSectionPreview() {
    // Preview with mocked data
    val mockAlbum = Album(
        name = "Swimming",
        release_date = "2018-09-14",
        artists = listOf(Album.Artist(name = "Mac Miller")),
        images = listOf(Album.Image(url = "https://example.com/album_cover.jpg", height = 250, width = 250))
    )
    AlbumDetailsSection(album = mockAlbum)
}

