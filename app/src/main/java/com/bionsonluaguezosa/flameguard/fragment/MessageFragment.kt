package com.bionsonluaguezosa.flameguard.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bionsonluaguezosa.flameguard.ChatActivity
import com.bionsonluaguezosa.flameguard.data.Message
import com.bionsonluaguezosa.flameguard.adapter.MessageAdapter
import com.bionsonluaguezosa.flameguard.R

class MessageFragment : Fragment() {

    private lateinit var messageRecyclerView: RecyclerView
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_message, container, false)

        messageRecyclerView = view.findViewById(R.id.message_recycler_view)
        messageRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val messages = getDummyMessages()
        messageAdapter = MessageAdapter(messages) { message ->
            val intent = Intent(requireActivity(), ChatActivity::class.java)
            intent.putExtra("contact_name", message.contactName)
            startActivity(intent)
        }

        messageRecyclerView.adapter = messageAdapter

        return view
    }

    private fun getDummyMessages(): List<Message> {
        // Dummy data for testing
        return listOf(
            Message("John Doe", "Hey, how are you?", "10:00 AM"),
            Message("Jane Smith", "Hello!", "11:30 AM")
        )
    }
}


