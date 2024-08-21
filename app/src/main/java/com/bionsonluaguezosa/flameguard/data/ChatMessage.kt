package com.bionsonluaguezosa.flameguard.data

data class ChatMessage(
    val messageContent: String,
    val sender: String, // Assuming you want to track who sent the message
    val timestamp: Long // Using timestamp for time tracking
)
