package com.example.recycleview.base.Posts

import androidx.lifecycle.LiveData

class PostRepository(private val postDAO: PostDAO) {

    val allPosts: LiveData<List<PostsDB>> = postDAO.getPostList()

    suspend fun insert(post: PostsDB) {
        postDAO.insert(post)
    }

    suspend fun getPostList() {
        postDAO.getPostList()
    }

    suspend fun delete(id: Int?){
        postDAO.delete(id)
    }

    suspend fun deleteAllPost(){
        postDAO.deleteAllPost()
    }

    suspend fun updatePost(id: Int?, newTitle: String?, newDescription: String?){
        postDAO.updatePost(id, newTitle, newDescription)
    }
}