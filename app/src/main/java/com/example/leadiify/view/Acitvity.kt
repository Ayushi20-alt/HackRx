package com.example.leadiify.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leadiify.R
import com.example.leadiify.adapter.ActivityAdapter
import com.example.leadiify.databinding.FragmentAcitvityBinding
import com.example.leadiify.model.Get_Templates_Data
import com.example.leadiify.model.acitivity_data
import com.example.unacademy.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Acitvity : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding=FragmentAcitvityBinding.inflate(inflater, container, false)
//        var data = ArrayList<acitivity_data>()
//        data.add(acitivity_data("Bajaj Finserv Facebook","July 06 , 2023 - 16:26","1",R.drawable.group_1000005043))
//        data.add(acitivity_data("Bajaj Finserv Facebook","July 06 , 2023 - 16:26","1",R.drawable.group_1000005043))
//        data.add(acitivity_data("Bajaj Finserv Facebook","July 06 , 2023 - 16:26","1",R.drawable.group_1000005043))
//        data.add(acitivity_data("Bajaj Finserv Facebook","July 06 , 2023 - 16:26","1",R.drawable.group_1000005043))
//        data.add(acitivity_data("Bajaj Finserv Facebook","July 06 , 2023 - 16:26","1",R.drawable.group_1000005043))
//        data.add(acitivity_data("Bajaj Finserv Facebook","July 06 , 2023 - 16:26","1",R.drawable.group_1000005043))

        RetrofitClient.init().getTemplates().enqueue(object : Callback<Get_Templates_Data?> {
            override fun onResponse(
                call: Call<Get_Templates_Data?>,
                response: Response<Get_Templates_Data?>
            ) {
                if(response.isSuccessful)
                {
                    var adapter= response.body()?.let { ActivityAdapter(it) }
                    var layout = LinearLayoutManager(
                        container?.context,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    binding.activityRecyclerview.adapter=adapter
                    binding.activityRecyclerview.layoutManager=layout
                }
            }

            override fun onFailure(call: Call<Get_Templates_Data?>, t: Throwable) {
                Toast.makeText(requireContext(), "Network Problem", Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }


}