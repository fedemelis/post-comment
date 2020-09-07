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



class PostAdapter(private val context: Context, private var postList: MutableList<PostsDB>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.todo_line, parent, false)
        return PostViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = postList[position].postTitle
        holder.description.text = postList[position].postBody
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title : TextView
        var description : TextView

        init {
            title = itemView.title
            description = itemView.description
            itemView.setOnClickListener{
                Common.currentPosition = adapterPosition
                val commentFragment =
                    CommentFragment()
                (it.context as FragmentActivity).supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, commentFragment)
                    addToBackStack(null)
                    commit()
                }
            }
            itemView.setOnLongClickListener{
                Common.currentPosition = adapterPosition
                BottomSheetFragment().show((it.context as FragmentActivity).supportFragmentManager, "BottomSheetDialog")
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