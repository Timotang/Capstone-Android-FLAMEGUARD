package com.bionsonluaguezosa.flameguard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bionsonluaguezosa.flameguard.data.Message
import com.bionsonluaguezosa.flameguard.R

class MessageAdapter(
    private val messages: List<Message>,
    private val onItemClick: (Message) -> Unit
) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_list_item, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.bind(message)
        holder.itemView.setOnClickListener {
            onItemClick(message)
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contactName: TextView = itemView.findViewById(R.id.contact_name)
        private val lastMessage: TextView = itemView.findViewById(R.id.last_message)
        private val messageTime: TextView = itemView.findViewById(R.id.message_time)

        fun bind(message: Message) {
            contactName.text = message.contactName
            lastMessage.text = message.lastMessage
            messageTime.text = message.time
        }
    }
}
