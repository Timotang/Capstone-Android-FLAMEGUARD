package com.bionsonluaguezosa.flameguard

data class NewsFeedItem(
    val reporterName: String,
    val reportDate: String,
    val reportDescription: String,
    val imageUrl: Int, // Use Int if you're using a drawable resource
    var likeCount: Int, // Make likeCount mutable
    val commentCount: Int
)
