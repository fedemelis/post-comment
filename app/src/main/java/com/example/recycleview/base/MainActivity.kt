package com.example.recycleview.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recycleview.R
import com.example.recycleview.ui.CommentFragment
import com.example.recycleview.ui.RecyclerFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, RecyclerFragment())
            commit()
        }
    }
}