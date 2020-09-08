package com.example.recycleview.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recycleview.R
import com.example.recycleview.ui.PostsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, PostsFragment())
            commit()
        }
    }
}