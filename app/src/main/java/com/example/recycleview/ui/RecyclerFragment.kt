package com.example.recycleview.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleview.R
import com.example.recycleview.base.Comments.CommentsDB
import com.example.recycleview.base.Posts.PostsDB
import com.example.recycleview.common.Common
import com.example.recycleview.model.PostViewModel
import kotlinx.android.synthetic.main.fragment_recycler.*

class RecyclerFragment : Fragment(R.layout.fragment_recycler), BottomSheetFragment.BottomSheetActionListener, PostAdapter.AdapterClickListener {

    private val postViewModel: PostViewModel by viewModels()
    var myMutableListOfPost = arrayListOf<PostsDB>()
    var adapter = PostAdapter(
        myMutableListOfPost,
        this
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var layoutManager : LinearLayoutManager = LinearLayoutManager(context)
        todoList.layoutManager = layoutManager

        postViewModel.allPosts.observe(viewLifecycleOwner, Observer {
            if(adapter.myListOfPosts().isEmpty()) {
                adapter.myListOfPosts().addAll(it)
                todoList.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            todoList.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        postViewModel.getPostList()

        btnOpenNewTaskAdd.setOnClickListener {
            transictionFragment()
        }
    }

    fun changeFragment(position:Int) {
        this.requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, AddTaskFragment(adapter, myMutableListOfPost, position))
            addToBackStack(null)
            commit()
        }
    }

    fun transictionFragment(){
        this.requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, AddTaskFragment(adapter, myMutableListOfPost, 1000))
            addToBackStack(null)
            commit()
        }
    }

    override fun onClickModify(position: Int) {
        changeFragment(position)
    }

    override fun onClickDelete(position: Int) {
        postViewModel.delete(myMutableListOfPost[position].idPost)
        adapter.myListOfPosts().removeAt(position)
        adapter.notifyDataSetChanged()
        postViewModel.getPostList()
    }

    override fun showComments() {
        this.requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, CommentFragment())
            addToBackStack(null)
            commit()
        }
    }

    override fun showBottomSheet(position: Int) {
        BottomSheetFragment(position, this).show(this.requireActivity().supportFragmentManager, "BottomSheetDialog")
    }
}