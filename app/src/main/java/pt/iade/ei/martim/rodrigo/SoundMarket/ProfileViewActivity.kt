package pt.iade.ei.martim.rodrigo.SoundMarket


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.platform.LocalContext


//IMPORTANT THIS IS ONLY THE UI,MOST OF THE INFORMATION DISPLAYED HERE WILL BE RECEIVED THROUGH THE DATABASE

class ProfileViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileScreen(onClick = { navigateToEditProfileActivity() })
        }
    }


    private fun navigateToEditProfileActivity() {
        val intent = Intent(this, EditProfileActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun ProfileScreen(onClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // Profile Picture, Name, and Email
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clickable { onClick() },
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.latina),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
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
            Spacer(modifier = Modifier.height(8.dp))
            Text("Rodrigo Freire", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("rfreire750@gmail.com", fontSize = 16.sp, color = Color.Gray)
            Text("Member Since 2024", fontSize = 12.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // About Me
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Country: ", fontWeight = FontWeight.Bold)
                Text("Portugal 🇵🇹")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("About me", fontWeight = FontWeight.Bold)
            Text(
                "Music lover and collector of rare albums, specializing in Hip Hop. Always on the lookout for vintage vinyl and unique CDs to add to my collection. Feel free to check out my listings or drop me a message if you're interested in trading!"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Collection, Wishlist, and Selling sections
        //Filters the results from the table(Collection,WishList,Selling)
        HorizontalCarousel(
            items = listOf("Swimming", "2014 Forest Hills Drive", "DAMN"),
            text = "Collection",
            onButtonClick = {
                val intent = Intent(context, CollectionActivity::class.java)
                context.startActivity(intent)
            }
        )

        HorizontalCarousel(
            items = listOf("DAMN"),
            text = "Wishlist",
            onButtonClick = {
                val intent = Intent(context, CollectionActivity::class.java)  // Adjusted to open WishlistActivity
                context.startActivity(intent)
            }
        )

        HorizontalCarousel(
            items = listOf("Something"),
            text = "Selling",
            onButtonClick = {
                val intent = Intent(context, CollectionActivity::class.java)  // Adjusted to open SellingActivity
                context.startActivity(intent)
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(onClick = { /* Preview click handler */ })
}