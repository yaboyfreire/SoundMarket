package pt.iade.ei.martim.rodrigo.SoundMarket.models

data class ChatItem(
    val chatId: String, // Unique ID for the chat
    val username: String, // Chat partner's name
    val profilePictureUrl: String, // URL for profile picture
    val lastMessage: String, // Last message text
    val lastMessageState: String, // State of the last message: "Sent", "Received", "Read"
    val unreadMessages: Int // Number of unread messages
)