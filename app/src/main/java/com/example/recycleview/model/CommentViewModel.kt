package com.example.recycleview.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.recycleview.base.Comments.CommentRepository
import com.example.recycleview.base.Comments.CommentsDB
import com.example.recycleview.base.Comments.CommentsDataBase
import com.example.recycleview.base.Posts.PostRepository
import com.example.recycleview.base.Posts.PostsDB
import com.example.recycleview.base.Posts.PostsDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommentViewModel(application: Application) : AndroidViewModel(application) {

    val repository: CommentRepository
    val allComments: LiveData<List<CommentsDB>>

    init {
        val commentDao = CommentsDataBase.getCommentsDB(application).commentDAO()
        repository = CommentRepository(commentDao)
        allComments = repository.allComments
    }

    fun insert(comment: CommentsDB){
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(comment)
        }
    }

    fun getCommentList() = viewModelScope.launch(Dispatchers.IO) {
        repository.getCommentList()
    }

    fun delete(id: Int?){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(id)
        }
    }

    fun deleteAllComments(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllComment()
        }
    }

    fun updatePost(id: Int?, newBody: String?){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateComment(id, newBody)
        }
    }
}