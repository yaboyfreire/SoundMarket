package pt.iade.ei.martim.rodrigo.SoundMarket

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import pt.iade.ei.martim.rodrigo.SoundMarket.models.ViewModels.AlbumViewModel
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album

class GenreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get genreId and genreName from Intent
        val genreId = intent.getStringExtra("GENRE_ID") ?: ""
        val genreName = intent.getStringExtra("GENRE_NAME") ?: ""

        setContent {
            // Pass genreId and genreName as parameters to GenreScreen
            GenreScreen(genreId = genreId, genreName = genreName)
        }
    }
}

@Composable
fun GenreScreen(genreId: String, genreName: String) {
    val context = LocalContext.current

    // Access the AlbumViewModel
    val albumViewModel: AlbumViewModel = viewModel()
    val albums = albumViewModel.albums.value

    // Fetch albums for the specific genre
    LaunchedEffect(genreName) {
        val token = "Bearer BQAay7OufkW4jNgJZIl1MJ-hQmyQd39EnqCWJKXNrcdnYX_SX9G5Qh5qjIGh81NhW9OS-FCAiFhsDPoVdIhN27l43b4356gsIsfDMRSH5wRCS47nqiU" // Replace with your actual token
        albumViewModel.fetchAlbumsByGenre(token, genreName)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())  // Making the page scrollable
    ){
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

        // Genre Name
        Text(
            text = genreName,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

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

        // Assuming that `albums` is a list of albums and we are showing the first few for "Trending"
        val trendingItems = albums.take(5)  // You can change this to a specific filter for trending albums
        HorizontalCarousel(items = trendingItems, text = "Trending", onButtonClick = {
            val intent = Intent(context, CollectionActivity::class.java)
            context.startActivity(intent)
        })

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

        // Assuming that `albums` also holds new releases, you can filter them similarly
        val newReleasesItems = albums.takeLast(5)  // You can change this to a specific filter for new releases
        HorizontalCarousel(items = newReleasesItems, text = "New Releases", onButtonClick = {
            val intent = Intent(context, CollectionActivity::class.java)
            context.startActivity(intent)
        })

        // Genre-specific albums carousel
        if (albums.isNotEmpty()) {
            Text(
                text = "Popular Albums",
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 24.dp, end = 24.dp)
            )
            HorizontalCarousel(
                items = albums,  // Pass the whole album object here
                text = "Popular Albums",
                onButtonClick = {
                    // Handle album click here, if necessary
                }
            )
        } else {
            Text("Loading albums...")  // Show a loading message if albums are not available yet
        }
    }
}

@Composable
fun HorizontalCarousel(items: List<Album>, text: String, onButtonClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(top = 30.dp, bottom = 25.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 0.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items.size) { index ->
                CarouselItem(album = items[index])  // Pass the whole album object
            }
        }
    }
}

@Composable
fun CarouselItem(album: Album) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(4.dp), // You can adjust the padding as needed
        shape = CircleShape
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val imageUrl = album.images.firstOrNull()?.url  // Use the first image URL from the album
            if (imageUrl != null) {
                Image(
                    painter = rememberImagePainter(imageUrl), // Use Coil or any image loading library
                    contentDescription = album.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                // Display a default image or placeholder if the album has no images
                Image(
                    painter = painterResource(id = R.drawable.latina),
                    contentDescription = "Default image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = album.name,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp) // Adjust the padding for the text
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewGenre() {
    GenreScreen(genreId = "1", genreName = "Pop") // Example data for preview
}
