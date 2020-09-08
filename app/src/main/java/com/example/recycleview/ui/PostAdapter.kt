package com.example.recycleview.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview.R
import com.example.recycleview.base.Posts.PostsDB
import com.example.recycleview.common.Common
import kotlinx.android.synthetic.main.todo_line.view.*



class PostAdapter(private val postList: MutableList<PostsDB>, private val listener: AdapterClickListener) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {



    interface AdapterClickListener{
        fun showComments()
        fun showBottomSheet(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.todo_line, parent, false)
        return PostViewHolder(itemView, listener)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = postList[position].postTitle
        holder.description.text = postList[position].postBody
    }

    class PostViewHolder(itemView: View, listener: AdapterClickListener) : RecyclerView.ViewHolder(itemView) {
        var title : TextView
        var description : TextView

        init {
            title = itemView.title
            description = itemView.description
            itemView.setOnClickListener{
                listener.showComments()
            }
            itemView.setOnLongClickListener{
                listener.showBottomSheet(adapterPosition)
                return@setOnLongClickListener true
            }
        }
    }

    fun myListOfPosts() : MutableList<PostsDB>{
        return postList
    }

    fun deleteItemFromListAt(position: Int){
        myListOfPosts().removeAt(position)
        notifyDataSetChanged()
    }
}