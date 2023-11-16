package com.june.studyproject

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class ComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { InitView() }
    }

    @Composable
    fun InitView() {
        Text("1111111111")
        Text("2222222222")
        Text("3333333333")
    }
}