import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.martim.rodrigo.SoundMarket.R

data class GridItem(val id: Int, val imageRes: Int, val label: String)

@Composable
fun HomeGenreList(items: List<GridItem>, onItemClick: (GridItem) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        // "Genres" label with underline
        Text(
            text = "Genres",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
        )
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 1.dp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Lazy grid to display genre items
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 100.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
                GridItemCard(item = item, onClick = { onItemClick(item) })
            }
        }
    }
}

@Composable
fun GridItemCard(item: GridItem, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.label,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .aspectRatio(1f)
        )
        Text(
            text = item.label,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeGenreList() {
    val genreItems = listOf(
        GridItem(1, R.drawable.rock, "Rock"),
        GridItem(2, R.drawable.popular, "Pop"),
        GridItem(3, R.drawable.hiphop, "Hip Hop"),
        GridItem(4, R.drawable.edm, "EDM"),
        GridItem(5, R.drawable.jazz, "Jazz"),
        GridItem(6, R.drawable.rock, "Classical"),
        GridItem(7, R.drawable.country, "Country"),
        GridItem(8, R.drawable.rnb, "R&B"),
        GridItem(9, R.drawable.rock, "Reggae"),
        GridItem(10, R.drawable.blues, "Blues"),
        GridItem(11, R.drawable.metal, "Metal"),
        GridItem(12, R.drawable.latina, "Latin")
    )
    HomeGenreList(genreItems) { }
}
