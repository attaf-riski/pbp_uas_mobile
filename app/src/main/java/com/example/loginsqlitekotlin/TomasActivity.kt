package com.example.loginsqlitekotlin

import ViewPagerAdapter2
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.loginsqlitekotlin.R
import com.google.android.material.tabs.TabLayout

class TomasActivity: AppCompatActivity() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tomas)

        setIdLayout()
        setInitLayout()
    }

    private fun setIdLayout() {
        tabLayout = findViewById(R.id.tabsLayout)
        viewPager = findViewById(R.id.viewPager)
    }

    private fun setInitLayout() {
        viewPager!!.adapter = ViewPagerAdapter2(supportFragmentManager)
        viewPager!!.offscreenPageLimit = 2
        tabLayout!!.setupWithViewPager(viewPager)

    }
}