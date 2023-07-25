package com.example.leadiify.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Leads_data_modelItem(
    val additionalInfo: String,
    val category: String,
    val connectionDegree: String,
    val currentJob: String,
    val detailed_leads: DetailedLeads,
    val firstName: String,
    val fullName: String,
    val id: Int,
    val job: String,
    val lastName: String,
    val location: String,
    val name: String,
    val pastJob: String,
    val profileImageUrl: String,
    val profileUrl: String,
    val query: String,
    val sharedConnections: String,
    val state_progress: Int,
    val timestamp: String,
    val url: String,
    val lead_score:String,
    val email:String
):Parcelable