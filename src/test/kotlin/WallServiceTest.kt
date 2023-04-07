package ru.netology

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {
    val post1 = Post(1, 2, 3, "Petya", "text", comment = mutableListOf<Comment>(), attachment = emptyArray<Attachment>())
    val post2 = Post(4,7,5, "Vanya", "content", comment = mutableListOf<Comment>(), attachment = emptyArray<Attachment>())
    val comment = Comment(1, 2, "hello")

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addNotZero() {
        WallService.add(post2)
        val result = post2.id
        assertEquals(4, result)
    }

    @Test
    fun editExistingId() {
        WallService.add(post2)
        val update = Post(4,7,5, "Vanya", "content", comment = mutableListOf<Comment>(comment), attachment = emptyArray<Attachment>())
        val result = WallService.edit(update)
        assertTrue(result)
    }

    @Test
    fun editNotExistingId() {
        WallService.add(post2)
        val update = Post(49,7,5, "Vanya", "content", comment = mutableListOf<Comment>(comment), attachment = emptyArray<Attachment>())
        val result = WallService.edit(update)
        assertFalse(result)
    }

    @Test
    fun getByIdTrue() {
        WallService.add(post1)
        WallService.getById(1)
    }

    @Test(expected = PostNotFoundException::class)
    fun getByIdException() {
        WallService.add(post1)
        WallService.getById(51)
    }

    @Test
    fun deleteTrue() {
        WallService.add(post2)
        val result = WallService.delete(4)
        assertTrue(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun deleteException() {
        WallService.add(post2)
        WallService.delete(55)
    }

    @Test
    fun createCommentId() {
        WallService.add(post2)
        WallService.createComment(4, comment)
    }

    @Test(expected = PostNotFoundException::class)
    fun createCommentNotId() {
        WallService.add(post2)
        WallService.createComment(5, comment)
    }

    @Test
    fun editCommentTrue() {
        WallService.add(post1)
        WallService.createComment(1, comment)
        val update = Comment(1, 2, "buy")
        val result = WallService.editComment(update)
        assertTrue(result)
    }

    @Test
    fun editCommentFalseId() {
        WallService.add(post1)
        WallService.createComment(1, comment)
        val update = Comment(5, 2, "buy")
        val result = WallService.editComment(update)
        assertFalse(result)
    }

    @Test
    fun editCommentFalseDeleted() {
        WallService.add(post1)
        WallService.createComment(1, comment = Comment(55, 26, "W", true))
        val update = Comment(55, 26, "buy", true)
        val result = WallService.editComment(update)
        assertFalse(result)
    }

    @Test
    fun getComments() {
        WallService.add(post2)
        WallService.createComment(4, comment)
        WallService.getComments(4)
    }

    @Test(expected = PostNotFoundException::class)
    fun getCommentsNo() {
        WallService.add(post2)
        WallService.createComment(4, comment)
        WallService.getComments(78)
    }

    @Test
    fun deleteCommentTrue() {
        WallService.add(post2)
        WallService.createComment(4, comment)
        val result = WallService.deleteComment(1)
        assertTrue(result)
    }

    @Test(expected = NotCommentIdException::class)
    fun deleteCommentNotCommentIdException() {
        WallService.add(post2)
        WallService.createComment(4, comment)
        WallService.deleteComment(63)
    }

    @Test(expected = DeletedCommentException::class)
    fun deleteCommentDeletedCommentException() {
        WallService.add(post1)
        WallService.createComment(1, comment = Comment(35, 56, "F", true))
        WallService.deleteComment(35)
    }

    @Test
    fun restoreCommentTrue() {
        WallService.add(post1)
        WallService.createComment(1, comment = Comment(35, 56, "F", true))
        val result = WallService.restoreComment(35)
        assertTrue(result)
    }

    @Test
    fun restoreCommentFalseD() {
        WallService.add(post1)
        WallService.createComment(1, comment = Comment(35, 56, "F", true))
        val result = WallService.restoreComment(1)
        assertFalse(result)
    }

    @Test
    fun restoreCommentFalse() {
        WallService.add(post1)
        WallService.createComment(1, comment = Comment(35, 56, "F", true))
        val result = WallService.restoreComment(5)
        assertFalse(result)
    }

    @Test
    fun negativeCommentNotificationAdd() {
        WallService.add(post2)
        WallService.createComment(4, comment)
        val reportComment = ReportComment(1, 2, 0)
        WallService.negativeCommentNotification(reportComment)
    }

    @Test(expected = NotCommentIdException::class)
    fun negativeCommentNotificationNotCommentId() {
        WallService.add(post2)
        WallService.createComment(4, comment)
        val reportComment = ReportComment(2, 2, 0)
        WallService.negativeCommentNotification(reportComment)
    }

    @Test(expected = NotOwnerIdException::class)
    fun negativeCommentNotificationNotOwnerId() {
        WallService.add(post2)
        WallService.createComment(4, comment)
        val reportComment = ReportComment(1, 3, 0)
        WallService.negativeCommentNotification(reportComment)
    }

    @Test(expected = NotReasonException::class)
    fun negativeCommentNotificationNotReason() {
        WallService.add(post2)
        WallService.createComment(4, comment)
        val reportComment = ReportComment(1, 2, 9)
        WallService.negativeCommentNotification(reportComment)
    }
}