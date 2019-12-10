package com.jarroyo.kmp_mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jarroyo.sharedcode.platformName
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val platformName = platformName()
        activity_main_tv.text = platformName
    }
}
