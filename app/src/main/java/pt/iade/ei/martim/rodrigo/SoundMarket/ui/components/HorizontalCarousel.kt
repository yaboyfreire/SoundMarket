import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.ButtonText

@Composable
fun HorizontalCarousel(items: List<String>,text:String) {
    Column(
        modifier = Modifier.padding(top=30.dp,bottom=25.dp) //padding around the entire carousel
    ) {

        ButtonText(text)
        Spacer(modifier = Modifier.height(8.dp)) //spacing between the ButtonText and the carousel

        LazyRow(
            contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items.size) { index ->
                CarouselItem(item = items[index])
            }
        }
    }
}

@Composable
fun CarouselItem(item: String) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(160.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = item, style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCarouselScreen() {
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")
    HorizontalCarousel(items = items,"Trending")
}
