package com.june.studyproject

import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var mCurrentNavController: LiveData<NavController>? = null

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView() {
        //6.0及以上的API
        //window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//黑色
        bn_navigation.itemIconTintList = null
    }

    override fun loadData() {
        //todo 了解和改造 BottomNavigation与NvaController的关联

        val navGraphIds = listOf(
            R.navigation.nav_component,
            R.navigation.nav_library,
            R.navigation.nav_expand
        )
        val controller = bn_navigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fc_container,
            intent = intent
        )
        mCurrentNavController = controller

//        tvToPay.setOnClickListener {
//            val uri = Uri.parse("tmmtmm://coinPayment?no=af59a80b85f293c75ae420d1fb6e6b06&idcr=c646f48a116a3bcdaa9a416b4c512f3f")
//
//            val tmmIntent = Intent("tmmtmm.app", uri)
//            tmmIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(tmmIntent)
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return mCurrentNavController?.value?.navigateUp() ?: false
    }
}