package com.example.recycleview.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.text.set
import androidx.fragment.app.viewModels
import com.example.recycleview.R
import com.example.recycleview.base.Posts.PostsDB
import com.example.recycleview.common.Common
import com.example.recycleview.model.PostViewModel
import kotlinx.android.synthetic.main.fragment_add_task.*

class AddOrModifyPostFragment(private val adapter: PostAdapter, private var myMutableListOfPosts: MutableList<PostsDB>, private var position: Int) : Fragment(R.layout.fragment_add_task){

    private val postViewModel: PostViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (position != -1){
            etTaskTitle.setText(myMutableListOfPosts[position].postTitle)
            etTaskDescription.setText(myMutableListOfPosts[position].postBody)
        }

        btnConfirmAddNewTask.setOnClickListener{
            if (position != -1){
                postViewModel.updatePost(myMutableListOfPosts[position].idPost, etTaskTitle.text.toString(), etTaskDescription.text.toString())
                myMutableListOfPosts[position] =
                    PostsDB(
                        myMutableListOfPosts[position].idPost,
                        etTaskTitle.text.toString(),
                        etTaskDescription.text.toString()
                    )
                adapter.notifyDataSetChanged()
                this.requireActivity().onBackPressed()
            }
            else{
                val randomNumber  = (10..100).random()
                myMutableListOfPosts.add(PostsDB(randomNumber, etTaskTitle.text.toString(), etTaskDescription.text.toString()))
                postViewModel.insert(PostsDB(randomNumber, etTaskTitle.text.toString(), etTaskDescription.text.toString()))
                adapter.notifyDataSetChanged()
                etTaskTitle.text.clear()
                etTaskDescription.text.clear()
                this.requireActivity().onBackPressed()
            }
        }

        btnBackToList.setOnClickListener{
            etTaskTitle.text.clear()
            etTaskDescription.text.clear()
            this.requireActivity().onBackPressed()
        }
    }
}