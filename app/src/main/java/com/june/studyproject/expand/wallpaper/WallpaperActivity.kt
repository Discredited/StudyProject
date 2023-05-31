package com.june.studyproject.expand.wallpaper

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.text.TextUtils
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.PathUtils
import com.blankj.utilcode.util.ScreenUtils
import com.june.base.basic.ext.click
import com.june.network.log.HeadersInterceptor
import com.june.network.log.OkHttpLogInterceptor
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.base.glide.GlideApp
import com.june.studyproject.common.Toast
import com.june.studyproject.databinding.ActivityWallpaperBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import timber.log.Timber
import java.io.File
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager
import kotlin.random.Random

/**
 * 壁纸页面
 *
 * 2023/4/18
 * @author June
 */
class WallpaperActivity : StudyBaseActivity<ActivityWallpaperBinding>() {

    private var wallpaperUrl = ""

    private val trustManagers: Array<TrustManager> = arrayOf(
        object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) {}

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) {
                try {
                    val trustManagerFactory: TrustManagerFactory =
                        TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                    trustManagerFactory.init(null as KeyStore?)
                    for (trustManager in trustManagerFactory.trustManagers) {
                        (trustManager as X509TrustManager).checkServerTrusted(chain, authType)
                    }
                } catch (e: java.lang.Exception) {
                    throw CertificateException(e)
                }
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    )

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .readTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .apply {
                val sslContext = SSLContext.getInstance("TLS")
                sslContext.init(null, trustManagers, SecureRandom())
                sslSocketFactory(sslContext.socketFactory, trustManagers[0] as X509TrustManager)

                addInterceptor(HeadersInterceptor())
                addInterceptor(OkHttpLogInterceptor())
            }
            .build()
    }

    override fun initView() {
        mBinding.btSetWallpaper.click {
            setWallpaper()
        }
    }

    override fun loadData() {
        wallpaperEnable()

        lifecycleScope.launch(Dispatchers.Main) {
            val responseHtml = requestUrl("https://bing.ioliu.cn")
            Timber.e("获取到的Html:${responseHtml}")

            val htmlFilePath = withContext(Dispatchers.IO) {
                val htmlFile = File("${PathUtils.getExternalAppFilesPath()}/htmlTemp.html")
                if (htmlFile.exists()) {
                    htmlFile.delete()
                } else {
                    htmlFile.createNewFile()
                }

                htmlFile.bufferedWriter().use { out ->
                    out.write(responseHtml)
                }

                htmlFile
            }

            Timber.e("Html本地文件地址:${htmlFilePath.absolutePath}")

            if (htmlFilePath.exists()) {
                Toast.showShort("正在解析数据")

                analyzeHtml(htmlFilePath)

                if (!TextUtils.isEmpty(wallpaperUrl)) {
                    GlideApp.with(this@WallpaperActivity)
                        .load(wallpaperUrl)
                        .into(mBinding.ivWallpaper)
                } else {
                    Toast.showShort("获取图片失败")
                }
            } else {
                Toast.showShort("Html文件不存在")
            }
        }
    }

    private fun wallpaperEnable() {
        val wallpaperManager = getSystemService(WALLPAPER_SERVICE) as WallpaperManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Timber.e("是否支持wallpaper:${wallpaperManager.isWallpaperSupported}")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Timber.e("是否支持设置wallpaper:${wallpaperManager.isSetWallpaperAllowed}")
        }
    }

    private fun setWallpaper() {
        lifecycleScope.launch(Dispatchers.Main) {
            val bitmap: Bitmap? = withContext(Dispatchers.IO) {
                val file = GlideApp.with(this@WallpaperActivity)
                    .downloadOnly()
                    .load(wallpaperUrl)
                    .submit()
                    .get()

                if (!file.exists()) {
                    val toast = "资源文件不存在 ${file.absoluteFile}"
                    Toast.showShort(toast)
                    Timber.e(toast)
                    return@withContext null
                }

                BitmapFactory.decodeFile(file.absolutePath)
            }

            if (null == bitmap) {
                Timber.e("设置壁纸失败")
            } else {
                val wallpaperManager = getSystemService(WALLPAPER_SERVICE) as WallpaperManager
                wallpaperManager.setBitmap(bitmap)
            }
        }
    }


    private suspend fun analyzeHtml(file: File) {
        withContext(Dispatchers.IO) {
            try {
                val doc: Document = Jsoup.parse(file)
                Timber.e("获取的Html:${doc.body()}")

                val imageElements: Elements? = doc.select("div.item div.card img.progressive__img")

                val imageList = mutableListOf<String>()

                imageElements?.forEach {
                    val imageUrl = it.attr("data-progressive")
                    Timber.e("获取到的图片地址：${imageUrl}")
                    if (!TextUtils.isEmpty(imageUrl)) {
                        imageList.add(imageUrl)
                    }
                }

                if (imageList.isNotEmpty() && TextUtils.isEmpty(wallpaperUrl)) {
                    val position = (imageList.size * Math.random()).toInt()
                    val finalPosition = if (position < 0) {
                        0
                    } else if (position >= imageList.size) {
                        imageList.size - 1
                    } else {
                        position
                    }
                    val imageUrl = imageList[finalPosition]
                    val imageFormat = imageUrl.substringAfterLast(".")
                    val urlPrefix = imageUrl.substringBeforeLast("_")
                    Timber.e("当前图片格式：${imageFormat}  url前缀:${urlPrefix}")
                    wallpaperUrl = "${urlPrefix}_1080x1920.${imageFormat}"
                }

            } catch (e: Exception) {
                Timber.e("加载Html异常，${e}")
            }
        }
    }

    private suspend fun requestUrl(url: String): String {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder().url(url).method("GET", null).build()
            try {
                val response = okHttpClient.newCall(request).execute()
                response.body?.string() ?: ""
            } catch (e: Exception) {
                Timber.e("网络请求失败:${e}")
                ""
            }
        }
    }
}