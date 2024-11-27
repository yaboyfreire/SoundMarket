package pt.iade.ei.martim.rodrigo.SoundMarket

import android.content.Intent
import android.os.Bundle
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
                val token = "Bearer BQCi07hKO1eotGgMRqfUxWOFHXytiAc1q2j0zVWVblr1fkfPax8S3ATZ2MYEmAnKxp_ht6i_anHYCwsjQ1oYVYNdxTS6Q6mmmAX4bvw0CEjEQDIWxzM" // Replace with your actual token
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

    Scaffold(
        topBar = {
            HomeTopBar { iconClicked ->
                when (iconClicked) {
                    "account" -> {
                        val intent = Intent(context, ProfileViewActivity::class.java)
                        context.startActivity(intent)
                    }
                    "notifications" -> { /* Handle notifications icon click */ }
                    "settings" -> {
                        val intent = Intent(context, SettingsActivity::class.java)
                        context.startActivity(intent)
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar { iconClicked ->
                when (iconClicked) {
                    "home" -> { /* Handle home icon click */ }
                    "add" -> {
                        val intent = Intent(context, SellActivity::class.java)
                        context.startActivity(intent)
                    }
                    "email" -> {
                        val intent = Intent(context, InboxActivity::class.java)
                        context.startActivity(intent)
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
        ) {

            SearchBar(onSearchQueryChanged = onSearchQueryChange)

            // If albums are loaded, display them
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
