package com.jiwondev.seoul_diningtable.presenter.signup

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.jiwondev.seoul_diningtable.databinding.ActivitySignUpBinding
import com.jiwondev.seoul_diningtable.presenter.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>({ActivitySignUpBinding.inflate(it)}) {
    private val signUpViewModel: SignUpViewModel by viewModels()

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

        Log.d("result ", "result : ${signUpViewModel.userEmail}")
        Log.d("result ", "result : ${signUpViewModel.type}")
    }
}