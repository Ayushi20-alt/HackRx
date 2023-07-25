package com.example.leadiify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.leadiify.databinding.LeadsDataBinding
import com.example.leadiify.model.Leads_data_model
import com.kofigyan.stateprogressbar.StateProgressBar

class LeadsAdapter(var data: Leads_data_model):RecyclerView.Adapter<LeadsAdapter.ViewHolder>() {

    var clickListener:ClickListener?=null
    interface ClickListener{
        fun OnClick(position:Int)
    }
    fun onClickListener( clickListener: ClickListener)
    {
        this.clickListener=clickListener
    }
    var descriptionData = arrayOf("Aware", "Interested", "Desire", "Closed")
    inner class ViewHolder(var bind:LeadsDataBinding):RecyclerView.ViewHolder(bind.root)
    {
        init {
            itemView.setOnClickListener {
                clickListener?.OnClick(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeadsAdapter.ViewHolder {
      var inflater =LayoutInflater.from(parent.context)
        var binding = LeadsDataBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeadsAdapter.ViewHolder, position: Int) {
        with(holder)
        {
            with(data[holder.adapterPosition])
            {
               bind.textView10.text=this.detailed_leads.jobTitle
                bind.ore.text=this.lead_score.toString()
                when(this.state_progress)
                {
                    1->bind.yourStateProgress.setCurrentStateNumber(StateProgressBar.StateNumber.ONE)
                    2->bind.yourStateProgress.setCurrentStateNumber(StateProgressBar.StateNumber.TWO)
                    3->bind.yourStateProgress.setCurrentStateNumber(StateProgressBar.StateNumber.THREE)
                    else ->bind.yourStateProgress.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR)
                }

                bind.textView7.text=this.fullName
                bind.yourStateProgress.setStateDescriptionData(descriptionData)
//                bind.imageView5.setBackgroundResource()
                bind.profilePhoto.load(this.profileImageUrl)
            }
        }
    }

    override fun getItemCount(): Int {
      return data.size
    }

}