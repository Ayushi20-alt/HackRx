package com.example.leadiify.model

data class campaign_dataItem(
    val duration: String,
    val id: Int,
    val impressions: String,
    val marketing_templates: List<MarketingTemplate>,
    val percentage_change: String,
    val target: Target,
    val total_likes: String
)