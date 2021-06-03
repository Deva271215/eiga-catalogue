package com.dicoding.eigacatalogue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.eigacatalogue.databinding.ActivityMainBinding
import com.dicoding.eigacatalogue.view.adapters.ViewPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        supportActionBar?.elevation = 0f

        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        activityMainBinding.apply {
            pager.adapter = viewPagerAdapter
            tabs.setupWithViewPager(pager)
        }
    }
}