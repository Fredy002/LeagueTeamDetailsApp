package com.example.app_sem7_pyfootball.controllers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sem7_pyfootball.R
import com.example.app_sem7_pyfootball.adapter.TeamAdapter
import com.example.app_sem7_pyfootball.database.TeamDB
import com.example.app_sem7_pyfootball.models.Team


class SaveFragment : Fragment(), TeamAdapter.OnItemClickListener {

    var team: List<Team> = ArrayList()
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        team = TeamDB.getInstance(view.context).getTeamDao().getAll()

        recyclerView = view.findViewById(R.id.rvTeamSave)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = TeamAdapter(team, view.context, this)
    }

    override fun OnItemClicked(team: Team) {
        TODO("Not yet implemented")
    }
}