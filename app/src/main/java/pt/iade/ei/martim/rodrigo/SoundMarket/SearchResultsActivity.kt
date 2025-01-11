package pt.iade.ei.martim.rodrigo.SoundMarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.AlbumItem
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.SearchBar
import pt.iade.ei.martim.rodrigo.SoundMarket.models.ViewModels.SearchResultsViewModel

class SearchResultsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val query = intent.getStringExtra("QUERY") ?: ""
        val token = intent.getStringExtra("TOKEN") ?: ""  // Get the token from intent

        setContent {
            val searchResultsViewModel: SearchResultsViewModel = viewModel()
            val searchResults by searchResultsViewModel.searchResults.collectAsState(initial = emptyList())
            var searchQuery by remember { mutableStateOf(query) }

            // Call the search function in the ViewModel when search query changes
            LaunchedEffect(Unit) {
                // Trigger search when the activity is created
                searchResultsViewModel.searchSpotify(token, searchQuery)
            }

            SearchResultsScreen(
                searchQuery = searchQuery,
                onSearchQueryChange = { query ->
                    searchQuery = query
                    searchResultsViewModel.searchSpotify(token, query) // Pass token and query to ViewModel
                },
                searchResults = searchResults,
                onGoToAlbumClick = { album ->
                    // Navigate to AlbumActivity with the album details
                },
                searchResultsViewModel = searchResultsViewModel, // Pass ViewModel to the Screen
                token = token  // Pass token to SearchResultsScreen
            )
        }
    }
}

@Composable
fun SearchResultsScreen(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    searchResults: List<Album>,
    onGoToAlbumClick: (Album) -> Unit,
    searchResultsViewModel: SearchResultsViewModel,
    token: String
) {
    val isLoading by searchResultsViewModel.isLoading.collectAsState() // Observe loading state

    Scaffold(
        topBar = {
            SearchBar(
                query = searchQuery,
                onSearchQueryChanged = { query ->
                    onSearchQueryChange(query)
                },
                onSearchAction = { query ->
                    searchResultsViewModel.searchSpotify(token, query) // Perform the search action with the token
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            // Show the loading spinner when data is being fetched
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator() // Show loading spinner
                }
            } else {
                // Show results if available, or a "No results found" message
                if (searchResults.isNotEmpty()) {
                    LazyColumn(
                        contentPadding = PaddingValues(bottom = 56.dp)
                    ) {
                        items(searchResults) { album ->
                            AlbumItem(
                                album = album,
                                onGoToAlbumClick = { onGoToAlbumClick(album) }
                            )
                        }
                    }
                } else {
                    Text("No results found. Try searching for something else.")
                }
            }
        }
    }
}
