package com.june.studyproject

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.june.studyproject.base.component.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView() {
        //6.0及以上的API
        //window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//黑色
        bn_navigation.itemIconTintList = null
    }

    override fun loadData() {
        val navHostFragment = NavHostFragment.create(R.navigation.nav_main)
        supportFragmentManager.beginTransaction()
                .add(R.id.fc_container, navHostFragment, javaClass.simpleName)
                .setPrimaryNavigationFragment(navHostFragment)
                .commitNow()

        //todo 了解和改造 BottomNavigation与NvaController的关联
        //NavigationUI.setupWithNavController()根据源码了解，每次都是重新创建Fragment实例
        bn_navigation.setupWithNavController(navHostFragment.findNavController())
    }
}