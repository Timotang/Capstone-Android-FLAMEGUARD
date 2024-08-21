package com.bionsonluaguezosa.flameguard

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bionsonluaguezosa.flameguard.adapter.ChatAdapter
import com.bionsonluaguezosa.flameguard.data.ChatMessage

class ChatActivity : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var chatMessages: MutableList<ChatMessage>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // Initialize views
        val chatWith = findViewById<TextView>(R.id.chat_with)
        chatRecyclerView = findViewById(R.id.chat_recycler_view)
        messageInput = findViewById(R.id.chat_input)
        sendButton = findViewById(R.id.send_button)

        // Set up RecyclerView for chat messages
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatMessages = getDummyChatMessages() // Replace with actual chat messages
        chatAdapter = ChatAdapter(chatMessages)
        chatRecyclerView.adapter = chatAdapter

        // Set up back button
        findViewById<ImageView>(R.id.back_btn).setOnClickListener {
            finish()
        }

        // Set up sending message
        sendButton.setOnClickListener {
            val message = messageInput.text.toString().trim()
            if (message.isNotEmpty()) {
                val chatMessage = ChatMessage(message, "SenderName", System.currentTimeMillis())
                chatMessages.add(chatMessage)
                chatAdapter.notifyItemInserted(chatMessages.size - 1)
                chatRecyclerView.scrollToPosition(chatMessages.size - 1)
                messageInput.text.clear()
            }
        }
    }

    private fun getDummyChatMessages(): MutableList<ChatMessage> {
        // Dummy data for testing
        return mutableListOf(
            ChatMessage("Hello!", "John Doe", System.currentTimeMillis() - 5000),
            ChatMessage("Hey there!", "Jane Smith", System.currentTimeMillis() - 3000),
            ChatMessage("How are you?", "John Doe", System.currentTimeMillis() - 1000)
        )
    }
}
