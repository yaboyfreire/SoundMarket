package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

@Composable
fun TrackItem(
    trackName: String,
    trackImageUrl: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        // Track Image
        Image(
            painter = rememberImagePainter(data = trackImageUrl),
            contentDescription = "Track Image",
            modifier = Modifier
                .size(50.dp)
                .padding(end = 8.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        // Track Name
        Text(
            text = trackName,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .alignByBaseline()
        )
    }
}
@Preview
@Composable
fun TrackItemPreview() {
    // Example data for the preview
    TrackItem(
        trackName = "Bohemian Rhapsody",
        trackImageUrl = "https://link-to-image.com/track_image.jpg"
    )
}