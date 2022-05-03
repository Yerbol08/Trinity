package com.enu.trinity.ui.notifications

data class ChatMessage(var messageText: String? = "",
                       var messageAuthor: String? = "",
                       var messageTime: Long = 0)
