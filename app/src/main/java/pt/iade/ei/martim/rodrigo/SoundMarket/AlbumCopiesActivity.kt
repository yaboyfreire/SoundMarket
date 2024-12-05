package pt.iade.ei.martim.rodrigo.SoundMarket

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.martim.rodrigo.SoundMarket.MainActivity
import pt.iade.ei.martim.rodrigo.SoundMarket.R
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.AlbumCopiesCard


class AlbumCopiesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlbumCopies()
        }
    }
}

@Composable
fun AlbumCopies() {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()
        .verticalScroll(scrollState)) {

        // App Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(300.dp, 130.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Album list section
        Column(modifier = Modifier.padding(16.dp)) {
                AlbumCopiesCard()




        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewAlbumCopies() {
    AlbumCopies(
    )

}