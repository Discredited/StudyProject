package com.june.studyproject.component.recycler.rvadapter.custom

import android.view.View
import com.june.base.basic.part.BaseActivity
import com.june.rvadapter.BaseAdapter
import com.june.rvadapter.ItemViewCreator
import com.june.rvadapter.ItemViewHolder
import com.june.studyproject.R
import com.june.studyproject.component.recycler.rvadapter.vo.NormalInterface
import com.june.studyproject.component.recycler.rvadapter.vo.NormalSimpleText
import com.june.studyproject.databinding.ActivityNormalListBinding

/**
 * 自定义Adapter实现方案，期望它非常简洁，方便使用，扩展性好
 * 使用:
 * XXXXAdapter.Builder()
 *            .putItem(自定义Creator(ViewHolder))
 *            .putItem(自定义Creator(ViewHolder))
 *            .putItem(自定义Creator(ViewHolder))
 *            ......
 *            .build()
 */
class CustomMultiAdapterActivity : BaseActivity<ActivityNormalListBinding>() {

    private val mAdapter by lazy {
        val creator = object : ItemViewCreator<NormalSimpleText>() {
            override fun getItemViewId(): Int = R.layout.item_multi_simple_text

            override fun createViewHolder(view: View): ItemViewHolder {
                return object : ItemViewHolder(view) {
                    override fun itemViewId(): Int = R.layout.item_multi_simple_text
                }
            }

            override fun covert(item: NormalSimpleText, holder: ItemViewHolder) {
            }
        }

//        BaseAdapter.Builder<NormalInterface>()
//            .putItem(creator)
//            .build()
    }

    override fun initView() {

    }

    override fun loadData() {
    }
}