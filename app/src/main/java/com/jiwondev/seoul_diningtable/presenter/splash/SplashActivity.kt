package com.jiwondev.seoul_diningtable.presenter.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jiwondev.seoul_diningtable.databinding.ActivitySplashBinding
import com.jiwondev.seoul_diningtable.presenter.base.BaseActivity
import com.jiwondev.seoul_diningtable.presenter.login.LoginActivity
import com.jiwondev.seoul_diningtable.presenter.map.MapActivity
import com.jiwondev.seoul_diningtable.presenter.signup.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>({ActivitySplashBinding.inflate(it)}) {
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                splashViewModel.userPreferenceFlow.collectLatest { preference ->
                    when(preference.autoLogin) {
                        true -> {
                            // TODO : 로그인 연동
                        }
                        false ->  moveActivity(LoginActivity::class.java)
                    }
                }
            }
        }
    }

    private fun moveActivity(clazz: Class<*>) {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, clazz))
            finish()
        }, 2000)
    }
}