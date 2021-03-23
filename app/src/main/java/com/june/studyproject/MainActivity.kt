package com.june.studyproject

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.setupWithNavController
import com.june.studyproject.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mHandler = Handler()
    private var mCurrentNavController: LiveData<NavController>? = null

    override fun viewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

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


        Timber.e("===>>  1")
        mHandler.postDelayed({ Timber.e("===>>  2") }, 200)
        Timber.e("===>>  3")
        mHandler.post { Timber.e("===>>  4") }
        Timber.e("===>>  5")
        mHandler.postDelayed({ Timber.e("===>>  6") }, 100)
    }

    override fun onSupportNavigateUp(): Boolean {
        return mCurrentNavController?.value?.navigateUp() ?: false
    }
}