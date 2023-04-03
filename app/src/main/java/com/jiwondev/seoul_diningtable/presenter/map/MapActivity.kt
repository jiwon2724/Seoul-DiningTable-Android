package com.jiwondev.seoul_diningtable.presenter.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.jiwondev.seoul_diningtable.R
import com.jiwondev.seoul_diningtable.databinding.ActivityMapBinding
import com.jiwondev.seoul_diningtable.presenter.base.BaseActivity

class MapActivity : BaseActivity<ActivityMapBinding>({ActivityMapBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(host.navController)
    }
}