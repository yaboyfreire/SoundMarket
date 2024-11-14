package pt.iade.ei.martim.rodrigo.SoundMarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.SearchBar


class CollectionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val albums = listOf(
                Album("Swimming", "Mac Miller", 2018),
                Album("Nevermind", "Nirvana", 1991),
                Album("DAMN.", "Kendrick Lamar", 2017),
                Album("Dark Side of the Moon", "Pink Floyd", 1973),
                Album("2014 Forest Hills Drive", "J. Cole", 2014),
                Album("My Beautiful Dark Twisted Fantasy", "Kanye West", 2010)
            )

            var filteredAlbums by remember { mutableStateOf(albums) }

            CollectionScreen(
                albums = filteredAlbums,
                onSearchQueryChange = { query ->
                    filteredAlbums = if (query.isBlank()) {
                        albums
                    } else {
                        albums.filter { it.title.contains(query, ignoreCase = true) }
                    }
                },
                onAddAlbumClick = {


                }
            )
        }
    }
}

@Composable
fun CollectionScreen(
    albums: List<Album>,
    onAddAlbumClick: () -> Unit,
    onSearchQueryChange: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()
        .padding(horizontal = 16.dp),) {
        Column {

            // Collection title
            Text(
                text = "Freire's Collection",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 16.dp,top = 16.dp).align(Alignment.CenterHorizontally)
            )

            SearchBar(onSearchQueryChanged = { query -> searchQuery = query })



            // Scrollable list of albums
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(albums) { album ->
                    AlbumItem(album)
                }
            }
        }

        // Floating action button (green button) for adding a new album
        FloatingActionButton(
            onClick = onAddAlbumClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            backgroundColor = Color.Green
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Album")
        }
    }
}

@Composable
fun AlbumItem(album: Album) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.LightGray)
            .height(90.dp)
    ) {
        Row(){

            Image(
                painter = painterResource(id = R.drawable.latina),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(90.dp).padding(10.dp),

            )
            Column(){
                Text(text = album.title, style = MaterialTheme.typography.h6,modifier=Modifier.padding(start=20.dp,top=25.dp))
                Text(text = "${album.artist} • ${album.year}", style = MaterialTheme.typography.body2,modifier=Modifier.padding(start=20.dp))
            }


        }


    }
}

@Preview(showBackground = true)
@Composable
fun CollectionActivityPreview() {
    val albums = listOf(
        Album("Swimming", "Mac Miller", 2018),
        Album("Nevermind", "Nirvana", 1991),
        Album("DAMN.", "Kendrick Lamar", 2017),
        Album("Dark Side of the Moon", "Pink Floyd", 1973),
        Album("2014 Forest Hills Drive", "J. Cole", 2014),
        Album("My Beautiful Dark Twisted Fantasy", "Kanye West", 2010)
    )
    var filteredAlbums by remember { mutableStateOf(albums) }

    CollectionScreen(
        albums = filteredAlbums,
        onSearchQueryChange = { query ->
            filteredAlbums = if (query.isBlank()) {
                albums
            } else {
                albums.filter { it.title.contains(query, ignoreCase = true) }
            }
        },
        onAddAlbumClick = {


        }
    )
}

data class Album(val title: String, val artist: String, val year: Int)

