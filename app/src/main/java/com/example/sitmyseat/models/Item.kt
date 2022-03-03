package com.example.sitmyseat.models

data class Item(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int


) {
    override fun toString(): String {
        return "adult : $adult" +
                "\noriginal_language : '$original_language'" +
                "\npopularity:$popularity" +
                "\nposter_path : '$poster_path'" +
                "\nrelease_date='$release_date'" +
                "\ntitle='$title'" +
                "\nvote_count=$vote_count"
    }
}