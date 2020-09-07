package com.example.recycleview.base.Comments

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface   CommentDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(comment: CommentsDB)

    @Update()
    suspend fun update(comment: CommentsDB)

    @Query("DELETE FROM comment WHERE id= :id")
    suspend fun delete(id: Int?)

    @Query("DELETE FROM comment")
    suspend fun deleteAllComment()

    @Query("SELECT * FROM comment ORDER BY id")
    fun getCommentList():LiveData<List<CommentsDB>>

    @Query("UPDATE comment SET body = :newBody WHERE id = :id")
    fun updateComment(id: Int?, newBody: String?)


}