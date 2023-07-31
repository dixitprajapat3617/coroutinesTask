package com.app.newapicalling.database

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import com.app.newapicalling.apiservice.ApiService
import com.app.newapicalling.model.Data
import com.app.newapicalling.userdao.UserDao

@Database(entities = [Data::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
   abstract fun userDao():UserDao
}
