package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding

const val LOG_TAG = "two_trees_oil"

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) return

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

        val viewModel = ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private fun updateBadge(count: Int) {
        val badge = binding.bottomNavigation.getOrCreateBadge(R.id.action_shop)
        if (count > 0) {
            badge.number = count
            badge.isVisible = true
        } else {
            badge.clearNumber()
            badge.isVisible = false
        }
    }

}