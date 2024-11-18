package pt.iade.ei.martim.rodrigo.SoundMarket

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AlbumScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Top Bar Section
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(300.dp, 100.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Album Details Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.latina),
                contentDescription = "Album Cover",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Swimming",
                    // Default font with bold weight applied to title
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "mac miller • 2018",
                    // Default font with no changes
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Box to contain both Text elements
                Box(
                    modifier = Modifier
                        .padding(8.dp) // Optional padding around the box
                        .background(Color(0xFF4CAF50))
                        .padding(16.dp) // Padding inside the box
                ) {
                    // Column for Text elements inside the Box
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "8.7/10",
                            color = Color.Black, // White text for contrast
                            style = MaterialTheme.typography.h6 // Optional: Adjust the style if needed
                        )
                        Text(
                            text = "SoundRating",
                            color = Color.Black, // White text for contrast
                            style = MaterialTheme.typography.body2 // Optional: Adjust the style if needed
                        )
                    }
                }
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { /* handle action here */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1DB954))

            ) {
                Text(text = "Listen Here")
            }
            Button(
                onClick = { /* handle action here */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
            ) {
                Text(text = "Add to Collection")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tracklist Section
        Column {
            Text(
                text = "TRACKLIST",
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            val tracks = listOf(
                "Come Back To Earth" to "2:41",
                "Hurt Feelings" to "4:05",
                "What's the Use?" to "4:49",
                "Perfecto" to "3:35",
                "Self Care" to "5:45",
                "Wings" to "4:10",
                "Ladders" to "4:47",
                "Small Worlds" to "4:31",
                "Conversation Pt. 1" to "3:31",
                "Dunno" to "3:57",
                "Jet Fuel" to "5:45",
                "2009" to "5:47",
                "So It Goes" to "5:31"
            )
            tracks.forEach { (title, time) ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                    )
                    Text(
                        text = time,
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Release Date", fontWeight = FontWeight.Bold)
            Text(
                text = "August 3, 2018",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Genre", fontWeight = FontWeight.Bold)
            Text(
                text = "Hip Hop",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Goes To AlbumCopies
        Button(
            onClick = { /* handle action here */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
        ) {
            Text(text = "5 Currently selling", color = Color.White)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAlbumScreen() {
    AlbumScreen()
}
