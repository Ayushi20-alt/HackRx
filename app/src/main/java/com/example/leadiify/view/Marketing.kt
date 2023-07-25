package com.example.leadiify.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leadiify.R
import com.example.leadiify.adapter.MarketingAdapter
import com.example.leadiify.databinding.FragmentMarketingBinding
import com.example.leadiify.model.campaign_data
import com.example.unacademy.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Marketing : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding= FragmentMarketingBinding.inflate(inflater, container, false)
        networkcall(container, binding)
        return binding.root
    }

    private fun networkcall(
        container: ViewGroup?,
        binding: FragmentMarketingBinding
    ) {
        RetrofitClient.init().getCampaigns().enqueue(object : Callback<campaign_data?> {
            override fun onResponse(
                call: Call<campaign_data?>,
                response: Response<campaign_data?>
            ) {
                if (response.isSuccessful) {
                    var adapter = response.body()?.let { MarketingAdapter(it) }
                    var layout = LinearLayoutManager(
                        container?.context,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    binding.marketingRecylerView.adapter = adapter
                    binding.marketingRecylerView.layoutManager = layout
                }
            }

            override fun onFailure(call: Call<campaign_data?>, t: Throwable) {
                Toast.makeText(requireContext(), "Check Internet Connection", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_marketing_to_createCampaign)
        }
    }

}