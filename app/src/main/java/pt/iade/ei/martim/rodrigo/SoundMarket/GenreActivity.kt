package pt.iade.ei.martim.rodrigo.SoundMarket

import HorizontalCarousel
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import pt.iade.ei.martim.rodrigo.SoundMarket.models.ViewModels.AlbumViewModel

class GenreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val genreId = intent.getStringExtra("GENRE_ID") ?: ""
        val genreName = intent.getStringExtra("GENRE_NAME") ?: ""

        setContent {
            GenreScreen(genreId = genreId, genreName = genreName)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreScreen(genreId: String, genreName: String) {
    val context = LocalContext.current
    val albumViewModel: AlbumViewModel = viewModel() // Get the AlbumViewModel
    val albums = albumViewModel.albums.value // Observe albums from the ViewModel

    // Fetch genre-specific albums when the screen is first loaded
    LaunchedEffect(genreId) {
        val token = "Bearer BQDNEoFK4WgHw69IuquQgaVpQlGPyIrB8WU5pxAYHUOongit7OYPZLnLVdxFReb3g0AcWS4UTBz9fTpB6onvh0HHjs6_IJS59o7p1VLnF4rURmUrOvU"
        albumViewModel.fetchAlbumsByGenre(token, genreId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = genreName) },
                navigationIcon = { /* Back button logic, if needed */ }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (albums.isNotEmpty()) {
                HorizontalCarousel(
                    albums = albums,
                    text = "Albums in $genreName",
                    onButtonClick = {},
                    onAlbumClick = { album ->
                        val intent = Intent(context, AlbumActivity::class.java).apply {
                            putExtra("ALBUM_ID", album.id) // Pass album details
                            putExtra("ALBUM_NAME", album.name)
                        }
                        context.startActivity(intent)
                    }
                )
            } else {
                Text(
                    text = "Loading albums...",
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight(Alignment.CenterVertically),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGenreScreen() {
    GenreScreen(genreId = "1", genreName = "Rock")
}
