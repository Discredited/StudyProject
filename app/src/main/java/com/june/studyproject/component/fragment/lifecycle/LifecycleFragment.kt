package com.june.studyproject.component.fragment.lifecycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.june.base.basic.ext.addLinearItemDecoration
import com.june.base.basic.ext.setLinearManager
import com.june.studyproject.R
import com.june.base.basic.part.BaseFragment
import com.june.studyproject.component.activity.lifecycle.RecordDisplayAdapter
import com.june.studyproject.component.activity.lifecycle.RecordDisplayVo
import com.june.studyproject.databinding.FragmentLifecycleBinding
import timber.log.Timber

class LifecycleFragment : BaseFragment<FragmentLifecycleBinding>() {

    private val mLifecycleViewModel by activityViewModels<FragmentLifecycleViewModel>()
    private lateinit var adapter: RecordDisplayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLifecycleViewModel.mRecordList.add(RecordDisplayVo("onCreate", javaClass.simpleName))
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mLifecycleViewModel.mRecordList.add(RecordDisplayVo("onCreateView", javaClass.simpleName))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("onViewCreated()")
        mLifecycleViewModel.mRecordList.add(RecordDisplayVo("onViewCreated", javaClass.simpleName))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.e("onActivityCreated()")
        mLifecycleViewModel.mRecordList.add(
                RecordDisplayVo(
                        "onActivityCreated()",
                        javaClass.simpleName
                )
        )

        mLifecycleViewModel.mItemChangeLive.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        Timber.e("onStart()")
        mLifecycleViewModel.mRecordList.add(
                RecordDisplayVo(
                        "onStart()",
                        javaClass.simpleName
                )
        )
    }

    override fun onResume() {
        super.onResume()
        Timber.e("onResume()")
        mLifecycleViewModel.mRecordList.add(
                RecordDisplayVo(
                        "onResume()",
                        javaClass.simpleName
                )
        )
        adapter.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        Timber.e("onPause()")
        mLifecycleViewModel.mRecordList.add(
                RecordDisplayVo(
                        "onPause()",
                        javaClass.simpleName
                )
        )
    }

    override fun onStop() {
        super.onStop()
        Timber.e("onStop()")
        mLifecycleViewModel.mRecordList.add(
                RecordDisplayVo(
                        "onStop()",
                        javaClass.simpleName
                )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.e("onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.e("onDestroy()")
    }

    override fun initView() {
        adapter = RecordDisplayAdapter(mLifecycleViewModel.mRecordList)

        mBinding.rvFragmentLifecycle.apply {
            setLinearManager()
            adapter = adapter
            setHasFixedSize(true)
            addLinearItemDecoration(size = resources.getDimensionPixelSize(R.dimen.dp_5))
        }
    }

    companion object {
        fun newInstance(): LifecycleFragment {
            return LifecycleFragment()
        }
    }
}