package com.bionsonluaguezosa.flameguard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bionsonluaguezosa.flameguard.NewsFeedAdapter
import com.bionsonluaguezosa.flameguard.NewsFeedItem
import com.bionsonluaguezosa.flameguard.R
import com.bionsonluaguezosa.flameguard.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the RecyclerView with a LinearLayoutManager
        binding.recyclerViewNewsFeed.layoutManager = LinearLayoutManager(requireContext())

        // Sample data for the RecyclerView
        val newsFeedItems = mutableListOf(
            NewsFeedItem(
                reporterName = "Reported by Timothy Bionson",
                reportDate = "June 1, 2024",
                reportDescription = "Fire Incident at Lot 32, Blk 15, Caloocan City, Manila",
                imageUrl = R.drawable.fire_image_placeholder, // Replace with your drawable
                likeCount = 100,
                commentCount = 20
            ),
            NewsFeedItem(
                reporterName = "Reported by Jane Doe",
                reportDate = "August 15, 2024",
                reportDescription = "Minor Fire at Makati City",
                imageUrl = R.drawable.fire_image_placeholder, // Replace with your drawable
                likeCount = 80,
                commentCount = 12
            )
        )

        // Set the adapter
        binding.recyclerViewNewsFeed.adapter = NewsFeedAdapter(newsFeedItems)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
