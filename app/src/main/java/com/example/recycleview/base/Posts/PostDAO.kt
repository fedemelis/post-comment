package com.example.recycleview.base.Posts

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface   PostDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(post : PostsDB)

    @Update()
    suspend fun update(post : PostsDB)

    @Query("DELETE FROM post WHERE id= :id")
    suspend fun delete(id: Int?)

    @Query("DELETE FROM post")
    suspend fun deleteAllPost()

    @Query("SELECT * FROM post")
    fun getPostList():LiveData<List<PostsDB>>

    @Query("UPDATE post SET title = :newTitle, description = :newDescription WHERE id = :id")
    fun updatePost(id: Int?, newTitle: String?, newDescription: String?)
    /*@Query("DELETE FROM post")
    suspend fun deleteAllPosts():List<PostsDB>*/

}