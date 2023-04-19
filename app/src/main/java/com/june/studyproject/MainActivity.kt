package com.june.studyproject

import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.base.ext.setupWithNavController
import com.june.studyproject.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import timber.log.Timber

class MainActivity : StudyBaseActivity<ActivityMainBinding>() {

    private var mCurrentNavController: LiveData<NavController>? = null

    override fun initView() {
        //mBinding.bottomNavigation.itemIconTintList = null

        // testAtomic()
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

    /**
     * 测试协程Atomic启动模式
     */
    private fun testAtomic() {
        val job = MainScope().launch(
            context = SupervisorJob() + Dispatchers.IO,
            start = CoroutineStart.ATOMIC
        ) {
            for (index in 0..10000) {
                Timber.i("输出次数：${index}  当前协程状态:${this.isActive}")
            }
            delay(2000)
            Timber.i("输出结束")
        }
        job.cancel()
    }
}