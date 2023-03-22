package ru.netology

class Comment(
    val id: Int,
    val ownerId: Int,
    val canPost: Boolean = true, // может ли пользователь комментировать запись
    val groupsCanPost: Boolean = false, // могут ли сообщества комментировать запись
    val canClose: Boolean = true, // может ли пользователь закрыть комментарии к записи
    val canOpen: Boolean = true // может ли пользователь открыть комментарии к записи
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