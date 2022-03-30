package com.myapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R
import com.myapplication.ui.activity.ui.fragments.UserProfileFragment
import com.myapplication.ui.adapter.ViewPagerAdapter
import com.myapplication.ui.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        tabs.setupWithViewPager(viewPager).let {
            setupTabIcons()
        }
        setupViewPager()
    }

    private fun setupTabIcons() {
        tabs.getTabAt(0)?.setIcon(R.drawable.home_white)
        tabs.getTabAt(1)?.setIcon(R.drawable.user)
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(this, supportFragmentManager)
        adapter.addFrag(HomeFragment(), "Home")
        adapter.addFrag(UserProfileFragment(), "Profile")
        viewPager.adapter = adapter
    }


}