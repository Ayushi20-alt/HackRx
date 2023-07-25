package com.example.leadiify.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.leadiify.databinding.MarketingCardsBinding
import com.example.leadiify.model.campaign_data
import kotlin.math.roundToInt

class MarketingAdapter(var data: campaign_data):RecyclerView.Adapter<MarketingAdapter.ViewHolder>() {
    inner class ViewHolder(var bind:MarketingCardsBinding):RecyclerView.ViewHolder(bind.root)
    {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketingAdapter.ViewHolder {
        var inflater =LayoutInflater.from(parent.context)
        var binding = MarketingCardsBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarketingAdapter.ViewHolder, position: Int) {
        with(holder)
        {
            with(data[holder.adapterPosition])
            {

               bind.textView14.text=this.total_likes
                bind.targetValue.text=this.target.target_name.toString()
                bind.impValue.text=this.impressions
                bind.durationData.text=this.duration
//                Log.d("ASDasd",((this.duration.toInt().toFloat()/20)*100F).roundToInt().toString())
                bind.durationProgress.progress= ((this.duration.toInt().toFloat()/20)*100F).roundToInt()
                bind.CompanyLogo.load(this.target.logo.toString() ?: "https.google.com")
            }
        }
    }

    override fun getItemCount(): Int {
      return data.size
    }

}