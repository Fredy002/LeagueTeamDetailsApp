package com.example.app_sem7_pyfootball.models

import com.google.gson.annotations.SerializedName

class ApiResponseDetails (
    @SerializedName("results")
    val results: Int,
    @SerializedName("teams")
    val teams: List<Team>
)