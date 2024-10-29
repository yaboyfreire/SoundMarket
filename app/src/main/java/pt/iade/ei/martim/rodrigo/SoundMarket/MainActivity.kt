package pt.iade.ei.martim.rodrigo.SoundMarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.HorizontalCarousel
import pt.iade.ei.martim.rodrigo.SoundwMarket.models.Item
import java.net.URI
import java.time.LocalDateTime
import java.time.ZoneOffset

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HorizontalCarousel(items = listOf("item1","item2"))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    val sampleItems = listOf("Sample 1", "Sample 2", "Sample 3", "Sample 4")
    HorizontalCarousel(items = sampleItems)
}