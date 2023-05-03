package com.jiwondev.seoul_diningtable.presenter.map.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jiwondev.seoul_diningtable.R
import com.jiwondev.seoul_diningtable.databinding.ActivitySearchProductBinding
import com.jiwondev.seoul_diningtable.presenter.base.BaseActivity

class SearchProductActivity : BaseActivity<ActivitySearchProductBinding>({ActivitySearchProductBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moveAnimation()

    }

    override fun finish() {
        super.finish()
        moveAnimation()
    }

    private fun moveAnimation() {
        overridePendingTransition(com.airbnb.lottie.R.anim.abc_fade_in, com.airbnb.lottie.R.anim.abc_fade_out)
    }
}