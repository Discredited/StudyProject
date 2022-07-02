package com.june.studyproject.expand

import com.june.base.basic.ext.click
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.databinding.ActivityExplosionBinding
import com.june.widget.explosion.ExplosionFieldView

/**
 * 粒子爆炸效果
 *
 * 2022/4/28
 * @author June
 */
class ExplosionActivity : StudyBaseActivity<ActivityExplosionBinding>() {

    private val mExplosionView by lazy { ExplosionFieldView.attach2Window(this) }

    override fun initView() {
        mBinding.flContainer.click {
            mExplosionView?.explode(it)
        }
    }

    override fun loadData() {
    }
}