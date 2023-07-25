package com.example.leadiify.model.sendCampaign

data class SendCampaignData(
    val duration: String,
    val impressions: String,
    val percentage_change: String,
    val target: Target,
    val total_likes: String,
    val template_name:String
)