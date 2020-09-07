package com.example.recycleview.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recycleview.R
import com.example.recycleview.ui.CommentFragment
import com.example.recycleview.ui.RecyclerFragment

//var recyclerFragment = RecyclerFragment()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, RecyclerFragment())
            commit()
        }

        /*retrofitService = Common.apiService
        layoutManager = LinearLayoutManager(this)
        todoList.layoutManager = layoutManager*/

        /*fun getAllPosts() {
            retrofitService.getPostList().enqueue(object : retrofit2.Callback<MutableList<Post>>{
                override fun onFailure(call: Call<MutableList<Post>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call<MutableList<Post>>, response: Response<MutableList<Post>>) {
                    adapter = PostAdapter(baseContext, response.body() as MutableList<Post>)
                    adapter.notifyDataSetChanged()
                    todoList.adapter = adapter

                }

            })
        }*/

        //getAllPosts()


       /* btnConfirm.setOnClickListener(){

            newTaskTitleValue = etTaskTitle.text.toString()
            newTaskDescriptionValue = etTaskDescription.text.toString()
            todo = TodoData(newTaskTitleValue, newTaskDescriptionValue, false )
            TODOList.add(todo)

            /*newTaskDescriptionValue = ""
            newTaskTitleValue = ""
            etTaskDescription.text.clear()
            etTaskTitle.text.clear()
            etTaskDescription.text.clear()
            todo = TodoData("", "", false)
            etTaskTitle.setText(null)
            etTaskDescription.setText(null)*/

            supportFragmentManager.beginTransaction().apply {
               replace(R.id.flFragment, recyclerFragment)
               commit()
           }
       }*/
    }
}