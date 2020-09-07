package com.example.recycleview.ui

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleview.R
import com.example.recycleview.base.Comments.CommentsDB
import com.example.recycleview.common.Common
import com.example.recycleview.model.CommentViewModel
import kotlinx.android.synthetic.main.fragment_comments_recycler.*
import kotlinx.android.synthetic.main.comment_line.*

class CommentFragment : Fragment(R.layout.fragment_comments_recycler) {

    lateinit var commentViewModel: CommentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lateinit var layoutManager : LinearLayoutManager
        var adapter : CommentAdapter

        var myMutableListOfComment : MutableList<CommentsDB> = arrayListOf()

        commentViewModel = ViewModelProvider(this).get(CommentViewModel::class.java)


        layoutManager = LinearLayoutManager(context)
        commentRecycler.layoutManager = layoutManager
        adapter = CommentAdapter(
            requireContext(),
            myMutableListOfComment
        )

        commentViewModel.allComments.observe(viewLifecycleOwner, Observer {
            if(adapter.myListOfComments().isEmpty()) {
                adapter.myListOfComments().addAll(it)
                commentRecycler.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            commentRecycler.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        /*if (Common.firstCom){
            commentViewModel.insert(CommentsDB(1, "DemoCommenti1"))
            commentViewModel.insert(CommentsDB(2, "DemoCommenti2"))
            commentViewModel.insert(CommentsDB(3, "DemoCommenti3"))
            commentViewModel.insert(CommentsDB(4, "DemoCommenti4"))
            commentViewModel.insert(CommentsDB(5, "DemoCommenti5"))
            commentViewModel.insert(CommentsDB(6, "DemoCommenti6"))
            Common.firstCom = false
        }*/

        commentViewModel.getCommentList()

        //adapter.myListOfComments().add(commentsDB)

        /*fun getAllComments() {
            retrofitService.getCommentList(currentPosition.toString()).enqueue(object : retrofit2.Callback<MutableList<CommentsDB>>{
                override fun onFailure(call: Call<MutableList<CommentsDB>>, t: Throwable) {

                }
                override fun onResponse(call: Call<MutableList<CommentsDB>>, response: Response<MutableList<CommentsDB>>) {
                    adapter.myListOfComments().addAll(response.body() as MutableList<CommentsDB>)
                    commentRecycler.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            })
        }

        getAllComments()*/

        btnCam.setOnClickListener{
            val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val REQUEST_CODE = 42
            startActivityForResult(takePicture, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val takenImage = data?.extras?.get("data") as Bitmap
        imageView.setImageBitmap(takenImage)

        super.onActivityResult(requestCode, resultCode, data)
    }

}