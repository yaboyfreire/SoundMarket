package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
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
import pt.iade.ei.martim.rodrigo.SoundMarket.ChatActivity
import pt.iade.ei.martim.rodrigo.SoundMarket.R

@Composable
fun AlbumCopiesCard(){
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
            val context = LocalContext.current

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                Button(
                    onClick = {val intent = Intent(context, ChatActivity::class.java)
                        context.startActivity(intent) },
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
                    painter = painterResource(id = R.drawable.account_circle),
                    contentDescription = "Additional Image",
                    modifier = Modifier.size(20.dp)
                        .align(Alignment.End)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAlbumCopiesCard (){
    AlbumCopiesCard()
}