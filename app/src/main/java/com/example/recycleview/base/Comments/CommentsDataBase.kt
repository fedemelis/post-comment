package com.example.recycleview.base.Comments

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CommentsDB::class], version = 1, exportSchema = false)
abstract class CommentsDataBase : RoomDatabase(){

    abstract fun commentDAO() : CommentDAO

    companion object{
        @Volatile
        private var INSTANCE : CommentsDataBase? = null
        fun getCommentsDB(context: Context) : CommentsDataBase {
            val tmpINSTANCE =
                INSTANCE
            if (tmpINSTANCE != null){
                return tmpINSTANCE
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, CommentsDataBase::class.java, "comments").build()
                INSTANCE = instance
                return instance
                }
        }
    }

}