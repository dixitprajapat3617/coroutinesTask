package com.app.newapicalling.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.newapicalling.UpdateActivity
import com.app.newapicalling.databinding.ItemUiDesignBinding
import com.app.newapicalling.model.Data
import com.bumptech.glide.Glide

class UserListAdapter(var context: Context,var userlist:MutableList<Data>):RecyclerView.Adapter<UserListAdapter.MyViewHoler>() {
    lateinit var binding: ItemUiDesignBinding
    class MyViewHoler(var binding: ItemUiDesignBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHoler {
        binding= ItemUiDesignBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHoler(binding)
    }

    override fun onBindViewHolder(holder: MyViewHoler, position: Int) {

        var name=userlist[position]
        holder.binding.tvName.text="${name.first_name},${name.last_name}"
        holder.binding.tvEmail.text="${name.email}"
        holder.binding.cardView.setOnClickListener {
            var intent=Intent(context,UpdateActivity::class.java)
            intent.putExtra("USER",name)
            context.startActivity(intent)
        }




        Glide
            .with(context)
            .load(name.avatar)
            .into(holder.binding.ivImage)

    }

    override fun getItemCount(): Int {
       return userlist.size
    }
    fun setitem(userlist: MutableList<Data>){
        this.userlist=userlist
        notifyDataSetChanged()
    }
}