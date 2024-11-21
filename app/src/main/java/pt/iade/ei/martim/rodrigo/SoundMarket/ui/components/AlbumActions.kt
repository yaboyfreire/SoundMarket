package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.HorizontalAlign

@Composable
fun AlbumActions() {
    val context = LocalContext.current

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),

    ) {
        androidx.compose.material.Button(
            onClick = {
                // Create an intent to open the Spotify link
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://open.spotify.com/album/5wtE5aLX5r7jOosmPhJhhk?si=Aendik6ZQnGl0T-10gE_ag")
                )
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1DB954))
        ) {
            androidx.compose.material.Text(text = "Listen Here")
        }

        androidx.compose.material.Button(
            onClick = { /* Handle add to collection logic here */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
        ) {
            androidx.compose.material.Text(text = "Add to Collection")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumActionsPreview() {
    AlbumActions()
}
