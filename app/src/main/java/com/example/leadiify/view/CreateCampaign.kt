package com.example.leadiify.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.leadiify.R
import com.example.leadiify.databinding.FragmentCreateCampaignBinding
import com.example.leadiify.model.Get_Templates_Data
import com.example.leadiify.model.campaign_data
import com.example.leadiify.model.sendCampaign.SendCampaignData
import com.example.leadiify.model.sendCampaign.Target
import com.example.unacademy.network.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class CreateCampaign : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var binding = FragmentCreateCampaignBinding.inflate(inflater, container, false)
        val services = resources.getStringArray(R.array.Company)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown1, services)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        RetrofitClient.init().getTemplates().enqueue(object : Callback<Get_Templates_Data?> {
            override fun onResponse(
                call: Call<Get_Templates_Data?>,
                response: Response<Get_Templates_Data?>
            ) {
                if (response.isSuccessful) {
                    val services = ArrayList<String>()
                    for (i in response.body()!!) {
                        services.add(i.template_name)
                    }
                    val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown1, services)
                    binding.autoCompleteTextView1.setAdapter(arrayAdapter)
                }
            }

            override fun onFailure(call: Call<Get_Templates_Data?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        binding.button1.setOnClickListener {
            binding.progressBar4.visibility=View.VISIBLE
            var data = SendCampaignData(
                duration = binding.duration.text.toString(),
                impressions = "",
                percentage_change = "",
                target = Target( binding.autoCompleteTextView.text.toString()),
                "",
                binding.autoCompleteTextView1.text.toString()
            )
            Log.d("asdasdasd",data.toString())
            RetrofitClient.init().sendCampaigns(data).enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {  binding.progressBar4.visibility=View.GONE

                     if(response.isSuccessful)
                     {
                         Toast.makeText(requireContext(), "Marketing Sent", Toast.LENGTH_SHORT).show()
                     }
                    else
                     {
                         Toast.makeText(requireContext(), response.message().toString(), Toast.LENGTH_SHORT).show()
                     }

                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    binding.progressBar4.visibility=View.GONE
                    Toast.makeText(requireContext(), t.localizedMessage.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        }
        return binding.root
    }


}