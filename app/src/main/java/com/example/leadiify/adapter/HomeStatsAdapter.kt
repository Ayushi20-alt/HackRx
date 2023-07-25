package com.example.leadiify.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leadiify.R
import com.example.leadiify.databinding.HomeStatesLayoutBinding
import com.example.leadiify.model.home_stats_model

class HomeStatsAdapter(var data : ArrayList<home_stats_model>):RecyclerView.Adapter<HomeStatsAdapter.ViewHolder>(){
    inner class ViewHolder(var bind:HomeStatesLayoutBinding):RecyclerView.ViewHolder(bind.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater=HomeStatesLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(layoutInflater)

    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder)
        {
            with(data[holder.adapterPosition])
            {
                bind.textView4.text=this.text.toString()
                bind.imageView2.setBackgroundResource(this.image)
                bind.progressBar.progressTintList= ColorStateList.valueOf(Color.parseColor(this.color))
                bind.textView5.text=this.count.toString()
                bind.textView5.setTextColor(Color.parseColor(this.color))
            }
        }
    }
}