package ru.netology

object WallService {
    private var posts = emptyArray<Post>()
    private var nextId = mutableSetOf<Int>() // private var lastId = 0
    private var comments = emptyArray<Comment>()
    private var reportsComment = emptyArray<ReportComment>()

    fun add(post: Post): Post {
        posts += post // posts += post.copy(id = ++lastId)
        nextId.add(post.id)
        return posts.last()
    }

    fun update(postOrigin: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == postOrigin.id) {
                posts[index] = postOrigin.copy()
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        nextId.clear() // lastId = 0
    }

    fun printlnAll() {
        for (post in posts) {
            println(post)
        }
        println("------------------")
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        for (post in posts) {
            if (postId == post.id) {
                comments += comment
                return comments.last()
            }
        }
        return throw PostNotFoundException("No post with id $postId")
    }

    fun negativeCommentNotification(reportComment: ReportComment): Int {
        for (comment in comments) {
            try {
                if (comment.ownerId == reportComment.ownerId && comment.id == reportComment.commentId && reportComment.reason >= 0 && reportComment.reason <= 8) {
                    reportsComment += reportComment
                    return 1
                }
            } catch (e: NotOwnerIdException) {
                if (comment.ownerId != reportComment.ownerId) {
                    return throw NotOwnerIdException("No comment with ${reportComment.ownerId}")
                }
            } catch (e: NotCommentIdException) {
                if (comment.id != reportComment.commentId) {
                    return throw NotCommentIdException("No comment with ${reportComment.commentId}")
                }
            }
        }
        return throw NotReasonException("No reason with number ${reportComment.reason}")
    }
}
