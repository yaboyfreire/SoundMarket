package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.martim.rodrigo.SoundMarket.AddToCollectionFromAlbumActivity
import pt.iade.ei.martim.rodrigo.SoundMarket.AddToCollectionFromCollectionActivity
import pt.iade.ei.martim.rodrigo.SoundMarket.models.Album

@Composable
fun AlbumActions(album: Album) {
    val context = LocalContext.current

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Button(
            onClick = {
                // Ensure the URL is valid and open it correctly
                val spotifyUrl = "https://open.spotify.com/album/${album.id}"
                if (spotifyUrl.isNotBlank()) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(spotifyUrl))
                    // Adding flags to ensure it opens in the appropriate browser
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1DB954))
        ) {
            Text(text = "Listen Here")
        }

        Button(
            onClick = {
                // Prepare the Intent to launch the AddToCollectionFromAlbumActivity
                val intent = Intent(context, AddToCollectionFromAlbumActivity::class.java)
                intent.putExtra("albumId", album.id)  // Pass the album ID
                intent.putExtra("albumTitle", album.name)  // Pass the album name
                intent.putExtra("albumArtist", album.artists.firstOrNull()?.name ?: "Unknown Artist")  // Pass the artist name
                intent.putExtra("imageURL", album.images.firstOrNull()?.url ?: "")  // Pass the image URL (or an empty string if not available)
                context.startActivity(intent)  // Start the activity
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text(text = "Add to Collection")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumActionsPreview() {
    val sampleAlbum = Album(
        id = "123",
        name = "Swimming",
        artists = listOf(Album.Artist(name = "Mac Miller")),
        externalUrls = Album.ExternalUrls(spotify = "https://open.spotify.com/album/5wtE5aLX5r7jOosmPhJhhk?si=Aendik6ZQnGl0T-10gE_ag")
    )
    AlbumActions(album = sampleAlbum)
}
