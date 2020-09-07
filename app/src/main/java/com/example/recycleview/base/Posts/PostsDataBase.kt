package com.example.recycleview.base.Posts

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PostsDB::class], version = 1, exportSchema = false)
abstract class PostsDataBase : RoomDatabase(){

    abstract fun postDAO() : PostDAO

    companion object{
        @Volatile
        private var INSTANCE : PostsDataBase? = null
        fun getPostsDB(context: Context) : PostsDataBase {
            val tmpINSTANCE =
                INSTANCE
            if (tmpINSTANCE != null){
                return tmpINSTANCE
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, PostsDataBase::class.java, "post").build()
                INSTANCE = instance
                return instance
                }
        }
    }

}