package ru.netology

data class Comment(
    val id: Int,
    val ownerId: Int,
    val massage: String,
    var deleted: Boolean = false
)

class Like(
    var count: Int,
    val userLikes: Boolean, // наличие лайка от пользователя
    val canLike: Boolean, // может ли пользователь поставить лайк
    val canPublish: Boolean // может ли пользователь сделать репост записи
)

class Repost(
    var count: Int,
    userReposted: Boolean // наличие репоста от пользователя
)

class View(
    var count: Int
)