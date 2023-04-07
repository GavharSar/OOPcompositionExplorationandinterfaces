package ru.netology

fun main() {
    val post3 = Post(45, 2, 3, "Q", "W", comment = mutableListOf<Comment>(), attachment = arrayOf<Attachment>(VideoAttachment(video = Video(1, 2, "D", 3, 4)), AudioAttachment(audio = Audio(1, 3, "A", "S", 5))))
    var firstAttachment = post3.attachment[1]
    if (firstAttachment is AudioAttachment) {
        val audioAttachment = firstAttachment
        println(audioAttachment)
    }

    val attachment: Attachment = VideoAttachment(video = Video(1, 2, "D", 3, 4))
    println(attachment.type)
}