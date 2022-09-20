package com.example.healthyvirtuosotest.views.activities

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.healthyvirtuosotest.R
import com.example.healthyvirtuosotest.core.abstraction.activity.BaseActivity
import com.example.healthyvirtuosotest.core.extensions.gone
import com.example.healthyvirtuosotest.core.extensions.visible
import com.example.healthyvirtuosotest.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomBar)
        NavigationUI.setupWithNavController(bottomNavigationView, navigation.navController)

        val appBarConfiguration = AppBarConfiguration(navigation.navController.graph)
        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(
            navigation.navController,
            appBarConfiguration
        )
        navigation.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movieDetailFragment -> bottomNavigationView.gone()
                else -> {
                    bottomNavigationView.visible()
                }
            }
        }
    }

    override fun getBindingClass(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override val toolbarId: Int
        get() = R.id.toolbar
    override val toolbarTitleId: Int
        get() = 0
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override var statusBarColor: Int
        get() = android.R.color.black
        set(value) {}
    override val appBarId: Int
        get() = 0
}