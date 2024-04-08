package com.example.taskmanager.roomdb.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taskmanager.roomdb.Room.UserInfo

@Dao
interface UserDao {
    @Query("SELECT * FROM userTable")
    fun getAllItem(): LiveData<List<UserInfo?>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTable(userInfo: UserInfo)

    @Update(UserInfo::class)
    suspend fun updateTable(userInfo: UserInfo)

    @Query("DELETE FROM userTable WHERE id = :id")
    suspend fun deleteById(id:Int)
}