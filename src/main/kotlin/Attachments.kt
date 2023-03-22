package ru.netology

class AudioAttachment(val audio: Audio) : Attachment {
    override val type: String = "audio"
}

class VideoAttachment(val video: Video) : Attachment {
    override val type: String = "video"
}

class PhotoAttachment(val photo: Photo) : Attachment {
    override val type: String = "photo"
}


class DocAttachment(val doc: Doc) : Attachment {
    override val type: String = "doc"
}

class LinkAttachment(val link: Link) : Attachment {
    override val type: String = "link"
}