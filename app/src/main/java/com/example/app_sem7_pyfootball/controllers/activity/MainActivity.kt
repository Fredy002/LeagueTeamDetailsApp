package com.example.app_sem7_pyfootball.controllers.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.app_sem7_pyfootball.R
import com.example.app_sem7_pyfootball.controllers.fragments.SaveFragment
import com.example.app_sem7_pyfootball.controllers.fragments.TeamFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    //declaramos la variable de navegacion
    private val onNavigationItemSelectedListener = BottomNavigationView.
        OnNavigationItemSelectedListener { item-> navigateTo(item) }

    private fun navigateTo(item: MenuItem):Boolean {
        item.isChecked = true
        return supportFragmentManager
            .beginTransaction()
            .replace(R.id.flFragment, getFragmentFor(item))
            .commit() > 0
    }

    private fun getFragmentFor(item: MenuItem): Fragment {
        return when(item.itemId){
            // cargamos los fragments
            R.id.menu_home -> TeamFragment()
            R.id.menu_favorite -> SaveFragment()
            else -> TeamFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView:BottomNavigationView = findViewById(R.id.bnvMenu)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navigateTo(navView.menu.findItem(R.id.menu_home))
    }


}