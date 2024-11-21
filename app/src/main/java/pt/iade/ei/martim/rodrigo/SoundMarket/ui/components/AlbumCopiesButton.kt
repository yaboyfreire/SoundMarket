package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.martim.rodrigo.SoundMarket.AlbumCopiesActivity
import androidx.compose.material.*

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight


@Composable
fun AlbumCopiesButton() {
    val context = LocalContext.current

    Button(
        onClick = { val intent = Intent(context, AlbumCopiesActivity::class.java)
            context.startActivity(intent)  },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
    ) {
        Text(text = "5 Currently selling",
            fontWeight = FontWeight.Bold,
            color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumCopiesButtonPreview() {
    AlbumCopiesButton()
}
