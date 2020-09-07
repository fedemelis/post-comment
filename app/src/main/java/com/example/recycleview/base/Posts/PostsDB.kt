package com.example.recycleview.base.Posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class PostsDB(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var idPost : Int? = null,

    @ColumnInfo(name = "title")
    var postTitle : String? = null,

    @ColumnInfo(name = "description")
    var postBody : String? = null


)
