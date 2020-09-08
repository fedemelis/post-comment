package com.example.recycleview.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview.R
import com.example.recycleview.base.Comments.CommentsDB
import kotlinx.android.synthetic.main.comment_line.view.*


class CommentAdapter(private var listOfComments: MutableList<CommentsDB>) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.comment_line, parent, false)
        return CommentViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int {
        return listOfComments.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.description.text = listOfComments[position].commentBody
    }

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var description : TextView

        init {
            description = itemView.comment
        }
    }

    fun myListOfComments() : MutableList<CommentsDB>{
        return listOfComments
    }



}