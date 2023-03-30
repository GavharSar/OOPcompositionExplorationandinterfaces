package ru.netology

class AudioAttachment(val audio: Audio) : Attachment {
    override val type: String = "audio"
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
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