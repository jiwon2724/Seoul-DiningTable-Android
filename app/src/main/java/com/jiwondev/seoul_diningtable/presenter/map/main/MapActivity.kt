package com.jiwondev.seoul_diningtable.presenter.map.main

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.jiwondev.seoul_diningtable.R
import com.jiwondev.seoul_diningtable.databinding.ActivityMapBinding
import com.jiwondev.seoul_diningtable.presenter.base.BaseActivity
import com.jiwondev.seoul_diningtable.presenter.common.Constant.Companion.GRANT_REQUEST_CODE
import com.jiwondev.seoul_diningtable.presenter.common.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapActivity : BaseActivity<ActivityMapBinding>({ActivityMapBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getPermission()
    }

    private fun init() {
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(host.navController)
    }

    private fun startSettingScreen() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:${packageName}")) // 설정화면
        with(intent) {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

    private fun getPermission() {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "권한 승인", Toast.LENGTH_SHORT).show()
            init()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                GRANT_REQUEST_CODE
            )
        }
    }

    private fun permissionsGrantResult(grantResults: IntArray) : Boolean {
        // grantResults : 권한 승인 결과 -> 권한 순서에 따라 대응된다.
        grantResults.forEach {
            if(it != PackageManager.PERMISSION_GRANTED) return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            GRANT_REQUEST_CODE -> {
                if(permissionsGrantResult(grantResults)) {
                    toast("권한이 승인.")
                    init()
                } else {
                    toast("권한이 승인되어야 앱 사용이 가능합니다.")
                    finish()

                    startSettingScreen()
                }
            }

            else -> {
                toast("권한이 승인되어야 앱 사용이 가능합니다.")
                finish()

                startSettingScreen()
            }
        }
    }
}