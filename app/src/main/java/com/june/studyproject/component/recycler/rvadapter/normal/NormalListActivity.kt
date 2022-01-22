package com.june.studyproject.component.recycler.rvadapter.normal

import com.june.base.basic.ext.addLinearItemDecoration
import com.june.base.basic.ext.setLinearManager
import com.june.base.basic.part.BaseActivity
import com.june.studyproject.common.ConstHelper
import com.june.studyproject.component.recycler.rvadapter.vo.NormalMultiImage
import com.june.studyproject.component.recycler.rvadapter.vo.NormalMultiText
import com.june.studyproject.component.recycler.rvadapter.vo.NormalSimpleImage
import com.june.studyproject.component.recycler.rvadapter.vo.NormalSimpleText
import com.june.studyproject.databinding.ActivityNormalListBinding

class NormalListActivity : BaseActivity<ActivityNormalListBinding>() {

    private val mAdapter = NormalMultiAdapter()

    override fun initView() {
        mBinding.rvNormalMulti.apply {
            setLinearManager()
            adapter = mAdapter
            setHasFixedSize(true)
            addLinearItemDecoration()
        }
    }

    override fun loadData() {
        mAdapter.setNewList(
            mutableListOf(
                NormalSimpleText("迦叶和尚", "十六年，你的阳寿只有十六年"),
                NormalMultiText("林飞", "这是白马寺的大师伽叶第一次看见林飞时，对他说的话", "这一年，林飞十六岁"),
                NormalSimpleImage("甘柠真", ConstHelper.getDiffImage(1)[0]),
                NormalSimpleText("三美女相伴", "却令海姬失去了姐姐，给不了甘柠真安定与幸福，也可怜鸠丹媚随之流浪"),
                NormalMultiImage("丽人行", ConstHelper.getDiffImage(2)[0], ConstHelper.getDiffImage(2)[1]),
                NormalMultiText("林飞", "纵使不甘，却也无奈", "在林飞准备了却心头之愿、迎接命运时，神秘人出现，将他带入了北境，继而开始了林飞抗逆天命历程"),
                NormalSimpleText("天定的魔主", "沙罗铁树为之盛开"),
                NormalMultiText("楚度和阿萝", "误入龙鲸之腹，巧遇师傅阿萝", "随楚度清虚天一行，名为囚禁，实为教导，无奈却令沙罗铁树花开"),
                NormalSimpleImage("鸠丹媚", ConstHelper.getDiffImage(1)[0])
            )
        )
        mAdapter.notifyDataSetChanged()
    }
}