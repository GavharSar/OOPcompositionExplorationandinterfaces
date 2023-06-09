package ru.netology

object WallService : CrudServise<Post> {
    private var posts = mutableListOf<Post>()
    private var postsId = mutableSetOf<Int>() // private var lastId = 0
    private var comments = emptyArray<Comment>()
    private var reportsComment = emptyArray<ReportComment>()

    override fun add(entity: Post): Int {
        posts.add(entity) // posts += post.copy(id = ++lastId)
        postsId.add(entity.id)
        return entity.id
    }

    override fun edit(entity: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == entity.id) {
                posts[index] = entity.copy()
                return true
            }
        }
        return false
    }

    override fun getById(id: Int): Post {
        for (post in posts) {
            if (post.id == id) {
                return post
            }
        }
        return throw PostNotFoundException("No post with id $id")
    }

    override fun get(): MutableList<Post> {
        for (post in posts) {
            println(post)
        }
        println("------------------")
        return posts
    }

    override fun delete(id: Int): Boolean {
        for (post in posts) {
            if (post.id == id) {
                posts.remove(post)
                post.comment.clear()
                return true
            }
        }
        return throw PostNotFoundException("No post with id $id")
    }

    override fun createComment(id: Int, comment: Comment): Comment {
        for (post in posts) {
            if (id == post.id) {
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
        for (post in posts) {
            if (post.id == id) {
                for (comment in post.comment) {
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
        if (comments.firstOrNull { it.deleted != true } == null) {
            throw DeletedCommentException("It is deleted comment")
        }
        for ((index, comment) in comments.withIndex()) {
            comments[index] = comment.copy(id, deleted = true)
            return true
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

    fun negativeCommentNotification(reportComment: ReportComment): Int {
        if (comments.firstOrNull { it.id == reportComment.commentId } == null) {
            throw NotCommentIdException("No comment with ${reportComment.commentId}")
        }
        if (comments.firstOrNull { it.ownerId == reportComment.ownerId } == null) {
            throw NotOwnerIdException("No comment with ${reportComment.ownerId}")
        }
        if (reportComment.reason !in 0..8) {
            throw NotReasonException("No reason with number ${reportComment.reason}")
        }
        reportsComment += reportComment
        return 1
    }

    fun clear() {
        posts.clear()
        postsId.clear() // lastId = 0
        comments = emptyArray<Comment>()
    }
}
