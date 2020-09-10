package com.example.recycleview.base.Comments

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recycleview.common.PostAdapterMultipleViewType
import com.example.recycleview.ui.PostAdapter

@Entity(tableName = "comment")
data class CommentsDB(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var idComment : Int? = null,

    @ColumnInfo(name = "body")
    var commentBody : String? = null,

    @ColumnInfo(name = "photo")
    var photo : String? = null

): PostAdapterMultipleViewType
