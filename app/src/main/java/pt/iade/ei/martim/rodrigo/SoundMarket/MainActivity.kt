package pt.iade.ei.martim.rodrigo.SoundMarket

import HorizontalCarousel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
            HomeScreen() // Display the HomeScreen
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    var searchQuery by remember { mutableStateOf("") } // State to hold the search query

    Scaffold(
        topBar = {
            HomeTopBar { iconClicked ->
                when (iconClicked) {
                    "profile" -> {
                        // Handle profile icon click
                    }
                    "notifications" -> {
                        // Handle notifications icon click
                    }
                    "settings" -> {
                        // Handle settings icon click
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar { iconClicked ->
                when (iconClicked) {
                    "home" -> {
                        // Handle home icon click
                    }
                    "add" -> {
                        // Handle sell icon click
                    }
                    "email" -> {
                        // Handle chat icon click
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp), // Optional padding on the sides
        ) {
            // Add SearchBar here
            SearchBar(onSearchQueryChanged = { query ->
                searchQuery = query // Update the search query
            })
            // Add HorizontalCarousel with dynamic items and a title
            HorizontalCarousel(
                items = listOf("Item 1", "Item 2", "Item 3"),
                text="Trending"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
