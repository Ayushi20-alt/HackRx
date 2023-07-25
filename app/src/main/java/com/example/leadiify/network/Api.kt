package com.example.leadiify.network

import com.example.leadiify.model.Get_Templates_Data
import com.example.leadiify.model.Leads_data_model
import com.example.leadiify.model.Scraperdataclass
import com.example.leadiify.model.SendConnection
import com.example.leadiify.model.campaign_data
import com.example.leadiify.model.sendCampaign.SendCampaignData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface Api {

 @GET("campaigns/")
 fun getCampaigns(): Call<campaign_data>

 @GET("leads/")
 fun getLeads():Call<Leads_data_model>

 @GET("campaign-template/")
 fun getTemplates():Call<Get_Templates_Data>

 @POST("campaigns/")
 fun sendCampaigns(@Body data: SendCampaignData):Call<ResponseBody>
 @POST("scraper/")
 fun senddata(@Body scraperdataclass: Scraperdataclass) : Call<ResponseBody>

 @POST("connection-request/")
 fun sendConnection(@Body data:SendConnection):Call<ResponseBody>

 @GET("delete-leads/")
 fun deleteAll():Call<ResponseBody>
}