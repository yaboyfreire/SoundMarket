package pt.iade.ei.martim.rodrigo.SoundMarket

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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

class CollectionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set content with the NewReleasesCollectionViewModel
        setContent {
            val newReleasesCollectionViewModel: NewReleasesCollectionViewModel = viewModel()  // Get the NewReleasesCollectionViewModel
            val albums = newReleasesCollectionViewModel.albums.collectAsState().value  // Observe albums from the ViewModel

            // Fetch new releases when the screen is first loaded
            LaunchedEffect(Unit) {
                val token = "Bearer BQCZTMDjqgwiltJOA58ZErMPjBGKX9GzbBOSXTEOUFEG2CL3QA6Fna1US4upNcsF2WkTotv2e7Yb_rxN-KXfUiWaQ6cRHw7TE0norg-2UDiWijT05gw" // Replace with your actual token
                newReleasesCollectionViewModel.fetchNewReleases(token)
            }

            CollectionScreen(
                albums = albums,
                onSearchQueryChange = { query ->
                    // Handle search query if needed
                },
                onAddAlbumClick = {
                    // Redirect to AddAlbumActivity
                    val intent = Intent(this, AddAlbumActivity::class.java)
                    startActivity(intent)
                },
                onGoToAlbumClick = {
                    // Redirect to GoToAlbumActivity
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
    onSearchQueryChange: (String) -> Unit,
    onAddAlbumClick: () -> Unit,
    onGoToAlbumClick: (Album) -> Unit
) {
    val context = LocalContext.current

    // Log the albums list when the screen loads to check if releaseDate is null
    LaunchedEffect(albums) {
        albums.forEach {
            Log.d("CollectionActivity", "Album: ${it.name}, Release Date: ${it.release_date}")
        }
    }

    Scaffold(
        topBar = {
            HomeTopBar { iconClicked ->
                // Handle clicks as before
            }
        },
        bottomBar = {
            BottomAppBar { iconClicked ->
                // Handle clicks as before
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
        ) {
            // Search bar and albums list
            SearchBar(onSearchQueryChanged = onSearchQueryChange)

            if (albums.isNotEmpty()) {
                LazyColumn(
                    contentPadding = PaddingValues(bottom = 56.dp)
                ) {
                    items(albums) { album ->
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


@Preview(showBackground = true)
@Composable
fun PreviewCollectionScreen() {
    CollectionScreen(
        albums = listOf(),
        onSearchQueryChange = {},
        onAddAlbumClick = {},
        onGoToAlbumClick = {}
    )
}
