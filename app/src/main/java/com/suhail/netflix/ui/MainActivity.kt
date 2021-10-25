package com.suhail.netflix.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.suhail.netflix.R
import com.suhail.netflix.repository.MoviesRepository
import com.suhail.netflix.ui.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.movieNavHost) as NavHostFragment? ?: return

        val navController = host.navController
        var actionBar = supportActionBar

        actionBar!!.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        setupActionBarWithNavController(navController)

    }
}