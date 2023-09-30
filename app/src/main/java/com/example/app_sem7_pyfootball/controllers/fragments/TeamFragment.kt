package com.example.app_sem7_pyfootball.controllers.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sem7_pyfootball.R
import com.example.app_sem7_pyfootball.adapter.TeamAdapter
import com.example.app_sem7_pyfootball.controllers.activity.TeamDetail
import com.example.app_sem7_pyfootball.models.ApiResponseHeader
import com.example.app_sem7_pyfootball.models.Team
import com.example.app_sem7_pyfootball.network.TeamService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TeamFragment : Fragment(), TeamAdapter.OnItemClickListener{

    var team: List<Team> = ArrayList()
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvTeams)

        loadTeams(view.context)
    }

    private fun loadTeams(context: Context) {

        val rvTeams = view?.findViewById<RecyclerView>(R.id.rvTeams)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-football-v1.p.rapidapi.com/v2/teams/league/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val teamService: TeamService
        teamService = retrofit.create(TeamService::class.java)

        val request = teamService.getTeams(
            "api-football-v1.p.rapidapi.com/",
            "0bee96e232msh91abfc697a99ee1p1f3b1fjsn3694ca9ffc56"
        )

        request.enqueue(object : Callback<ApiResponseHeader> {
            override fun onResponse(
                call: Call<ApiResponseHeader>,
                response: Response<ApiResponseHeader>
            ) {
                if(response.isSuccessful){
                    val teams: List<Team> = response.body()!!.api.teams ?: ArrayList()
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = TeamAdapter(teams, context, this@TeamFragment)
                }
                else{
                    Log.d("Activity Fail", "Error "+response.code())
                }
            }

            override fun onFailure(call: Call<ApiResponseHeader>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun OnItemClicked(team: Team) {
        val intent = Intent(context, TeamDetail::class.java)
        intent.putExtra("team", team)
        startActivity(intent)
    }

}