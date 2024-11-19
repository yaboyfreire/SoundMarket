package pt.iade.ei.martim.rodrigo.SoundMarket

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter



@Composable
fun ChatScreen(
    currentTopic: String,
    albumName: String,
    artistName: String,
    profileInfo: UserProfile,
    messages: List<Message>,
    onSendMessage: (String) -> Unit,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Info(Clickable)
            Image(
                painter = rememberAsyncImagePainter(profileInfo.profilePictureUrl),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable { onProfileClick() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = profileInfo.userName, fontWeight = FontWeight.Bold)
                profileInfo.email?.let { Text(text = it) }
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        // Current Topic
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(currentTopic),
                contentDescription = "Topic Image",
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = albumName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = artistName, fontSize = 14.sp, color = Color.DarkGray)
            }
        }


        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(messages) { message ->
                MessageBubble(message)
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Add attachment logic */ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
            TextField(
                value = "",
                onValueChange = { /* Update input */ },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type a message...") },
                maxLines = 1
            )
            IconButton(onClick = { /* Send message */ }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = "Send")
            }
        }
    }
}

@Composable
fun MessageBubble(message: Message) {
    val alignment = if (message.isSentByCurrentUser) Alignment.End else Alignment.Start
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Text(
            text = message.text,
            modifier = Modifier
                .background(
                    if (message.isSentByCurrentUser) Color.Blue else Color.Gray,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp),
            color = Color.White
        )
        Text(
            text = "${message.timestamp} | ${message.status}",
            fontSize = 12.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(4.dp)
        )
    }
}

// Models
data class UserProfile(
    val userName: String,
    val email: String?,
    val profilePictureUrl: String
)

data class Message(
    val text: String,
    val timestamp: String,
    val status: String, // e.g., "Sent", "Received", "Read"
    val isSentByCurrentUser: Boolean
)

// Preview
@Preview
@Composable
fun PreviewChatScreen() {
    ChatScreen(
        currentTopic = "https://upload.wikimedia.org/wikipedia/en/5/5e/Mac_Miller_-_Swimming.png",
        albumName = "Swimming",
        artistName = "Mac Miller",
        profileInfo = UserProfile(
            userName = "Martim Conceição",
            email = "martimC@gmail.com",
            profilePictureUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTyzTWQoCUbRNdiyorem5Qp1zYYhpliR9q0Bw&s"
        ),
        messages = listOf(
            Message("Hey there!", "9:30 AM", "Read", true),
            Message("Hi! How are you?", "9:31 AM", "Received", false)
        ),
        onSendMessage = {},
        onProfileClick = {}
    )
}
