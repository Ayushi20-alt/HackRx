package com.example.leadiify.view

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.leadiify.R
import com.example.leadiify.adapter.EnrichedDataAdapter
import com.example.leadiify.adapter.LeadsAdapter
import com.example.leadiify.databinding.FragmentUserProfileBinding
import com.example.leadiify.model.EnrichedDataModel
import com.example.leadiify.model.Leads_data_modelItem
import com.example.leadiify.model.SendConnection
import com.example.unacademy.network.RetrofitClient
import com.kofigyan.stateprogressbar.StateProgressBar
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserProfile : Fragment() {

    var descriptionData = arrayOf("Aware", "Interested", "Desire", "Closed")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        var data  = arguments?.getParcelable<Leads_data_modelItem>("data")
//        Toast.makeText(requireContext(), data.toString(), Toast.LENGTH_SHORT).show()
        binding.name.text=data?.fullName.toString()
        binding.yourStateProgress.setStateDescriptionData(descriptionData)
        binding.profilePhoto.load(data?.profileImageUrl.toString())
        binding.iid.text=data?.lead_score.toString()
        binding.cName.text=data?.category.toString()
        binding.cTitle.text=data?.query.toString()
        binding.function.text=data?.detailed_leads?.location.toString()
        binding.textView28.text=data?.detailed_leads?.connectionDegree.toString()
        binding.email.text=data?.email.toString()
        binding.industry.text=data?.detailed_leads?.jobTitle.toString()
        binding.institute.text=data?.additionalInfo.toString()
        binding.keywords.text=data?.url.toString()
        binding.keywords.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(binding.keywords.text.toString())
            startActivity(i)
        }
        binding.name.setOnClickListener {
            val url = data?.detailed_leads?.linkedinProfileUrl.toString() ?: "https:LinkedIn.com"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        binding.button.setOnClickListener {
            val dialodView = LayoutInflater.from(requireContext()).inflate(R.layout.message_input, null)
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(dialodView)

            val alertDialog: AlertDialog = mBuilder.create()
            alertDialog.show()
            var text = "Greetings, I would love to explore full time opportunities at your company, could we connect so I can share my resume"
            text=dialodView.findViewById<TextView>(R.id.textvalue).text.toString()
            dialodView.findViewById<Button>(R.id.send_message).setOnClickListener {
                RetrofitClient.init().sendConnection(SendConnection(text,
                    data?.id ?: 0
                )).enqueue(object : Callback<ResponseBody?> {
                    override fun onResponse(
                        call: Call<ResponseBody?>,
                        response: Response<ResponseBody?>
                    ) {
                        if(response.isSuccessful)
                        {
                            Toast.makeText(requireContext(),"Request Sent",Toast.LENGTH_LONG).show()

                        }
                        else
                            Toast.makeText(requireContext(),"Already Connected",Toast.LENGTH_LONG).show()
                        alertDialog.cancel()
                    }

                    override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                        Toast.makeText(requireContext(),"",Toast.LENGTH_LONG).show()
                        alertDialog.cancel()
                    }
                })
            }
        }
        when(data?.state_progress)
        {
            1->binding.yourStateProgress.setCurrentStateNumber(StateProgressBar.StateNumber.ONE)
            2->binding.yourStateProgress.setCurrentStateNumber(StateProgressBar.StateNumber.TWO)
            3->binding.yourStateProgress.setCurrentStateNumber(StateProgressBar.StateNumber.THREE)
            else ->binding.yourStateProgress.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR)
        }

        val data1 = arrayListOf<Pair<String, String>>()

        data1.add(Pair("id", data?.detailed_leads?.id.toString()))
        data1.add(Pair("linkedinProfileUrl",data?.detailed_leads?.linkedinProfileUrl.toString()))
        data1.add(Pair("email", data?.detailed_leads?.email.toString()))
        data1.add(Pair("linkedinProfile", data?.detailed_leads?.linkedinProfile.toString()))
        data1.add(Pair("headline", data?.detailed_leads?.headline.toString()))
        data1.add(Pair("location", data?.location.toString()))
        data1.add(Pair("imgUrl", data?.detailed_leads?.imgUrl.toString()))
        data1.add(Pair("firstName", data?.detailed_leads?.firstName.toString()))
        data1.add(Pair("lastName", data?.detailed_leads?.lastName.toString()))
        data1.add(Pair("fullName", data?.detailed_leads?.fullName.toString()))
        data1.add(Pair("subscribers", data?.detailed_leads?.subscribers.toString()))
        data1.add(Pair("connectionDegree",data?.detailed_leads?.connectionDegree.toString()))
        data1.add(Pair("vmid", data?.detailed_leads?.vmid.toString()))
        data1.add(Pair("userId", data?.detailed_leads?.userId.toString()))
        data1.add(Pair("linkedinSalesNavigatorUrl",data?.detailed_leads?.linkedinSalesNavigatorUrl.toString()))
        data1.add(Pair("connectionsCount",data?.detailed_leads?.connectionsCount.toString()))
        data1.add(Pair("connectionsUrl", data?.detailed_leads?.connectionsUrl.toString()))
        data1.add(Pair("mutualConnectionsUrl", data?.detailed_leads?.mutualConnectionsUrl.toString()))
        data1.add(Pair("mutualConnectionsText", data?.detailed_leads?.mutualConnectionsText.toString()))
        data1.add(Pair("company", data?.detailed_leads?.company.toString()))
        data1.add(Pair("companyUrl", data?.detailed_leads?.companyUrl.toString()))
        data1.add(Pair("jobTitle", data?.detailed_leads?.jobTitle.toString()))
        data1.add(Pair("jobDescription", data?.detailed_leads?.jobDescription.toString()))
        data1.add(Pair("jobLocation", data?.detailed_leads?.jobLocation.toString()))
        data1.add(Pair("jobDateRange", data?.detailed_leads?.jobDateRange.toString()))
        data1.add(Pair("jobDuration", data?.detailed_leads?.jobDuration.toString()))
        data1.add(Pair("company2", data?.detailed_leads?.company2.toString()))
        data1.add(Pair("companyUrl2", data?.detailed_leads?.companyUrl2.toString()))
        data1.add(Pair("jobTitle2",data?.detailed_leads?.jobTitle2.toString()))
        data1.add(Pair("jobDescription2", data?.detailed_leads?.jobDescription2.toString()))
        data1.add(Pair("jobLocation2", data?.detailed_leads?.jobLocation2.toString()))
        data1.add(Pair("jobDateRange2",data?.detailed_leads?.jobDateRange2.toString()))
        data1.add(Pair("jobDuration2", data?.detailed_leads?.jobDuration2.toString()))
        data1.add(Pair("school", data?.detailed_leads?.school.toString()))
        data1.add(Pair("schoolUrl", data?.detailed_leads?.schoolUrl.toString()))
        data1.add(Pair("schoolDegree", data?.detailed_leads?.schoolDegree.toString()))
        data1.add(Pair("schoolDateRange", data?.detailed_leads?.schoolDateRange.toString()))
        data1.add(Pair("school2", data?.detailed_leads?.school2.toString()))
        data1.add(Pair("schoolDegree2", data?.detailed_leads?.schoolDegree2.toString()))
        data1.add(Pair("schoolDateRange2", data?.detailed_leads?.schoolDateRange2.toString()))
        data1.add(Pair("civilityFromDropContact",data?.detailed_leads?.civilityFromDropContact.toString()))
        data1.add(Pair("websiteFromDropContact", data?.detailed_leads?.websiteFromDropContact.toString()))
        data1.add(Pair("companyWebsite", data?.detailed_leads?.companyWebsite.toString()))
        data1.add(Pair("allSkills", data?.detailed_leads?.allSkills.toString()))
        data1.add(Pair("skill1", data?.detailed_leads?.skill1.toString()))
        data1.add(Pair("endorsement1", data?.detailed_leads?.endorsement1.toString()))
        data1.add(Pair("skill2", data?.detailed_leads?.skill2.toString()))
        data1.add(Pair("endorsement2",data?.detailed_leads?.endorsement2.toString()))
        data1.add(Pair("skill3", data?.detailed_leads?.skill3.toString()))
        data1.add(Pair("endorsement3",data?.detailed_leads?.endorsement3.toString()))
        data1.add(Pair("skill4", data?.detailed_leads?.skill4.toString()))
        data1.add(Pair("endorsement4",data?.detailed_leads?.endorsement4.toString()))
        data1.add(Pair("skill5", data?.detailed_leads?.skill5.toString()))
        data1.add(Pair("endorsement5", data?.detailed_leads?.endorsement5.toString()))
        data1.add(Pair("skill6", data?.detailed_leads?.skill6.toString()))
        data1.add(Pair("endorsement6", data?.detailed_leads?.endorsement6.toString()))
        data1.add(Pair("baseUrl", data?.detailed_leads?.baseUrl.toString()))
        data1.add(Pair("profileId",data?.detailed_leads?.profileId.toString()))
        data1.add(Pair("timestamp", data?.detailed_leads?.timestamp.toString()))
        data1.add(Pair("description", data?.detailed_leads?.description.toString()))
        data1.add(Pair("schoolUrl2", data?.detailed_leads?.schoolUrl2.toString()))
        data1.add(Pair("website", data?.detailed_leads?.website.toString()))
        data1.add(Pair("birthday", data?.detailed_leads?.birthday.toString()))
        data1.add(Pair("mail", data?.detailed_leads?.mail.toString()))
        data1.add(Pair("schoolDescription", data?.detailed_leads?.schoolDescription.toString()))
        data1.add(Pair("twitter", data?.detailed_leads?.twitter.toString()))
        data1.add(Pair("twitterProfileUrl", data?.detailed_leads?.twitterProfileUrl.toString()))
        data1.add(Pair("mailFromDropcontact", data?.detailed_leads?.mailFromDropcontact.toString()))
        data1.add(Pair("qualificationFromDropContact",data?.detailed_leads?.qualificationFromDropContact.toString()))

       var  adapter= EnrichedDataAdapter(data1)
        var layout = LinearLayoutManager(
            container?.context,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.enrichedRecyclerView.adapter=adapter
        binding.enrichedRecyclerView.layoutManager=layout



        return binding.root
    }


}