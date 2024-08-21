package com.bionsonluaguezosa.flameguard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bionsonluaguezosa.flameguard.databinding.ActivityViewPostBinding

class ViewPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPostBinding
    private val comments = mutableListOf<Comment>() // This will hold the comments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get data from intent
        val reporterName = intent.getStringExtra("REPORTER_NAME")
        val reportDate = intent.getStringExtra("REPORT_DATE")
        val reportDescription = intent.getStringExtra("REPORT_DESCRIPTION")
        val imageUrl = intent.getIntExtra("IMAGE_URL", R.drawable.fire_image_placeholder)

        // Set data to views
        binding.postReporterName.text = reporterName
        binding.postReportDate.text = reportDate
        binding.postReportDescription.text = reportDescription
        binding.postImage.setImageResource(imageUrl)

        // Set up RecyclerView for comments
        val commentAdapter = CommentAdapter(comments)
        binding.commentsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.commentsRecyclerView.adapter = commentAdapter

        // Handle comment submission
        binding.submitCommentButton.setOnClickListener {
            val commentText = binding.commentInput.text.toString()
            if (commentText.isNotBlank()) {
                val newComment = Comment(username = "User", commentText = commentText)
                comments.add(newComment)
                commentAdapter.notifyItemInserted(comments.size - 1) // Notify adapter
                binding.commentInput.text.clear() // Clear the input field
            }
        }
    }
}
