package com.june.studyproject.base.ext

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.june.studyproject.R

fun Toolbar.initToolbar(
        title: String,
        menu: Int = 0,
        navIcon: Int = R.drawable.ic_back_black,
        titleCenter: Boolean = true
) {

    val toolbarTitle = findViewById<AppCompatTextView>(R.id.toolbarTitle)

    //设置返回键按钮
    if (navIcon == 0) {
        val padding = resources.getDimensionPixelSize(R.dimen.common_margin)
        setPadding(padding, 0, 0, padding)
    } else {
        setNavigationIcon(navIcon)
        if (navIcon == R.drawable.ic_back_white) {
            val white = ContextCompat.getColor(context, R.color.color_white)
            toolbarTitle.setTextColor(white)
            setTitleTextColor(white)
        }
    }

    //设置标题
    if (titleCenter) {
        //标题居中
        toolbarTitle.visibility = View.VISIBLE
        toolbarTitle.text = title
        setTitle("")
    } else {
        //使用Toolbar默认标题
        toolbarTitle.visibility = View.GONE
        setTitle(title)
    }

    //设置菜单
    if (menu != 0) {
        inflateMenu(menu)
    }
}