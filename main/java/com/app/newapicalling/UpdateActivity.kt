package com.app.newapicalling

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.newapicalling.databinding.ActivityUpdateBinding
import com.app.newapicalling.model.Data

class UpdateActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_update)
       var name= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("USER",Data::class.java)
        }else{
            intent.getParcelableExtra("USER")
        }
        if (name!=null){
            binding.name.setText(name.first_name)
            binding.name.setText(name.last_name)
            binding.email.setText(name.email)
        }


    }


}