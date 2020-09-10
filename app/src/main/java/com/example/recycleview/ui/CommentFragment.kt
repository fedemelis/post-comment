package com.example.recycleview.ui

import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleview.R
import com.example.recycleview.base.Comments.CommentsDB
import com.example.recycleview.base.ImageBitmapString
import com.example.recycleview.base.Photos.Photo
import com.example.recycleview.common.Common
import com.example.recycleview.common.PostAdapterMultipleViewType
import com.example.recycleview.model.CommentViewModel
import com.example.recycleview.model.PostViewModel
import kotlinx.android.synthetic.main.fragment_comments_recycler.*
import kotlinx.android.synthetic.main.comment_line.*

class CommentFragment() : Fragment(R.layout.fragment_comments_recycler) {

    private val commentViewModel: CommentViewModel by viewModels()
    private val imageConverter = ImageBitmapString()
    private var myMutableListOfComment : MutableList<PostAdapterMultipleViewType> = arrayListOf()
    var adapter = CommentAdapter(
    myMutableListOfComment
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var layoutManager = LinearLayoutManager(context)
        commentRecycler.layoutManager = layoutManager

        commentViewModel.getCommentList()

        commentViewModel.allComments.observe(viewLifecycleOwner, Observer {
            if(adapter.myListOfComments().isEmpty()) {
                for (line in it){
                    if (line.photo != null){
                        adapter.myListOfComments().add(Photo(imageConverter.stringToBitMap(line.photo)))
                        commentRecycler.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                    else{
                        adapter.myListOfComments().add(CommentsDB(line.idComment, line.commentBody, null))
                        commentRecycler.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            commentRecycler.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        btnCam.setOnClickListener{
            val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val REQUEST_CODE = 42
            startActivityForResult(takePicture, REQUEST_CODE)
        }

        clearCommentsButton.setOnClickListener{
            commentViewModel.deleteAllComments()
            commentViewModel.getCommentList()
            myMutableListOfComment.clear()
            adapter.notifyDataSetChanged()
        }

        addTextCommentButton.setOnClickListener{
            var myNewComment = CommentsDB(generateNumb(), etNewComment.text.toString(), null)
            commentViewModel.insert(myNewComment)
            myMutableListOfComment.add(myNewComment)
            commentViewModel.getCommentList()
            adapter.notifyDataSetChanged()
        }
    }

    private fun generateNumb() : Int{
        return (10..100).random()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val takenImage = data?.extras?.get("data") as Bitmap
        var myNewComment = CommentsDB(generateNumb(), null, imageConverter.bitMapToString(takenImage))
        commentViewModel.insert(myNewComment)
        adapter.myListOfComments().add(Photo(takenImage))
        commentViewModel.getCommentList()

        super.onActivityResult(requestCode, resultCode, data)
    }

}