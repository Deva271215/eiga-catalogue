package com.dicoding.eigacatalogue

data class MovieEntity(
        val id: Int? = 0,
        val image: String? = "",
        val title: String? = "",
        val airYear: Int? = 0,
        val genre: String? = "",
        val dur: String? = "",
        val score: Double? = 0.0,
        val tagLine: String? = "",
        val overview: String? = "",
        val director: String? = "",
        val creator: String? = "",
        val release: String? = "",
 )