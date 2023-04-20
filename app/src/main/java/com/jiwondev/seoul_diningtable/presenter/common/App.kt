package com.jiwondev.seoul_diningtable.presenter.common

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.jiwondev.seoul_diningtable.BuildConfig
import com.jiwondev.seoul_diningtable.R
import com.kakao.sdk.common.KakaoSdk
import com.naver.maps.map.NaverMapSdk
import com.navercorp.nid.NaverIdLoginSDK
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        init()
    }

    private fun init() {
        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient(BuildConfig.NAVER_CLIENT_ID)
        NaverIdLoginSDK.initialize(
            this,
            BuildConfig.NAVER_LOGIN_CLIENT_ID,
            BuildConfig.NAVER_LOGIN_CLEINT_SECRET,
            resources.getString(R.string.app_name)
        )
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // 다크모드 비활성화
    }
}