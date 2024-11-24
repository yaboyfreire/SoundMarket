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
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.AlbumItem
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.SearchBar

class AddAlbumActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val defaultAlbums = listOf(
                Album("To Pimp a Butterfly", "Kendrick Lamar", 2015),
                Album("The Wall", "Pink Floyd", 1979),
                Album("The Eminem Show", "Eminem", 2002),
                Album("Lover", "Taylor Swift", 2019),
                Album("Good Kid, M.A.A.D City", "Kendrick Lamar", 2012),
                Album("1989", "Taylor Swift", 2014),
                Album("Thriller", "Michael Jackson", 1982)

            )

            var filteredAlbums by remember { mutableStateOf(defaultAlbums) }

            AddAlbumScreen(
                albums = filteredAlbums,
                onSearchQueryChange = { query ->
                    filteredAlbums = if (query.isBlank()) {
                        defaultAlbums
                    } else {
                        defaultAlbums.filter { it.title.contains(query, ignoreCase = true) }
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
            SearchBar(onSearchQueryChanged = { query ->
                searchQuery = query
                onSearchQueryChange(query)
            })

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
        Album("To Pimp a Butterfly", "Kendrick Lamar", 2015),
        Album("The Wall", "Pink Floyd", 1979),
        Album("The Eminem Show", "Eminem", 2002),
        Album("Lover", "Taylor Swift", 2019),
        Album("Good Kid, M.A.A.D City", "Kendrick Lamar", 2012),
        Album("1989", "Taylor Swift", 2014),
    )
    var filteredAlbums by remember { mutableStateOf(defaultAlbums) }

    AddAlbumScreen(
        albums = filteredAlbums,
        onSearchQueryChange = { query ->
            filteredAlbums = if (query.isBlank()) {
                defaultAlbums
            } else {
                defaultAlbums.filter { it.title.contains(query, ignoreCase = true) }
            }
        }
    )
}
