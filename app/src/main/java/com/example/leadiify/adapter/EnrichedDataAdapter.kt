package com.example.leadiify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leadiify.databinding.EnrichedDataBinding
import com.example.leadiify.model.EnrichedDataModel

class EnrichedDataAdapter(var data : ArrayList<Pair<String,String>>):RecyclerView.Adapter<EnrichedDataAdapter.ViewHolder>() {
    inner class ViewHolder(var bind : EnrichedDataBinding):RecyclerView.ViewHolder(bind.root)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = EnrichedDataBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      with(holder)
      {
          with(data[holder.adapterPosition])
          {
            bind.Headere.text=this.first.toString()
              bind.textView9.text=this.second.toString()
          }
      }
    }
}