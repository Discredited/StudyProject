package com.june.studyproject.base.ext

import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.setupWithFragment(
    supportFragment: FragmentManager,
    navigationId: Int
) {
    val navHostFragment = NavHostFragment.create(navigationId)

    setOnNavigationItemSelectedListener { item ->

        true
    }
}