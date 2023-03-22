package ru.netology

class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int
)

class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val duration: Int,
    val date: Int

)

class Photo(
    val id: Int,
    val ownerId: Int,
    val date: Int,
    val text: String,
    val width: Int,
    val height: Int
)


class Doc(
    val id: Int,
    val ownerId: Int,
    val date: Int,
    val title: String,
    val size: Int
)

class Link(
    val url: String,
    val title: String,
    val caption: String,
    val description: String,
    val photo: Photo
)