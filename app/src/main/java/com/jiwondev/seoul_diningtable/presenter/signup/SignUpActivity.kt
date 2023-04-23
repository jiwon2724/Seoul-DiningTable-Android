package com.jiwondev.seoul_diningtable.presenter.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jiwondev.seoul_diningtable.R
import com.jiwondev.seoul_diningtable.databinding.ActivitySignUpBinding
import com.jiwondev.seoul_diningtable.presenter.base.BaseActivity

class SignUpActivity : BaseActivity<ActivitySignUpBinding>({ActivitySignUpBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        clickListener()

    }


    private fun clickListener() {
        binding.signupLeftArrowButton.setOnClickListener { finish() }
    }

    private fun init() {
        // TODO : Intent로 유저 type 받기.
    }
}