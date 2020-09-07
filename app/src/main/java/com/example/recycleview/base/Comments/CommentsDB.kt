package com.example.recycleview.base.Comments

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment")
data class CommentsDB(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var idComment : Int? = null,

    @ColumnInfo(name = "body")
    var commentBody : String? = null



)
