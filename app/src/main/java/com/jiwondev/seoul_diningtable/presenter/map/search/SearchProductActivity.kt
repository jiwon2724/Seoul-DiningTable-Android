package com.jiwondev.seoul_diningtable.presenter.map.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jiwondev.seoul_diningtable.R

class SearchProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)

        overridePendingTransition(com.airbnb.lottie.R.anim.abc_fade_in, com.airbnb.lottie.R.anim.abc_fade_out)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(com.airbnb.lottie.R.anim.abc_fade_in, com.airbnb.lottie.R.anim.abc_fade_out)
    }
}