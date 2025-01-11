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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import pt.iade.ei.martim.rodrigo.SoundMarket.R
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album

@Composable
fun AlbumDetailsSection(album: Album) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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
        Column(
            modifier = Modifier.weight(1f) // Use weight to allocate space for the text content
        ) {
            // Album Title with wrapping
            Text(
                text = album.name,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Clip,
                maxLines = 3 // Allow wrapping to two lines if needed
            )
            Spacer(modifier = Modifier.height(4.dp))
            // Artist Name and Year with wrapping
            album.artists.firstOrNull()?.let { artist ->
                Text(
                    text = "${artist.name} • ${album.release_date.take(4)}",
                    overflow = TextOverflow.Clip,
                    maxLines = 3 // Allow wrapping to two lines if needed
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Display album rating and "SoundRating"
        Surface(
            color = Color(0xFF4CAF50),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically) // Ensure it's vertically centered
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "8.7/10", color = Color.Black) // You can dynamically update the rating later
                Text(text = "SoundRating", color = Color.Black)
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

