package com.example.recycleview.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.recycleview.base.Posts.PostRepository
import com.example.recycleview.base.Posts.PostsDB
import com.example.recycleview.base.Posts.PostsDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : AndroidViewModel(application) {

    val repository: PostRepository
    val allPosts: LiveData<List<PostsDB>>

    init {
        val postsDAO = PostsDataBase.getPostsDB(application).postDAO()
        repository = PostRepository(postsDAO)
        allPosts = repository.allPosts
    }

    fun insert(post: PostsDB){
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(post)
        }
    }

    fun getPostList() = viewModelScope.launch(Dispatchers.IO) {
        repository.getPostList()
    }

    fun delete(id: Int?){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(id)
        }
    }

    fun deleteAllPost(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllPost()
        }
    }

    fun updatePost(id: Int?, newTitle: String?, newDescription: String?){
        viewModelScope.launch(Dispatchers.IO){
            repository.updatePost(id, newTitle, newDescription)
        }
    }
}