package com.example.app_sem7_pyfootball.controllers.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.app_sem7_pyfootball.R
import com.example.app_sem7_pyfootball.database.TeamDB
import com.example.app_sem7_pyfootball.models.Team
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class TeamDetail : AppCompatActivity() {
    lateinit var ivLogoDetail: ImageView
    lateinit var tvNameDetail: TextView
    lateinit var tvVenueName: TextView
    lateinit var fabSave: FloatingActionButton
    lateinit var tvCityName: TextView
    lateinit var tvYearFounded: TextView
    lateinit var tvSurfaceType: TextView
    lateinit var tvLocationOfManagement: TextView
    lateinit var tvCapacityShare: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)

        ivLogoDetail = findViewById(R.id.ivLogoDetail)
        tvNameDetail = findViewById(R.id.tvNameDetail)
        tvVenueName = findViewById(R.id.tvVenueName)
        fabSave = findViewById(R.id.fabSave)
        tvCityName = findViewById(R.id.tvCityName)
        tvYearFounded = findViewById(R.id.tvYearFounded)
        tvSurfaceType = findViewById(R.id.tvSurfaceType)
        tvLocationOfManagement = findViewById(R.id.tvLocationOfManagement)
        tvCapacityShare = findViewById(R.id.tvCapacityShare)
        
        initFields(this)
    }

    private fun initFields(context: Context) {
        val teamObject:Team? = intent.getSerializableExtra("team") as Team?

        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        //cargamos la imagen
        picBuilder.build().load(teamObject?.logo)
            //cuando no se puede cargar la imagen
            .placeholder(R.drawable.ic_launcher_background)
            //cuando se esta cargando la imagen
            .error(R.drawable.ic_launcher_background)
            // cuando se cargo la imagen
            .into(ivLogoDetail)

        tvNameDetail.text = teamObject?.name
        tvVenueName.text = teamObject?.venueName
        tvCityName.text = teamObject?.cityName
        tvYearFounded.text = teamObject?.yearFounded.toString()
        tvSurfaceType.text = teamObject?.surfaceType

        val address = teamObject?.locationOfManagement
        if (address != null) {
            tvLocationOfManagement.text = address
        } else {
            tvLocationOfManagement.text = "No address"
        }

        tvCapacityShare.text = teamObject?.capacityShare.toString()


        fabSave.setOnClickListener {
            saveTeam(teamObject)
            finish()
        }
    }

    private fun saveTeam(teamObject: Team?) {
        if (teamObject != null) {
            // PARA GRAVAR EN LA BASE DE DATOS LOS FAVORITOS
            TeamDB.getInstance(this).getTeamDao().insert(teamObject)
        }
    }
}