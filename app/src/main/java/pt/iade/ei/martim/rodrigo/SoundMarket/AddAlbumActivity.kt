package pt.iade.ei.martim.rodrigo.SoundMarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.AlbumItem
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.SearchBar

class AddAlbumActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val defaultAlbums = listOf(
                Album(
                    albumType = "album",
                    artists = listOf(Album.Artist("https://spotify.com", "href", "1", "Kendrick Lamar", "artist", "uri")),
                    availableMarkets = listOf("US", "GB"),
                    externalUrls = Album.ExternalUrls("https://spotify.com"),
                    href = "https://api.spotify.com/v1/albums/1",
                    id = "1",
                    images = listOf(Album.Image(640, "https://link-to-image.jpg", 640)),
                    name = "To Pimp a Butterfly",
                    release_date = "2015-03-15",
                    release_date_precision = "day",
                    total_tracks = 16,
                    type = "album",
                    uri = "spotify:album:1"
                ),
                Album(
                    albumType = "album",
                    artists = listOf(Album.Artist("https://spotify.com", "href", "2", "Pink Floyd", "artist", "uri")),
                    availableMarkets = listOf("US", "GB"),
                    externalUrls = Album.ExternalUrls("https://spotify.com"),
                    href = "https://api.spotify.com/v1/albums/2",
                    id = "2",
                    images = listOf(Album.Image(640, "https://link-to-image2.jpg", 640)),
                    name = "The Wall",
                    release_date = "1979-11-30",
                    release_date_precision = "day",
                    total_tracks = 26,
                    type = "album",
                    uri = "spotify:album:2"
                )
                // Add more albums as needed
            )

            var filteredAlbums by remember { mutableStateOf(defaultAlbums) }

            AddAlbumScreen(
                albums = filteredAlbums,
                onSearchQueryChange = { query ->
                    filteredAlbums = if (query.isBlank()) {
                        defaultAlbums
                    } else {
                        defaultAlbums.filter { it.name.contains(query, ignoreCase = true) }
                    }
                }
            )
        }
    }
}

@Composable
fun AddAlbumScreen(
    albums: List<Album>,
    onSearchQueryChange: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Screen title
            Text(
                text = "Add an Album",
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Search bar
           /* SearchBar(
                onSearchQueryChanged = { query ->
                    searchQuery = query
                    onSearchQueryChange(query) // Update the parent with the query
                }
            )*/

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(albums) { album ->
                    AlbumItem(album, onGoToAlbumClick = {})
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddAlbumActivityPreview() {
    val defaultAlbums = listOf(
        Album(
            albumType = "album",
            artists = listOf(Album.Artist("https://spotify.com", "href", "1", "Kendrick Lamar", "artist", "uri")),
            availableMarkets = listOf("US", "GB"),
            externalUrls = Album.ExternalUrls("https://spotify.com"),
            href = "https://api.spotify.com/v1/albums/1",
            id = "1",
            images = listOf(Album.Image(640, "https://link-to-image.jpg", 640)),
            name = "To Pimp a Butterfly",
            release_date = "2015-03-15",
            release_date_precision = "day",
            total_tracks = 16,
            type = "album",
            uri = "spotify:album:1"
        ),
        Album(
            albumType = "album",
            artists = listOf(Album.Artist("https://spotify.com", "href", "2", "Pink Floyd", "artist", "uri")),
            availableMarkets = listOf("US", "GB"),
            externalUrls = Album.ExternalUrls("https://spotify.com"),
            href = "https://api.spotify.com/v1/albums/2",
            id = "2",
            images = listOf(Album.Image(640, "https://link-to-image2.jpg", 640)),
            name = "The Wall",
            release_date = "1979-11-30",
            release_date_precision = "day",
            total_tracks = 26,
            type = "album",
            uri = "spotify:album:2"
        )
        // Add more albums as needed...
    )
    var filteredAlbums by remember { mutableStateOf(defaultAlbums) }

    AddAlbumScreen(
        albums = filteredAlbums,
        onSearchQueryChange = { query ->
            filteredAlbums = if (query.isBlank()) {
                defaultAlbums
            } else {
                defaultAlbums.filter { it.name.contains(query, ignoreCase = true) }
            }
        }
    )
}
