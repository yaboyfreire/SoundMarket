package pt.iade.ei.martim.rodrigo.SoundMarket

import HorizontalCarousel
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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

    // Fetch genre-specific albums
    LaunchedEffect(genreId) {
        val token = "Bearer BQACZ5h-y2qs1xHPaSbteG-HmTkPO4EIsKvhqUzvLyQW4qQ1AkUpnTzTqoMHyU8DAenk33412AA5I5spaXmAmqBZMt-1SumMHXcZR4OSB4VZ7UNyLM0"


        albumViewModel.fetchAlbumsByGenre(token, genreId)
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(300.dp, 130.dp)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            TopAppBar(
                title = { Text(text = genreName) }
            )

            if (albums.isNotEmpty()) {
                HorizontalCarousel(
                    albums = albums,
                    text = "Albums in $genreName",
                    onButtonClick = {},
                    onAlbumClick = { album ->
                        val intent = Intent(context, AlbumActivity::class.java).apply {
                            putExtra("ALBUM_ID", album.id)
                            putExtra("ALBUM_NAME", album.name)
                        }
                        context.startActivity(intent)
                    }
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Loading albums...",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGenreScreen() {
    GenreScreen(genreId = "1", genreName = "Rock")
}
