package pt.iade.ei.martim.rodrigo.SoundMarket

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import pt.iade.ei.martim.rodrigo.SoundMarket.models.UserAlbum
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.UserAlbumItem
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.BottomAppBar
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.HomeTopBar

class UserCollectionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the list of UserAlbums passed from ProfileViewActivity
        val userAlbums = intent.getSerializableExtra("userAlbums") as? ArrayList<UserAlbum> ?: emptyList()

        setContent {
            UserCollectionScreen(userAlbums = userAlbums)
        }
    }
}

@Composable
fun UserCollectionScreen(userAlbums: List<UserAlbum>) {
    val context = LocalContext.current

    // State for the search query
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    // Filtered list based on search query
    val filteredAlbums = userAlbums.filter {
        it.name.contains(searchQuery.text, ignoreCase = true) // Filter by album name
    }

    Scaffold(
        topBar = {
            HomeTopBar { /* Handle actions if needed */ }
        },
        bottomBar = {
            BottomAppBar { /* Handle actions if needed */ }
        }
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {

            // Search bar to filter albums by title
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // If there are no albums, show a message
            if (filteredAlbums.isNotEmpty()) {
                LazyColumn(
                    contentPadding = PaddingValues(bottom = 56.dp) // Adjust the bottom padding for the BottomAppBar
                ) {
                    items(filteredAlbums) { userAlbum ->
                        // Handle navigation inside the onClick block, which is correctly within the composable
                        UserAlbumItem(album = userAlbum) { spotifyID ->
                            // Navigate to the album details screen
                            val intent = Intent(context, AlbumActivity::class.java).apply {
                                putExtra("ALBUM_ID", spotifyID) // Pass the album spotifyID
                            }
                            context.startActivity(intent) // Perform the navigation here
                        }
                    }
                }
            } else {
                // Show message when no albums are available
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "No albums available.", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
