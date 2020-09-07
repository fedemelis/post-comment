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

class RecyclerFragment : Fragment(R.layout.fragment_recycler) {

    /*companion object{
        var postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
}*/

    //lateinit var postViewModel: PostViewModel
    private val postViewModel: PostViewModel by viewModels()
    //var addTaskFragment = AddTaskFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lateinit var layoutManager : LinearLayoutManager

        layoutManager = LinearLayoutManager(context)
        todoList.layoutManager = layoutManager

        var adapter = PostAdapter(
            requireContext(),
            Common.myMutableListOfPost
        )

        //postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        /*if (Common.firstPost){
            postViewModel.insert(PostsDB(1, "DemoPostTitolo1", "DemoPostCommento1"))
            postViewModel.insert(PostsDB(2, "DemoPostTitolo2", "DemoPostCommento1"))
            postViewModel.insert(PostsDB(3, "DemoPostTitolo3", "DemoPostCommento1"))
            postViewModel.insert(PostsDB(4, "DemoPostTitolo4", "DemoPostCommento1"))
            postViewModel.insert(PostsDB(5, "DemoPostTitolo5", "DemoPostCommento1"))
            postViewModel.insert(PostsDB(6, "DemoPostTitolo6", "DemoPostCommento1"))
            Common.firstPost = false
        }*/

        postViewModel.allPosts.observe(viewLifecycleOwner, Observer {
            if(adapter.myListOfPosts().isEmpty()) {
                adapter.myListOfPosts().addAll(it)
                todoList.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            todoList.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        //populate()
        postViewModel.getPostList()

        /*val item = object : SwipeToDelete(requireContext(), 0, ItemTouchHelper.RIGHT) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val dataPositionToast = viewHolder.adapterPosition
                /*val printDeletedItemToast = Toast.makeText(
                    context!!,
                    //"Hai cancellato: " + adapter.myListOfPosts()[dataPositionToast].postTitle,
                    Toast.LENGTH_SHORT
                )
                printDeletedItemToast.show()
                adapter.deleteItemFromListAt(viewHolder.adapterPosition)*/
            }
        }

        val slideToEdit = object : SwipeToDelete(requireContext(), 0, ItemTouchHelper.LEFT) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val positionForEdit = viewHolder.adapterPosition
                //currentTitle = adapter.myListOfPosts()[viewHolder.adapterPosition].postTitle
                //currentBody = adapter.myListOfPosts()[viewHolder.adapterPosition].postDescription
                changeFragment(positionForEdit)
            }
        }

        val itemTouchHelper = ItemTouchHelper(item)
        itemTouchHelper.attachToRecyclerView(todoList)

        val itemTouchHelperLeftSlide = ItemTouchHelper(slideToEdit)
        itemTouchHelperLeftSlide.attachToRecyclerView(todoList)*/

        btnOpenNewTaskAdd.setOnClickListener {
            Common.modify = false
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
}