package com.example.leadiify.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leadiify.databinding.ActivityDataBinding
import com.example.leadiify.databinding.TemplatesCardBinding
import com.example.leadiify.model.Get_Templates_DataItem
import com.example.leadiify.model.acitivity_data
import java.lang.Exception

class ActivityAdapter(var data:ArrayList<Get_Templates_DataItem>):RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {
    inner class ViewHolder(var bind:TemplatesCardBinding):RecyclerView.ViewHolder(bind.root)
    {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityAdapter.ViewHolder {
      var inflater =LayoutInflater.from(parent.context)
        var binding = TemplatesCardBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActivityAdapter.ViewHolder, position: Int) {
        with(holder)
        {
            with(data[holder.adapterPosition])
            {
                try {
                    bind.templateName.text = this.template_name.toString()
                    bind.ClickRate.text = this.click_rate.toString()
                    bind.OpenRate.text = this.open_rate.toString()
                    bind.meetingRate.text = this.meeting_rate.toString()
                    bind.ReplyRate.text = this.reply_rate.toString()
                    bind.bounceRate.text = this.bounce_rate.toString()
                }
                catch (e:Exception)
                {

                }
            }
        }
    }

    override fun getItemCount(): Int {
      return data.size
    }

}