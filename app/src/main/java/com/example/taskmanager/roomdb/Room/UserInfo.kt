package com.example.taskmanager.roomdb.Room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "userTable")
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String
)