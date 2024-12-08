package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

// Import this for BitmapPainter
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pt.iade.ei.martim.rodrigo.SoundMarket.R

@Composable
fun ProfilePicture(userImageBitmap: Bitmap?, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.BottomEnd
    ) {
        if (userImageBitmap != null) {
            // Use BitmapPainter for displaying Bitmap
            Image(
                painter = BitmapPainter(userImageBitmap.asImageBitmap()),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            // Placeholder image
            Image(
                painter = painterResource(R.drawable.latina),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit Profile Picture",
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .padding(4.dp)
        )
    }
}
