package pt.iade.ei.martim.rodrigo.SoundMarket

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.AlbumActions
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.AlbumCopiesButton
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.AlbumDetailsSection
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.AlbumTopBar
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.TrackListSection

class AlbumActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlbumScreen()
        }
    }
}

@Composable
fun AlbumScreen() {
    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())

    ) {
        AlbumTopBar()
        Spacer(modifier = Modifier.height(16.dp))
        AlbumDetailsSection()
        Spacer(modifier = Modifier.height(16.dp))
        AlbumActions()
        Spacer(modifier = Modifier.height(16.dp))
        TrackListSection()
        Spacer(modifier = Modifier.height(16.dp))
        AlbumMetadata()
        Spacer(modifier = Modifier.height(16.dp))
        AlbumCopiesButton()
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumScreenPreview() {
    AlbumScreen()
}

