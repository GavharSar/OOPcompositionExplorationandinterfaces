package ru.netology

object NotesService : CrudServise<Note> {
    private var notes = mutableListOf<Note>()
    private var notesId = mutableSetOf<Int>() // private var lastId = 0
    private var comments = emptyArray<Comment>()

    override fun add(entity: Note): Int {
        notes.add(entity)
        notesId.add(entity.id)
        return entity.id
    }

    override fun edit(entity: Note): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.id == entity.id) {
                notes[index] = entity.copy()
                return true
            }
        }
        return false
    }

    override fun getById(id: Int): Note {
        for (note in notes) {
            if (note.id == id) {
                return note
            }
        }
        return throw PostNotFoundException("No post with id $id")
    }

    override fun get(): MutableList<Note> {
        for (note in notes) {
            println(note)
        }
        println("------------------")
        return notes
    }

    override fun delete(id: Int): Boolean {
        for (note in notes) {
            if (note.id == id) {
                notes.remove(note)
                return true
            }
        }
        return throw PostNotFoundException("No post with id $id")
    }

    override fun createComment(id: Int, comment: Comment): Comment {
        for (note in notes) {
            if (id == note.id) {
                comments += comment
                return comments.last()
            }
        }
        return throw PostNotFoundException("No post with id $id")
    }

    override fun editComment(comment: Comment): Boolean {
        for ((index, commentOrigin) in comments.withIndex()) {
            if (commentOrigin.id == comment.id && comment.deleted == false) {
                comments[index] = comment.copy()
                return true
            }
        }
        return false
    }

    override fun getComments(id: Int): MutableList<Comment> {
        var commentList = mutableListOf<Comment>()
        for (note in notes) {
            if (note.id == id) {
                for (comment in note.comment) {
                    if (comment.deleted == false) {
                        commentList.add(comment)
                    } else {
                        break
                    }
                }
                return commentList
            }
        }
        return throw PostNotFoundException("No post with id $id")
    }

    override fun deleteComment(id: Int): Boolean {
        if (comments.firstOrNull { it.id == id } == null) {
            throw NotCommentIdException("No comment with $id")
        }
        if (comments.firstOrNull { it.deleted == false } == null) {
            throw DeletedCommentException("It is deleted comment")
        }
        for ((index, comment) in comments.withIndex()) {
            if (comment.id == id) {
                comments[index] = comment.copy(id, deleted = true)
                return true
            }
        }
        return false
    }

    override fun restoreComment(id: Int): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (comment.id == id && comment.deleted == true) {
                comments[index] = comment.copy(id, deleted = false)
                return true
            }
        }
        return false
    }

    fun clear() {
        notes.clear()
        notesId.clear() // lastId = 0
        comments = emptyArray<Comment>()
    }
}