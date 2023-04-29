package com.jiwondev.seoul_diningtable.presenter.signup

import android.os.Bundle
import androidx.activity.viewModels
import com.jiwondev.seoul_diningtable.databinding.ActivitySignUpBinding
import com.jiwondev.seoul_diningtable.presenter.base.BaseActivity


class SignUpActivity : BaseActivity<ActivitySignUpBinding>({ActivitySignUpBinding.inflate(it)}) {
    val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        clickListener()
    }


    private fun clickListener() {
        binding.signupLeftArrowButton.setOnClickListener { finish() }
    }

    private fun init() {
        intent.apply {
            signUpViewModel.type = getStringExtra("type") ?: ""
            signUpViewModel.userEmail = getStringExtra("userEmail") ?: ""
        }
    }
}