package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import HorizontalCarousel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            HomeTopBar(onIconClick = { /* Handle icon clicks here */ })
        },
        bottomBar ={
            BottomAppBar(onIconClick={})
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxWidth()) {
            // Add SearchBar here
            SearchBar(onSearchQueryChanged = { query ->
                searchQuery = query // Just update the state for now
            })

            // Placeholder for CarouselScreen; replace with your actual item display
            Spacer(modifier = Modifier.height(0.dp)) // Add some spacing
            HorizontalCarousel(items = listOf("Item 1", "Item 2", "Item 3"),"Trending") // Placeholder items
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val text = "Trending"
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")
    HorizontalCarousel(items = items,"Trending")
}

