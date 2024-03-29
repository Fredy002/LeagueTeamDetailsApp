package com.example.app_sem7_pyfootball.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "teams")
class Team (
    @PrimaryKey
    @SerializedName("team_id")
    val teamId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("venue_name")
    val venueName : String,
    @SerializedName("venue_city")
    val cityName: String,
    @SerializedName("founded")
    val yearFounded: Int,
    @SerializedName("venue_surface")
    val surfaceType: String,
    @SerializedName("venue_address")
    val locationOfManagement: String,
    @SerializedName("venue_capacity")
    val capacityShare: Int
): Serializable