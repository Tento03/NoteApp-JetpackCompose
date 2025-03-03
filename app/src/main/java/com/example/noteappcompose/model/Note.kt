package com.example.noteappcompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "noteAppCompose")
data class Note(
    @PrimaryKey
    var id:String,var title:String,var content:String)
