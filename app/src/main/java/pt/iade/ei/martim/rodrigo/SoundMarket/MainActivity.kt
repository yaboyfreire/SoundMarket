package pt.iade.ei.martim.rodrigo.SoundMarket

import GridItem
import HomeGenreList
import HorizontalCarousel
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.BottomAppBar
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.HomeTopBar
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.SearchBar
import pt.iade.ei.martim.rodrigo.SoundMarket.models.ViewModels.AlbumViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(onButtonClick = { navigateToCollectionActivity() })
        }
    }

    private fun navigateToCollectionActivity() {
        val intent = Intent(this, CollectionActivity::class.java).apply {
            putExtra("COLLECTION_TYPE", "NewReleases")
        }
        startActivity(intent)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(onButtonClick: () -> Unit) {
    val context = LocalContext.current
    val albumViewModel: AlbumViewModel = viewModel() // Get the AlbumViewModel
    val albums = albumViewModel.albums.value // Observe albums from the ViewModel

    // Fetch new releases when the screen is first loaded
    LaunchedEffect(Unit) {
        val token = "Bearer BQDSPyigZKKu_Ugy54ynAVh3co9UsTSpXraOpGbFrRAoGJQB-W2xuPrVOj2-7usREyaFxFv1Mr9QDJV4qCl7p2XD33rwZCNz2T5708F7SmEcGJ-CivE"
        albumViewModel.fetchNewReleases(token)
    }

    val genreItems = listOf(
        GridItem(1, R.drawable.rock, "Rock"),
        GridItem(2, R.drawable.popular, "Pop"),
        GridItem(3, R.drawable.hiphop, "Hip Hop"),
        GridItem(4, R.drawable.edm, "EDM"),
        GridItem(5, R.drawable.jazz, "Jazz"),
        GridItem(6, R.drawable.rock, "Classical"),
        GridItem(7, R.drawable.country, "Country"),
        GridItem(8, R.drawable.rnb, "R&B"),
        GridItem(9, R.drawable.rock, "Reggae"),
        GridItem(10, R.drawable.blues, "Blues"),
        GridItem(11, R.drawable.metal, "Metal"),
        GridItem(12, R.drawable.latina, "Latin")
    )

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

            SearchBar(onSearchQueryChanged = { query -> /* Handle search query change */ })

            if (albums.isNotEmpty()) {
                HorizontalCarousel(
                    albums = albums, // Now using the albums from the ViewModel
                    text = "New Releases",
                    onButtonClick = onButtonClick,
                    onAlbumClick = { album ->
                        val intent = Intent(context, AlbumActivity::class.java).apply {
                            putExtra("ALBUM_ID", album.id) // Pass album details
                            putExtra("ALBUM_NAME", album.name)
                        }
                        context.startActivity(intent)
                    }
                )
            } else {
                Text("Loading albums...")
            }

            HomeGenreList(items = genreItems) { clickedItem ->
                // Check if clickedItem has valid data before starting GenreActivity
                if (clickedItem.id != null && clickedItem.label.isNotEmpty()) {
                    val intent = Intent(context, GenreActivity::class.java).apply {
                        putExtra("GENRE_ID", clickedItem.id.toString()) // Pass the genre ID
                        putExtra("GENRE_NAME", clickedItem.label) // Pass the genre name
                    }
                    context.startActivity(intent)
                } else {
                    Log.e("HomeScreen", "Invalid genre data: id = ${clickedItem.id}, label = ${clickedItem.label}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(onButtonClick = {})
}
