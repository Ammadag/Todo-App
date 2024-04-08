package com.example.taskmanager.roomdb.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [UserInfo::class], version = 1, exportSchema = false)
abstract class UserDB : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {

        ///enhanced the creation of single instance of database while pointing out to a singleton database object "INSTANCE"
        @Volatile
        var _INSTANCE: UserDB? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): UserDB {
            if (_INSTANCE != null) return _INSTANCE!!
            synchronized(this) {
                _INSTANCE = Room
                    .databaseBuilder(
                        context,
                        UserDB::class.java,
                        "todo_database"
                    )
                    .build()
                return _INSTANCE!!
            }
        }
    }
}

//    companion object {
//        @Volatile
//        var dbInstance: UserDB? = null
//        fun getDbInstance(context: Context): UserDB {
//            if (dbInstance != null) {
//                return dbInstance!!
//            }
//          synchronized(this) {
//                dbInstance = Room.databaseBuilder
//               (context, UserDB::class.java, "MyDB")
//             .build()
//                return dbInstance
//         }
//        }
//    }

