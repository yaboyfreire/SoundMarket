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
                // Create an intent to open the Spotify link dynamically
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(album.externalUrls.spotify))
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1DB954))
        ) {
            Text(text = "Listen Here")
        }

        Button(
            onClick = {
                val intent = Intent(context, AddToCollectionFromCollectionActivity::class.java)
                intent.putExtra("albumId", album.id)  // You can pass more album details here if needed
                context.startActivity(intent)
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
