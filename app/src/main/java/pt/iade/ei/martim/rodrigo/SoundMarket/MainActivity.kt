package pt.iade.ei.martim.rodrigo.SoundMarket

import HomeGenreList
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.BottomAppBar
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.HomeTopBar
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.SearchBar
import pt.iade.ei.martim.rodrigo.SoundMarket.viewmodel.AlbumViewModel
import GridItem
import HorizontalCarousel
import android.annotation.SuppressLint


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
        val token = "Bearer BQCNbB81uYvLSsROby0NaPCM1E159niIcCloQt0TYBOyAKd4_Lstk6kwF7GWQxqjpMIhb505CxeolsJJ9b6UYBYOA5fIhfkmERGb23qkkkggK_NK2tk" // Replace with your actual token
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
                )
            } else {
                Text("Loading albums...")
            }

            HomeGenreList(items = genreItems) { clickedItem ->
                val intent = Intent(context, GenreActivity::class.java).apply {
                    putExtra("GENRE_ID", clickedItem.id)
                    putExtra("GENRE_NAME", clickedItem.label)
                }
                context.startActivity(intent)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(onButtonClick = {})
}