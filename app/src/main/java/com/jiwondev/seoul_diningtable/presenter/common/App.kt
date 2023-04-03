package com.jiwondev.seoul_diningtable.presenter.common

import android.app.Application
import com.jiwondev.seoul_diningtable.BuildConfig
import com.naver.maps.map.NaverMapSdk

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        init()

    }

    private fun init() {
        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient(BuildConfig.NAVER_CLIENT_ID)
    }
}