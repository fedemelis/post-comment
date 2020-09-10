package com.example.recycleview.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview.R
import com.example.recycleview.base.Comments.CommentsDB
import com.example.recycleview.base.Photos.Photo
import com.example.recycleview.common.PostAdapterMultipleViewType
import kotlinx.android.synthetic.main.comment_line.view.*
import kotlinx.android.synthetic.main.image_line.view.*
import org.w3c.dom.Comment


class CommentAdapter(private var listOfComments: MutableList<PostAdapterMultipleViewType>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1){
            var itemView = LayoutInflater.from(parent.context).inflate(R.layout.comment_line, parent, false)
            return TextCommentViewHolder(
                itemView
            )
        }
        else{
            var itemView = LayoutInflater.from(parent.context).inflate(R.layout.image_line, parent, false)
            return ImageCommentViewHolder(
                itemView
            )
        }
    }

    override fun getItemCount(): Int {
        return listOfComments.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TextCommentViewHolder) {
            holder.bind(listOfComments[position] as CommentsDB)
        } else if (holder is ImageCommentViewHolder) {
            holder.bind(listOfComments[position] as Photo)
        }
    }

        //onbindViewHolder
        //holder.description.text = listOfComments[position].commentBody

    class TextCommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(commentsDB: CommentsDB){
            itemView.comment.text = commentsDB.commentBody
        }
    }
    class ImageCommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(photo: Photo){
            itemView.photoContainer.setImageBitmap(photo.myBitmap)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (listOfComments[position]) {
            is CommentsDB -> 1
            is Photo -> 0
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    fun myListOfComments() : MutableList<PostAdapterMultipleViewType>{
        return listOfComments
    }



}