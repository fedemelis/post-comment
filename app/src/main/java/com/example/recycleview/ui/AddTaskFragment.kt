package com.example.recycleview.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.recycleview.R
import com.example.recycleview.base.Posts.PostsDB
import com.example.recycleview.common.Common
import com.example.recycleview.model.PostViewModel
import kotlinx.android.synthetic.main.fragment_add_task.*

class AddTaskFragment : Fragment(R.layout.fragment_add_task) {

    private var idPosition=-1
    private val postViewModel: PostViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        var adapter = PostAdapter(
            requireContext(),
            Common.myMutableListOfPost
        )

        btnConfirmAddNewTask.setOnClickListener{
            //var recyclerFragment = RecyclerFragment()

            if (Common.modify){
                //recyclerFragment.updateReceiver(Common.myMutableListOfPost[Common.currentPosition].idPost, etTaskTitle.text.toString(), etTaskDescription.text.toString())
                postViewModel.updatePost(Common.myMutableListOfPost[Common.currentPosition].idPost, etTaskTitle.text.toString(), etTaskDescription.text.toString())
                Common.myMutableListOfPost[Common.currentPosition] =
                    PostsDB(
                        Common.myMutableListOfPost[Common.currentPosition].idPost,
                        etTaskTitle.text.toString(),
                        etTaskDescription.text.toString()
                    )
                adapter.notifyDataSetChanged()
                this.requireActivity().onBackPressed()
            }
            else{
                val randomNumber  = (10..100).random()
                //recyclerFragment.insertReciver(PostsDB(randomNumber, etTaskTitle.text.toString(), etTaskDescription.text.toString()))
                Common.myMutableListOfPost.add(PostsDB(randomNumber, etTaskTitle.text.toString(), etTaskDescription.text.toString()))
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

    override fun onResume() {
        super.onResume()

        if(idPosition!=-1)
        {
            etTaskTitle.setText(Common.currentTitle)
            etTaskDescription.setText(Common.currentBody)
        }
    }

    fun editTask(position:Int){
        idPosition=position
    }
}