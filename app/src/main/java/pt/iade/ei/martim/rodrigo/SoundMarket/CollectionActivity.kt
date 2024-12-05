package pt.iade.ei.martim.rodrigo.SoundMarket

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.BottomAppBar
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.HomeTopBar
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.SearchBar
import pt.iade.ei.martim.rodrigo.SoundMarket.viewmodel.NewReleasesCollectionViewModel
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.AlbumItem
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.util.query

class CollectionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val newReleasesCollectionViewModel: NewReleasesCollectionViewModel = viewModel()
            val albums by newReleasesCollectionViewModel.albums.collectAsState(initial = emptyList())
            var searchQuery by remember { mutableStateOf("") }  // Manage search query state

            // Fetch new releases when the screen is first loaded
            LaunchedEffect(Unit) {
                val token = "Bearer BQC_moOmtD4BSJhAZaJOfaxWmCv0Uh0c2LN_yc_mAfwJOdGo_AYcJK3raQOYG4hIsx_AZ_GK1gP-EYh4JRC1Wc02u9Fowh2kARy3TC9aWHxGujE7esc"
                newReleasesCollectionViewModel.fetchNewReleases(token)
            }

            CollectionScreen(
                albums = albums,
                query = searchQuery,  // Pass searchQuery to CollectionScreen
                onSearchQueryChange = { query -> searchQuery = query },  // Update search query state
                onAddAlbumClick = {
                    val intent = Intent(this, AddAlbumActivity::class.java)
                    startActivity(intent)
                },
                onGoToAlbumClick = {
                    val intent = Intent(this, AlbumActivity::class.java)
                    startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun CollectionScreen(
    albums: List<Album>,
    query: String,  // Use query passed from CollectionActivity
    onSearchQueryChange: (String) -> Unit,
    onAddAlbumClick: () -> Unit,
    onGoToAlbumClick: (Album) -> Unit
) {
    val filteredAlbums = albums.filter { it.name.contains(query, ignoreCase = true) } // Filter albums by query

    Scaffold(
        topBar = {
            HomeTopBar { iconClicked -> /* Handle clicks as before */ }
        },
        bottomBar = {
            BottomAppBar { iconClicked -> /* Handle clicks as before */ }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            // Pass the query and search change handler to the SearchBar
            SearchBar(onSearchQueryChanged = { newQuery ->
                onSearchQueryChange(newQuery) // Update the search query in CollectionActivity
            })

            if (filteredAlbums.isNotEmpty()) {
                LazyColumn(
                    contentPadding = PaddingValues(bottom = 56.dp)
                ) {
                    items(filteredAlbums) { album ->
                        AlbumItem(
                            album = album,
                            onGoToAlbumClick = { onGoToAlbumClick(album) }
                        )
                    }
                }
            } else {
                Text("Loading albums...")
            }
        }
    }
}


