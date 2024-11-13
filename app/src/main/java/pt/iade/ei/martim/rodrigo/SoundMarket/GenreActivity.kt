package pt.iade.ei.martim.rodrigo.SoundMarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class GenreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GenreScreen()
        }
    }
}

@Composable
fun GenreScreen() {
    Column {
        // App Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(300.dp, 130.dp)
                    .fillMaxWidth(),
            )
        }

        // Trending Carousel
        Text(
            text = "Trending",
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        )
        Divider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 1.dp,
            color = Color.Gray
        )
        val trendingItems =
            listOf( "Trending 1",
                    "Trending 2",
                    "Trending 3",
                    "Trending 4",
                    "Trending 5")
        HorizontalCarousel(items = trendingItems, text = "Trending")

        // New Releases Carousel
        Text(
            text = "New Releases",
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        )
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 1.dp,
            color = Color.Gray
        )
        val newReleasesItems = listOf("Release 1", "Release 2", "Release 3", "Release 4", "Release 5")
        HorizontalCarousel(items = newReleasesItems, text = "New Releases")

        // Artists Carousel with Images
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Artists",
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 24.dp, end = 24.dp)
            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 1.dp,
            color = Color.Gray
        )

        val artistItems = listOf("Artist 1", "Artist 2", "Artist 3", "Artist 4", "Artist 5")
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(artistItems.size) { index ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.blues), // replace with dynamic resource if available
                        contentDescription = "Artist",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = artistItems[index],
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HorizontalCarousel(items: List<String>, text: String) {
    Column(
        modifier = Modifier.padding(top = 30.dp, bottom = 25.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 0.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items.size) { index ->
                CarouselItem(item = items[index])
            }
        }
    }
}

@Composable
fun CarouselItem(item: String) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = item, style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGenre() {
    GenreScreen()
}
