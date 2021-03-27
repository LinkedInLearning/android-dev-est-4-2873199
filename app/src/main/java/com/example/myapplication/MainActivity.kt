package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding

const val LOG_TAG = "two_trees_oil"

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) return

        supportFragmentManager.commit {
            add<HomeFragment>(R.id.container, null)
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.action_home -> goToHome()

                R.id.action_tours -> goToTours()

                R.id.action_shop -> goToShop()

                else -> false
            }
        }

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

    private fun goToShop(): Boolean {
        supportFragmentManager.commit {
            replace<ShopFragment>(R.id.container, null, null)
        }

        return true
    }

    private fun goToTours(): Boolean {
        supportFragmentManager.commit {
            replace<ToursFragment>(R.id.container, null, null)
        }

        return true
    }

    private fun goToHome(): Boolean {
        supportFragmentManager.commit {
            replace<HomeFragment>(R.id.container, null, null)
        }

        return true
    }

}