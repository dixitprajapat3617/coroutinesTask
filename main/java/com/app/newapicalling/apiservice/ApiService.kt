package com.app.newapicalling.apiservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.app.newapicalling.model.Data
import com.app.newapicalling.model.UserData
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users?page=2")
    fun getUserList():Call<UserData>

}