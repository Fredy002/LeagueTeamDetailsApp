package com.example.app_sem7_pyfootball.models

import com.google.gson.annotations.SerializedName

class ApiResponseHeader (
    @SerializedName("api")
    val api: ApiResponseDetails
)