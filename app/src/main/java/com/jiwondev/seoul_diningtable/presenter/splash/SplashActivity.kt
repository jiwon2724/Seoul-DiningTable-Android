package com.jiwondev.seoul_diningtable.presenter.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.jiwondev.seoul_diningtable.databinding.ActivitySplashBinding
import com.jiwondev.seoul_diningtable.presenter.base.BaseActivity
import com.jiwondev.seoul_diningtable.presenter.login.LoginActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>({ActivitySplashBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO : Datastore 사용해서 값 분기해줘야해~
        moveActivity(LoginActivity::class.java)

    }

    private fun moveActivity(clazz: Class<*>) {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, clazz))
            finish()
        }, 2000)
    }
}