package com.june.studyproject

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.june.studyproject.base.component.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    //private val mFragmentList = mutableListOf<Fragment>()
    //private var mCurrentPosition = 0  //当前Fragment的位置

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView() {
        //6.0及以上的API
        //window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//黑色
        bn_navigation.itemIconTintList = null

//        bn_navigation.setOnNavigationItemSelectedListener { item ->
//            fragmentSelectedChange(item.itemId)
//        }
    }

    override fun loadData() {
        //todo 了解和改造 BottomNavigation与NvaController的关联
        //NavigationUI.setupWithNavController()根据源码了解，每次都是重新创建Fragment实例
        val navHostFragment = NavHostFragment.create(R.navigation.nav_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fc_container, navHostFragment, javaClass.simpleName)
            .setPrimaryNavigationFragment(navHostFragment)
            .commitNow()
        bn_navigation.setupWithNavController(navHostFragment.findNavController())

//        if (mFragmentList.isEmpty()) {
//            mFragmentList.add(ComponentFragment())
//            mFragmentList.add(LibraryFragment())
//            mFragmentList.add(ExpandFragment())
//
//            val fragmentTransaction = supportFragmentManager.beginTransaction()
//            mFragmentList.forEachIndexed { index, fragment ->
//                fragmentTransaction.add(R.id.fc_container, fragment, fragment.javaClass.simpleName)
//                if (index == 0) {
//                    fragmentTransaction.show(fragment).setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
//                } else {
//                    fragmentTransaction.hide(fragment).setMaxLifecycle(fragment, Lifecycle.State.CREATED)
//                }
//            }
//            fragmentTransaction.commitNow()
//        }
    }

//    private fun fragmentSelectedChange(itemId: Int): Boolean {
//        val position = when (itemId) {
//            R.id.libraryFragment -> 1
//            R.id.expandFragment -> 2
//            else -> 0
//        }
//
//        if (mCurrentPosition == position) {
//            return true
//        }
//        mCurrentPosition = position
//
//        val transaction = supportFragmentManager.beginTransaction()
//        mFragmentList.forEachIndexed { index, fragment ->
//            if (index == position) {
//                transaction.show(fragment).setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
//            } else {
//                transaction.hide(fragment).setMaxLifecycle(fragment, Lifecycle.State.CREATED)
//            }
//        }
//        transaction.commitNow()
//        return true
//    }
}