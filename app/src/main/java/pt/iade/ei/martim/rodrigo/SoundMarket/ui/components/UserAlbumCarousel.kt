package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.ButtonText
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import pt.iade.ei.martim.rodrigo.SoundMarket.models.UserAlbum


@Composable
fun UserAlbumCarousel(userAlbums: List<UserAlbum>, text: String, onButtonClick: () -> Unit, onAlbumClick: (UserAlbum) -> Unit) {
    Column(
        modifier = Modifier.padding(top = 15.dp, bottom = 5.dp)
    ) {
        ButtonText(text = text, onClick = onButtonClick)
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(userAlbums.size) { index ->
                CarouselItem(userAlbum = userAlbums[index], onAlbumClick = onAlbumClick)
            }
        }
    }
}

@Composable
fun CarouselItem(userAlbum: UserAlbum, onAlbumClick: (UserAlbum) -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(180.dp)
            .clickable { onAlbumClick(userAlbum) },
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Displaying the album image
            Image(
                painter = rememberAsyncImagePainter(model = userAlbum.imageURL),
                contentDescription = userAlbum.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Displaying album name
            Text(text = userAlbum.name, style = MaterialTheme.typography.labelLarge, maxLines = 1)
            // Displaying artist name
            Text(
                text = userAlbum.artist,
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserAlbumCarousel() {
    // Correcting the dummy data for testing
    val dummyUserAlbums = listOf(
        UserAlbum(
            id = 1,
            name = "Back in Black", // Correct album name
            condition = "New",
            format = "Vinyl",
            artist = "AC/DC",
            genre = "Rock",
            albumSpotifyID = "2YeWzDmj0hGKHXYv2hgBz8",
            albumAddedDate = "2025-01-11T22:39:43",
            imageURL = "https://i.scdn.co/image/ab67616d0000b2730f4e8eae1bdb84c98a8b02c1",
            userId = 0
        ),
        UserAlbum(
            id = 81,
            name = "Ahaaaaam",
            condition = "New",
            format = "Vinyl",
            artist = "Artist Name",
            genre = "Genre",
            albumSpotifyID = "1234567890abcdef",
            albumAddedDate = "2025-01-09T11:02:02",
            imageURL = "https://example.com/image.jpg",
            userId = 0
        )
    )

    // Preview with corrected data
    UserAlbumCarousel(
        userAlbums = dummyUserAlbums,
        text = "User Collection",
        onButtonClick = { /* Mock behavior */ },
        onAlbumClick = { userAlbum ->
            println("Clicked on album: ${userAlbum.name}") // Mock behavior for album click
        }
    )
}
