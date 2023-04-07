package com.jiwondev.seoul_diningtable.presenter.map

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

class MapActivity : BaseActivity<ActivityMapBinding>({ActivityMapBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getPermission()
    }

    private fun init() {
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(host.navController)
    }

    private fun getPermission() {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 승인", Toast.LENGTH_SHORT).show()
            init()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                GRANT_REQUEST_CODE
            )
        }
    }

    private fun startSettingScreen() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:${packageName}")) // 설정화면
        with(intent) {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // grantResults : 권한 승인 결과 -> 권한에 순서에 따라 대응된다.
        when(requestCode) {
            GRANT_REQUEST_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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