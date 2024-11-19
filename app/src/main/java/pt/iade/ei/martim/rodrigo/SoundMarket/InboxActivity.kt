package pt.iade.ei.martim.rodrigo.SoundMarket

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.martim.rodrigo.SoundMarket.models.ChatItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.BottomAppBar
import pt.iade.ei.martim.rodrigo.SoundMarket.ui.components.HomeTopBar
import androidx.compose.material3.Scaffold
import androidx.compose.ui.platform.LocalContext


class InboxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sampleChats = listOf(
                ChatItem(
                    chatId = "1",
                    username = "John Doe",
                    profilePictureUrl = "https://via.placeholder.com/50",
                    lastMessage = "Hey, are you coming tonight?",
                    lastMessageState = "Read",
                    unreadMessages = 0
                ),
                ChatItem(
                    chatId = "2",
                    username = "Jane Smith",
                    profilePictureUrl = "https://via.placeholder.com/50",
                    lastMessage = "Let me know when you're free.",
                    lastMessageState = "Sent",
                    unreadMessages = 2
                ),
                ChatItem(
                    chatId = "3",
                    username = "MartimC",
                    profilePictureUrl = "https://neweralive.na/wp-content/uploads/2024/06/lloyd-sikeba.jpg",
                    lastMessage = "I'll check it out!",
                    lastMessageState = "Received",
                    unreadMessages = 1
                )
            )
            InboxScreen(chatList = sampleChats, onChatClick = { chatId ->
                println("Clicked on chat: $chatId")
            })
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InboxScreen(
    chatList: List<ChatItem>, // List of all current chats
    onChatClick: (String) -> Unit // Callback when a chat is clicked (pass the chat ID)

) {

    val context = LocalContext.current
    Scaffold(
        bottomBar = {
            BottomAppBar { iconClicked ->
                when (iconClicked) {
                    "home" -> { val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent) }
                    "add" -> { val intent = Intent(context, SellActivity::class.java)
                        context.startActivity(intent)}
                    "email" -> { /* Handle chat icon click */ }
                }
            }
        }
    ){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp)
    ) {
        // Screen Title
        Text(
            text = "Inbox",
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Chat List
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(chatList) { chat ->
                ChatRow(
                    chat = chat,
                    onClick = { val intent = Intent(context, ChatActivity::class.java)
                        context.startActivity(intent) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
}
@Composable
fun ChatRow(chat: ChatItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray,shape = RoundedCornerShape(15.dp))
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Picture
        Image(
            painter = rememberAsyncImagePainter(chat.profilePictureUrl),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Chat Info
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = chat.username,
                fontSize = 16.sp,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = chat.lastMessage,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // Unread Messages and Last Message State
        Column(
            horizontalAlignment = Alignment.End
        ) {
            if (chat.unreadMessages > 0) {
                Text(
                    text = "${chat.unreadMessages} unread",
                    fontSize = 12.sp,
                    color = Color.Red
                )
            }
            Text(
                text = chat.lastMessageState, // Example: "Sent", "Received", "Read"
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InboxScreenPreview() {
    val sampleChats = listOf(
        ChatItem(
            chatId = "1",
            username = "John Doe",
            profilePictureUrl = "https://via.placeholder.com/50",
            lastMessage = "Hey, are you coming tonight?",
            lastMessageState = "Read",
            unreadMessages = 0
        ),
        ChatItem(
            chatId = "2",
            username = "Jane Smith",
            profilePictureUrl = "https://via.placeholder.com/50",
            lastMessage = "Let me know when you're free.",
            lastMessageState = "Sent",
            unreadMessages = 2
        ),
        ChatItem(
            chatId = "3",
            username = "MartimC",
            profilePictureUrl = "https://neweralive.na/wp-content/uploads/2024/06/lloyd-sikeba.jpg",
            lastMessage = "I'll check it out!",
            lastMessageState = "Received",
            unreadMessages = 1
        )
    )

    InboxScreen(chatList = sampleChats, onChatClick = { chatId ->
        println("Clicked on chat: $chatId")
    })
}
