package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.martim.rodrigo.SoundMarket.Album
import pt.iade.ei.martim.rodrigo.SoundMarket.AlbumActivity
import pt.iade.ei.martim.rodrigo.SoundMarket.R

@Composable
fun AlbumItem(album: Album, onGoToAlbumClick: () -> Unit,) {

    val context = LocalContext.current

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(16.dp)) // Add rounded corners
            .height(90.dp)
            .clickable {
                // Launch the AlbumActivity
                val intent = Intent(context, AlbumActivity::class.java).apply {
                    putExtra("ALBUM_TITLE", album.title)
                    putExtra("ALBUM_ARTIST", album.artist)
                    putExtra("ALBUM_YEAR", album.year)
                }
                context.startActivity(intent)
                // Optionally call the passed callback
                onGoToAlbumClick()
            }
    ) {
        Row {


            Image(
                painter = painterResource(id = R.drawable.latina),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(90.dp)
                    .padding(10.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                // Title with one line and ellipsis if too long
                Text(
                    text = album.title,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1, // Limit to 1 line
                    overflow = TextOverflow.Ellipsis, // "..." when text overflows
                    modifier = Modifier.padding(top = 25.dp)
                )

                // Artist and year on one line, no overflow
                Text(
                    text = "${album.artist} • ${album.year}",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun AlbumItemPreview() {
    AlbumItem(
        album = Album(
            title = "Test Album",
            artist = "Test Artist",
            year = 2023
        ),
        onGoToAlbumClick = {}
    )
}


