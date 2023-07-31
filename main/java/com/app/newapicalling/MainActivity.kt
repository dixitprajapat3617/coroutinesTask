package com.app.newapicalling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.room.Room
import com.app.newapicalling.adapter.UserListAdapter
import com.app.newapicalling.apiservice.ApiService
import com.app.newapicalling.database.AppDatabase
import com.app.newapicalling.databinding.ActivityMainBinding
import com.app.newapicalling.model.Data
import com.app.newapicalling.model.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var retrofit: Retrofit
    lateinit var service: ApiService
    lateinit var db:AppDatabase

    lateinit var madapter:UserListAdapter
    private var namelist= mutableListOf<Data>()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db=Room.databaseBuilder(this,AppDatabase::class.java, name = "dixit.db").fallbackToDestructiveMigration().build()



        retrofit=Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service=retrofit.create(ApiService::class.java)

        madapter= UserListAdapter(this,namelist)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter=madapter
        loadUserDatil()

    }

    private fun loadUserDatil() {
        service.getUserList().enqueue(object :Callback<UserData>{
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                if (response.isSuccessful){
                    var res=response.body()
                    namelist=res!!.data
                    madapter.setitem(namelist)
                  inseruser(namelist)



                    }
                }




            override fun onFailure(call: Call<UserData>, t: Throwable) {
                Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }



    private fun inseruser(namelist: MutableList<Data>) {
        CoroutineScope(Dispatchers.IO).launch {
            var datadao=db.userDao()
            for (data in namelist){
               datadao.insertAll(data)
            }
        }
    }

    /*private fun insertData(namelist: MutableList<Data>) {
        CoroutineScope(Dispatchers.IO).launch {
            // Get a reference to the database instance
            val dataDao = db.userDao()

            // Insert the data into the database
            for (data in namelist) {
                dataDao.insertAll(data)
            }
        }
    }*/
}