package ru.netology

data class Note(
    val id: Int, // айди записи
    val ownerId: Int?, // айди владельца стены, на которой размещена запись
    val authorId: Int, // айди автора
    val authorName: String, // имя автора
    val text: String, // тулс поста
    val comment: MutableList<Comment>,
    val attachment: Array<Attachment>
)