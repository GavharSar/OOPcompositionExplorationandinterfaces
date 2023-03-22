package ru.netology

data class Post(
    val id: Int, // айди поста
    val ownerId: Int?, // айди владельца стены, на которой размещена запись
    val authorId: Int, // айди автора
    val createdBy: Int = 0, // айди админстратора, который опубликовал запись
    val replyOwnerId: Int, // айди владельца записи, в ответ на которую была оставлена текущая
    val replyPostId: Int, // айди записи, в ответ на которую была оставлена текущая
    val authorName: String, // имя автора
    val content: String = "content", // текст поста
    val publish: Int = 111, // дата публикации
    val canDelete: Boolean = true, // может ли пользователь удалить пост
    val canEdit: Boolean = true, // может ли пользователь редактировать пост
    val canPin: Boolean = true, // может ли пользователь прикрепить пост
    val friendsOnly: Boolean = false, // только для друзей
    val copyRight: String?, // источник материала
    val postType: String = "text", // тип записи
    val postSource: String = "text", // способ размещения
    val comments: Comment = Comment(0, 2),
    val likes: Like = Like(0, false, true, true),
    val reposts: Repost? = Repost(0, true),
    val views: View,
    val attachments: Array<Attachment> = emptyArray()
)