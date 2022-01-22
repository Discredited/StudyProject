package com.june.studyproject

import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.june.base.basic.part.BaseActivity
import com.june.studyproject.base.ext.setupWithNavController
import com.june.studyproject.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var mCurrentNavController: LiveData<NavController>? = null

    override fun initView() {
        //6.0及以上的API
        //window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//黑色
        mBinding.bottomNavigation.itemIconTintList = null
    }

    override fun loadData() {
        //todo 了解和改造 BottomNavigation与NvaController的关联

        val navGraphIds = listOf(
            R.navigation.nav_component,
            R.navigation.nav_library,
            R.navigation.nav_expand
        )
        val controller = mBinding.bottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fragment_container,
            intent = intent
        )
        mCurrentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return mCurrentNavController?.value?.navigateUp() ?: false
    }
}