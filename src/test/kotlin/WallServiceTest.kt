package ru.netology

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {
    val post1 = Post(1, 2, 3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = null, views = View(0))
    val post2 = Post(4,7,3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = "post", views = View(10))
    val comment = Comment(1, 2)
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun updateExisting() {
        WallService.add(post1)
        WallService.add(post2)
        val update = Post(4,7,3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = "post edit", views = View(15))
        val result = WallService.update(update)
        assertTrue(result)
    }

    @Test
    fun updateNotExisting() {
        WallService.add(post1)
        WallService.add(post2)
        val update = Post(49,7,3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = "post edit", views = View(15))
        val result = WallService.update(update)
        assertFalse(result)
    }

    @Test
    fun addNotZero() {
        WallService.add(post2)
        val result = post2.id
        assertEquals(4, result)
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