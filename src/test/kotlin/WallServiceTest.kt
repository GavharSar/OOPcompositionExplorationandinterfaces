package ru.netology

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun updateExisting() {
        val post1 = Post(1, 2, 3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = null, views = View(0))
        val post2 = Post(4,7,3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = "post", views = View(10))
        WallService.add(post1)
        WallService.add(post2)
        val update = Post(4,7,3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = "post edit", views = View(15))
        val result = WallService.update(update)
        assertTrue(result)
    }

    @Test
    fun updateNotExisting() {
        val post1 = Post(1, 2, 3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = null, views = View(0))
        val post2 = Post(4,7,3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = "post", views = View(10))
        WallService.add(post1)
        WallService.add(post2)
        val update = Post(49,7,3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = "post edit", views = View(15))
        val result = WallService.update(update)
        assertFalse(result)
    }

    @Test
    fun addNotZero() {
        val post = Post(4,7,3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = "post edit", views = View(15))
        WallService.add(post)
        val result = post.id
        assertEquals(4, result)
    }

    @Test
    fun createCommentId() {
        val post = Post(4,7,3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = "post edit", views = View(15))
        WallService.add(post)
        WallService.createComment(4, comment = Comment(1, 2))
    }

    @Test(expected = PostNotFoundException::class)
    fun createCommentNotId() {
        val post = Post(4,7,3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = "post edit", views = View(15))
        WallService.add(post)
        WallService.createComment(5, comment = Comment(3,4))
    }

    @Test
    fun negativeCommentNotificationAdd() {
        var post2 = Post(4,7,3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = "post", views = View(10))
        WallService.add(post2)
        WallService.createComment(4, comment = Comment(1, 2))
        val reportComment = ReportComment(2, 3, 0)
    } // не работает

    @Test(expected = NotCommentIdException::class)
    fun negativeCommentNotificationNotCommentId() {
        var post2 = Post(4,7,3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = "post", views = View(10))
        WallService.add(post2)
        WallService.createComment(4, comment = Comment(1, 2))
        val reportComment = ReportComment(2, 2, 0)
    } // не работает

    @Test(expected = NotOwnerIdException::class)
    fun negativeCommentNotificationNotOwnerId() {
        var post2 = Post(4,7,3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = "post", views = View(10))
        WallService.add(post2)
        WallService.createComment(4, comment = Comment(1, 2))
        val reportComment = ReportComment(1, 3, 0)
    } // не работает

    @Test(expected = NotReasonException::class)
    fun negativeCommentNotificationNotReason() {
        var post2 = Post(4,7,3, replyOwnerId = 5, replyPostId = 6, authorName = "Petya", copyRight = "post", views = View(10))
        WallService.add(post2)
        WallService.createComment(4, comment = Comment(1, 2))
        val reportComment = ReportComment(1, 3, 9)
    } // не работает
}