package com.june.studyproject

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.base.ext.setupWithNavController
import com.june.studyproject.databinding.ActivityMainBinding

class MainActivity : StudyBaseActivity<ActivityMainBinding>() {

    private var mCurrentNavController: LiveData<NavController>? = null

    override fun initView() {
        //mBinding.bottomNavigation.itemIconTintList = null

        mBinding.fragmentContainer.postDelayed(
            {
                startActivity(Intent(this, ComposeActivity::class.java))
            },
            3000L
        )
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