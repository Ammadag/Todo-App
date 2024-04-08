package com.example.taskmanager.roomdb.recyclerView

import com.example.taskmanager.roomdb.Room.UserInfo

interface OnItemClick {

    fun onUpdate(userinfo: UserInfo)
    fun onDelete(id: Int)
}