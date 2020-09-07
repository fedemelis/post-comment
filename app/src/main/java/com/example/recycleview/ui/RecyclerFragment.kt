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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lateinit var layoutManager : LinearLayoutManager

        layoutManager = LinearLayoutManager(context)
        todoList.layoutManager = layoutManager

        var adapter = PostAdapter(
            requireContext(),
            Common.myMutableListOfPost
        )

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

    fun updateReceiver(id: Int?, newTitle:String?, newDescription:String?){
        postViewModel.updatePost(id, newTitle, newDescription)
    }

    fun insertReciver(post: PostsDB){
        postViewModel.insert(post)
    }

    fun deleteReceiver(id: Int?){
        postViewModel.delete(id)
    }

    fun changeFragment(position:Int) {
            transictionFragment()
            AddTaskFragment().editTask(position)
    }

    fun transictionFragment(){
        this.requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, AddTaskFragment())
            addToBackStack(null)
            commit()
        }
    }

    override fun onClickModify(position: Int) {
        transictionFragment()
    }

    override fun onClickDelete(position: Int) {
        postViewModel.delete(position)
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