package com.example.healthyvirtuosotest.views.activities

import android.os.Bundle
import androidx.navigation.ui.NavigationUI
import com.example.healthyvirtuosotest.R
import com.example.healthyvirtuosotest.core.abstraction.activity.BaseActivity
import com.example.healthyvirtuosotest.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomBar)
        NavigationUI.setupWithNavController(bottomNavigationView, navigation.navController)
    }

    override fun getBindingClass(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override val toolbarId: Int
        get() = 0
    override val toolbarTitleId: Int
        get() = 0
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override var statusBarColor: Int
        get() = 0
        set(value) {}
    override val appBarId: Int
        get() = 0
}