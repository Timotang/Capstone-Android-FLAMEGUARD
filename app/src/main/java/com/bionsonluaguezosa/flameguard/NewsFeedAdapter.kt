package com.bionsonluaguezosa.flameguard

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bionsonluaguezosa.flameguard.databinding.ItemNewsFeedBinding

class NewsFeedAdapter(private val newsFeedItems: MutableList<NewsFeedItem>) :
    RecyclerView.Adapter<NewsFeedAdapter.NewsFeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedViewHolder {
        val binding = ItemNewsFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsFeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsFeedViewHolder, position: Int) {
        val item = newsFeedItems[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int = newsFeedItems.size

    inner class NewsFeedViewHolder(private val binding: ItemNewsFeedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsFeedItem: NewsFeedItem, position: Int) {
            binding.reporterName.text = newsFeedItem.reporterName
            binding.reportDate.text = newsFeedItem.reportDate
            binding.reportDescription.text = newsFeedItem.reportDescription
            binding.newsImage.setImageResource(newsFeedItem.imageUrl)
            binding.likeCount.text = "${newsFeedItem.likeCount} likes this post"
            binding.commentCount.text = "${newsFeedItem.commentCount} Comments"

            binding.likeIcon.setOnClickListener {
                newsFeedItem.likeCount++
                notifyItemChanged(position)
            }

            binding.commentIcon.setOnClickListener {
                // Launch ViewPostActivity
                val intent = Intent(itemView.context, ViewPostActivity::class.java).apply {
                    putExtra("REPORTER_NAME", newsFeedItem.reporterName)
                    putExtra("REPORT_DATE", newsFeedItem.reportDate)
                    putExtra("REPORT_DESCRIPTION", newsFeedItem.reportDescription)
                    putExtra("IMAGE_URL", newsFeedItem.imageUrl)
                }
                itemView.context.startActivity(intent)
            }
        }
    }
}
