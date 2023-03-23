package ru.netology

fun main() {
    var post = Post(1, 2, 3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = null, views = View(0))
    var post2 = Post(4,7,3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = "post", views = View(10))
    WallService.add(post)
    WallService.add(post2)
    println(post)
    WallService.printlnAll()

    val post3 = Post(45, 2, 3, 4, 5, 6, "aaa", "text", copyRight = null, views = View(155), attachments = Array<Attachment>(3, {AudioAttachment(audio = Audio(1,2, "A", "S", 3))}))
    val firstAttachment = post3.attachments[0]
    if (firstAttachment is AudioAttachment) {
        val audioAttachment = firstAttachment
        println(audioAttachment)
    }

    val attachment: Attachment = VideoAttachment(video = Video(1, 2, "D", 3, 4))
    println(attachment.type)

    WallService.createComment(4, comment = Comment(1, 2))

    val reportComment = ReportComment(1, 2, 0)
    println(WallService.negativeCommentNotification(reportComment))

}