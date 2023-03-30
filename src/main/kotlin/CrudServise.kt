package ru.netology

interface CrudServise<E> {
    fun add(entity: E): Int
    fun edit(entity: E): Boolean
    fun getById(id: Int): E
    fun get(): MutableList<E>
    fun delete(id: Int): Boolean

    fun createComment(id: Int, comment: Comment): Comment
    fun editComment(comment: Comment): Boolean
    fun getComments(id: Int): MutableList<Comment>
    fun deleteComment(id: Int): Boolean
    fun restoreComment(id: Int): Boolean



}