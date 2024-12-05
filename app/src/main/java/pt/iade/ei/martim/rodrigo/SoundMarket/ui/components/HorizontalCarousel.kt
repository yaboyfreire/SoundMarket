import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.ButtonText
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album


@Composable
fun HorizontalCarousel(albums: List<Album>, text: String, onButtonClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(top = 15.dp, bottom = 5.dp) // Padding around the carousel
    ) {

        ButtonText(text = text, onClick = onButtonClick)  // onButtonClick passed here
        Spacer(modifier = Modifier.height(8.dp)) // Spacing between the ButtonText and the carousel

        LazyRow(
            contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(albums.size) { index ->
                CarouselItem(album = albums[index])
            }
        }
    }
}



@Composable
fun CarouselItem(album: Album) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(180.dp), // Adjusted height for album details
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = album.images.firstOrNull()?.url),
                contentDescription = album.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = album.name, style = MaterialTheme.typography.labelLarge, maxLines = 1)
            Text(
                text = album.artists.firstOrNull()?.name ?: "Unknown Artist",
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCarouselScreen() {
    // Dummy data for testing
    val dummyAlbums = listOf(
        Album(
            albumType = "album",
            artists = listOf(
                Album.Artist(
                    externalUrls = "https://spotify.com/artist1",
                    href = "https://api.spotify.com/v1/artists/artist1",
                    id = "artist1",
                    name = "Artist 1",
                    type = "artist",
                    uri = "spotify:artist:artist1"
                )
            ),
            availableMarkets = listOf("US", "GB", "CA"),
            externalUrls = Album.ExternalUrls("https://spotify.com/album1"), // Correct usage here
            href = "https://api.spotify.com/v1/albums/album1",
            id = "album1",
            images = listOf(
                Album.Image(300, "https://via.placeholder.com/300", 300),
                Album.Image(640, "https://via.placeholder.com/640", 640)
            ),
            name = "Album 1",
            release_date = "2024-01-01",
            release_date_precision = "day",
            total_tracks = 10,
            type = "album",
            uri = "spotify:album:album1"
        ),
        Album(
            albumType = "album",
            artists = listOf(
                Album.Artist(
                    externalUrls = "https://spotify.com/artist2",
                    href = "https://api.spotify.com/v1/artists/artist2",
                    id = "artist2",
                    name = "Artist 2",
                    type = "artist",
                    uri = "spotify:artist:artist2"
                )
            ),
            availableMarkets = listOf("US", "GB", "CA"),
            externalUrls = Album.ExternalUrls("https://spotify.com/album2"), // Correct usage here
            href = "https://api.spotify.com/v1/albums/album2",
            id = "album2",
            images = listOf(
                Album.Image(300, "https://via.placeholder.com/300", 300),
                Album.Image(640, "https://via.placeholder.com/640", 640)
            ),
            name = "Album 2",
            release_date = "2024-02-01",
            release_date_precision = "day",
            total_tracks = 12,
            type = "album",
            uri = "spotify:album:album2"
        )
    )

    // Pass the dummy albums to the HorizontalCarousel
    HorizontalCarousel(albums = dummyAlbums, text = "Trending", onButtonClick = {})
}
