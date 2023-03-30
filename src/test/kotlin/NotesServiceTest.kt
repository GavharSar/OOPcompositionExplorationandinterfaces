import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import ru.netology.*

class NotesServiceTest {
    val note1 = Note(1, 2, 3, "Petya", "text", comment = mutableListOf<Comment>(), attachment = emptyArray<Attachment>())
    val note2 = Note(4,7,5, "Vanya", "content", comment = mutableListOf<Comment>(), attachment = emptyArray<Attachment>())
    val comment = Comment(1, 2, "hello")

    @Before
    fun clearBeforeTest() {
        NotesService.clear()
    }

    @Test
    fun addNotZero() {
        NotesService.add(note2)
        val result = note2.id
        assertEquals(4, result)
    }

    @Test
    fun editExistingId() {
        NotesService.add(note2)
        val update = Note(4,7,5, "Vanya", "content", comment = mutableListOf<Comment>(comment), attachment = emptyArray<Attachment>())
        val result = NotesService.edit(update)
        assertTrue(result)
    }

    @Test
    fun editNotExistingId() {
        NotesService.add(note2)
        val update = Note(49,7,5, "Vanya", "content", comment = mutableListOf<Comment>(comment), attachment = emptyArray<Attachment>())
        val result = NotesService.edit(update)
        assertFalse(result)
    }

    @Test
    fun getByIdTrue() {
        NotesService.add(note1)
        NotesService.getById(1)
    }

    @Test(expected = PostNotFoundException::class)
    fun getByIdException() {
        NotesService.add(note1)
        NotesService.getById(51)
    }

    @Test
    fun deleteTrue() {
        NotesService.add(note2)
        val result = NotesService.delete(4)
        assertTrue(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun deleteException() {
        NotesService.add(note1)
        NotesService.delete(55)
    }

    @Test
    fun createCommentId() {
        NotesService.add(note2)
        NotesService.createComment(4, comment)
    }

    @Test(expected = PostNotFoundException::class)
    fun createCommentNotId() {
        NotesService.add(note2)
        NotesService.createComment(5, comment)
    }

    @Test
    fun editCommentTrue() {
        NotesService.add(note1)
        NotesService.createComment(1, comment)
        val update = Comment(1, 2, "buy")
        val result = NotesService.editComment(update)
        assertTrue(result)
    }

    @Test
    fun editCommentFalseId() {
        NotesService.add(note2)
        NotesService.createComment(4, comment)
        val update = Comment(5, 2, "buy")
        val result = NotesService.editComment(update)
        assertFalse(result)
    }

    @Test
    fun editCommentFalseDel() {
        NotesService.add(note2)
        NotesService.createComment(4, comment = Comment(25, 36, "S", true))
        val update = Comment(25, 36, "buy", true)
        val result = NotesService.editComment(update)
        assertFalse(result)
    }

    @Test
    fun getComments() {
        NotesService.add(note2)
        NotesService.createComment(4, comment)
        NotesService.getComments(4)
    }

    @Test(expected = PostNotFoundException::class)
    fun getCommentsNo() {
        NotesService.add(note2)
        NotesService.createComment(4, comment)
        NotesService.getComments(78)
    }

    @Test
    fun deleteCommentTrue() {
        NotesService.add(note1)
        NotesService.createComment(1, comment)
        val result = NotesService.deleteComment(1)
        assertTrue(result)
    }

    @Test(expected = NotCommentIdException::class)
    fun deleteCommentNotCommentIdException() {
        NotesService.add(note1)
        NotesService.createComment(1, comment)
        NotesService.deleteComment(63)
    }

    @Test(expected = DeletedCommentException::class)
    fun deleteCommentDeletedCommentException() {
        NotesService.add(note1)
        NotesService.createComment(1, comment = Comment(56, 45, "G", true))
        NotesService.deleteComment(56)
    }

    @Test
    fun restoreCommentTrue() {
        NotesService.add(note1)
        NotesService.createComment(1, comment = Comment(56, 45, "G", true))
        val result = NotesService.restoreComment(56)
        assertTrue(result)
    }

    @Test
    fun restoreCommentFalseId() {
        NotesService.add(note1)
        NotesService.createComment(1, comment = Comment(56, 45, "G", true))
        val result = NotesService.restoreComment(5)
        assertFalse(result)
    }

    @Test
    fun restoreCommentFalseDeleted() {
        NotesService.add(note1)
        NotesService.createComment(1, comment = Comment(56, 45, "G", true))
        val result = NotesService.restoreComment(1)
        assertFalse(result)
    }
}