package com.june.studyproject

import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.base.ext.setupWithNavController
import com.june.studyproject.databinding.ActivityMainBinding

class MainActivity : StudyBaseActivity<ActivityMainBinding>() {

    private var mCurrentNavController: LiveData<NavController>? = null

    override fun initView() {
        //mBinding.bottomNavigation.itemIconTintList = null
        val navGraphIds = listOf(
            R.navigation.nav_component,
            R.navigation.nav_library,
            R.navigation.nav_expand
        )
        val controller = mBinding.bottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )
        mCurrentNavController = controller
    }

    override fun loadData() {
    }

    override fun onSupportNavigateUp(): Boolean {
        return mCurrentNavController?.value?.navigateUp() ?: false
    }
}