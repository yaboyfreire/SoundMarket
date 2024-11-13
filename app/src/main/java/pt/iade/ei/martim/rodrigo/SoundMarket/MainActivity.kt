package pt.iade.ei.martim.rodrigo.SoundMarket

import GridItem
import HomeGenreList
import HorizontalCarousel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.BottomAppBar
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.HomeTopBar
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.SearchBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    var searchQuery by remember { mutableStateOf("") }

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
                    "profile" -> { /* Handle profile icon click */ }
                    "notifications" -> { /* Handle notifications icon click */ }
                    "settings" -> { /* Handle settings icon click */ }
                }
            }

        },
        bottomBar = {
            BottomAppBar { iconClicked ->
                when (iconClicked) {
                    "home" -> { /* Handle home icon click */ }
                    "add" -> { /* Handle sell icon click */ }
                    "email" -> { /* Handle chat icon click */ }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
        ) {

            SearchBar(onSearchQueryChanged = { query -> searchQuery = query })

            HorizontalCarousel(
                items = listOf("Item 1", "Item 2", "Item 3"),
                text = "Trending"
            )

            HomeGenreList(items = genreItems) { clickedItem ->
                println("Clicked on ${clickedItem.label}")
                // Handle grid item click
            }



        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}