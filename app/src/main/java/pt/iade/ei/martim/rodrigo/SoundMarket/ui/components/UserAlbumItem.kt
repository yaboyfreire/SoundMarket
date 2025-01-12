package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import pt.iade.ei.martim.rodrigo.SoundMarket.AlbumActivity
import pt.iade.ei.martim.rodrigo.SoundMarket.models.UserAlbum

@Composable
fun UserAlbumItem(album: UserAlbum, onClick: (String) -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(16.dp)) // Rounded corners and background
            .clickable { onClick(album.albumSpotifyID) } // Trigger onClick with the album's spotifyID
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            // Album Image
            Image(
                painter = rememberAsyncImagePainter(album.imageURL),
                contentDescription = "Album Cover",
                modifier = Modifier
                    .size(90.dp)
                    .padding(10.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                // Album name (title) with one line and ellipsis if too long
                Text(
                    text = album.name,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1, // Limit to 1 line
                    overflow = TextOverflow.Ellipsis, // "..." when text overflows
                )

                // Artist name
                Text(
                    text = album.artist,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(top = 4.dp)
                )

                // Album format (if available)
                album.format?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}


