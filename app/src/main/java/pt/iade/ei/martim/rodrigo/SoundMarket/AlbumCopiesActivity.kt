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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.martim.rodrigo.SoundMarket.R


class AlbumCopiesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlbumCopiesScreen()
        }
    }
}

@Composable
fun AlbumCopiesScreen() {
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
            repeat(7) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Image Section
                        Image(
                            painter = painterResource(id = R.drawable.latina),
                            contentDescription = "Album Cover",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(end = 8.dp)
                        )

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Condition: Good",
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Plays very well",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }

                        // Buttons and additional image section
                        Column(
                            modifier = Modifier
                                .padding(start = 8.dp)
                        ) {
                            Button(
                                onClick = { /* TODO: Add talk with seller functionality */ },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                                modifier = Modifier
                                    .size(150.dp, 35.dp)
                            ) {
                                Text(
                                    text = "Talk With Seller",
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            Image(
                                painter = painterResource(id = R.drawable.account_circle_24px),
                                contentDescription = "Additional Image",
                                modifier = Modifier.size(20.dp)
                                    .align(Alignment.End)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAlbumCopiesScreen() {
    AlbumCopiesScreen()
}
