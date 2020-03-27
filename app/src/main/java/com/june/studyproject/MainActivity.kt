package com.june.studyproject

import androidx.fragment.app.Fragment
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.component.index.ComponentFragment
import com.june.studyproject.expand.ExpandFragment
import com.june.studyproject.library.LibraryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val mFragments = mutableListOf<Fragment>()

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView() {
        bn_navigation.itemIconTintList = null
        bn_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.component -> {
                    supportFragmentManager.beginTransaction()
                            .show(mFragments[0])
                            .hide(mFragments[1])
                            .hide(mFragments[2])
                            .commitNow()
                }
                R.id.library -> {
                    supportFragmentManager.beginTransaction()
                            .show(mFragments[1])
                            .hide(mFragments[0])
                            .hide(mFragments[2])
                            .commitNow()
                }
                R.id.expand -> {
                    supportFragmentManager.beginTransaction()
                            .show(mFragments[2])
                            .hide(mFragments[0])
                            .hide(mFragments[1])
                            .commitNow()
                }
            }
            true
        }
    }

    override fun loadData() {
        mFragments.add(ComponentFragment())
        mFragments.add(LibraryFragment())
        mFragments.add(ExpandFragment())

        supportFragmentManager.beginTransaction()
                .add(R.id.fc_container, mFragments[0], "")
                .add(R.id.fc_container, mFragments[1], "")
                .add(R.id.fc_container, mFragments[2], "")
                .show(mFragments[0])
                .hide(mFragments[1])
                .hide(mFragments[2])
                .commitNow()
    }
}