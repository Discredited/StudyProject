package com.june.function.pdfpreview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import com.june.base.basic.part.BaseActivity
import com.june.function.pdfpreview.databinding.ActivityPdfPreviewBinding

/**
 * pdf预览页面
 * 2022年04月27日15:16:04
 * https://github.com/skymxc/Example/tree/master/displaypdf
 *
 * 2022/4/27
 * @author June
 */
class PDFPreViewActivity : BaseActivity<ActivityPdfPreviewBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val webView = WebView(this)
        webView.settings.apply {
            //支持缩放
            this.setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = false

            //自适应屏幕
            useWideViewPort = true
            loadWithOverviewMode = true

            //允许js 并读取文件
            javaScriptEnabled = true
            allowFileAccess = true
            allowFileAccessFromFileURLs = true
            allowUniversalAccessFromFileURLs = true
        }

        val url = intent.getStringExtra("PDF_URL")
        webView.loadUrl("file:///android_asset/viewer.html?file=${url}")

        mBinding.flWebContainer.addView(webView)
    }

    companion object {
        @JvmStatic
        fun start(context: Context, url: String) {
            context.startActivity(Intent(context, PDFPreViewActivity::class.java).apply {
                putExtra("PDF_URL", url)
            })
        }
    }
}