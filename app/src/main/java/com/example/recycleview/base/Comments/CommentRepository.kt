package com.example.recycleview.base.Comments

import androidx.lifecycle.LiveData

class CommentRepository(private val commentDAO: CommentDAO) {

    val allComments: LiveData<List<CommentsDB>> = commentDAO.getCommentList()

    suspend fun insert(comment: CommentsDB) {
        commentDAO.insert(comment)
    }

    suspend fun getCommentList() {
        commentDAO.getCommentList()
    }

    suspend fun delete(id: Int?){
        commentDAO.delete(id)
    }

    suspend fun deleteAllComment(){
        commentDAO.deleteAllComment()
    }

    suspend fun updateComment(id: Int?, newBody: String?){
        commentDAO.updateComment(id, newBody)
    }
}