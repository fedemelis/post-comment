package com.example.recycleview.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.recycleview.R
import com.example.recycleview.common.Common
import com.example.recycleview.model.PostViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet.*

class BottomSheetFragment() : BottomSheetDialogFragment() {

    var addTaskFragment = AddTaskFragment()
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var adapter = PostAdapter(
            requireContext(),
            Common.myMutableListOfPost
        )

        modifyLine.setOnClickListener(){
            val positionForEdit =
                Common.currentPosition
            Common.currentTitle = adapter.myListOfPosts()[Common.currentPosition].postTitle
            Common.currentBody = adapter.myListOfPosts()[Common.currentPosition].postBody
            Common.modify = true
            changeFragment(positionForEdit)
            dismiss()
        }

        deleteLine.setOnClickListener{
            val deletingItemAlert = AlertDialog.Builder(context)
            val dataPos = Common.currentPosition
            deletingItemAlert.setTitle("Sei sicuro?")
            deletingItemAlert.setMessage("Vuoi cancellare questo post?")
            deletingItemAlert.setPositiveButton("Si") { _: DialogInterface, _: Int ->
                postViewModel.delete(Common.myMutableListOfPost[dataPos].idPost)
                adapter.myListOfPosts().removeAt(dataPos)
                adapter.notifyDataSetChanged()
            }
            deletingItemAlert.setNegativeButton("No") { _: DialogInterface, _: Int -> }
            deletingItemAlert.show()
            dismiss()
        }
    }

    fun changeFragment(position:Int) {
        transictionFragment()
        addTaskFragment.editTask(position)
    }

    fun transictionFragment(){
        this.requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, AddTaskFragment())
            addToBackStack(null)
            commit()
        }
    }

}