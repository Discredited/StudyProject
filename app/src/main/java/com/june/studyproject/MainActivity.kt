package com.june.studyproject

import android.os.Build
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.base.ext.setupWithNavController
import com.june.studyproject.databinding.ActivityMainBinding

class MainActivity : StudyBaseActivity<ActivityMainBinding>() {

    private var mCurrentNavController: LiveData<NavController>? = null

    override fun initView() {
        //mBinding.bottomNavigation.itemIconTintList = null

        setStatusBarColor()
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

    private fun setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window?.isNavigationBarContrastEnforced = false
            ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, windowInsets ->
                val inserts = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars())
                v.setPadding(inserts.left, v.paddingTop, inserts.right, inserts.bottom)
                WindowInsetsCompat.CONSUMED
            }
        }
    }
}