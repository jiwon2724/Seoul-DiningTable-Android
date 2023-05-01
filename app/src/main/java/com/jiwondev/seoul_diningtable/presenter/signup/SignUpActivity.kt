package com.jiwondev.seoul_diningtable.presenter.signup

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.jiwondev.seoul_diningtable.databinding.ActivitySignUpBinding
import com.jiwondev.seoul_diningtable.presenter.base.BaseActivity
import com.jiwondev.seoul_diningtable.presenter.common.Constant
import com.jiwondev.seoul_diningtable.presenter.common.Constant.Companion.OWNER
import com.jiwondev.seoul_diningtable.presenter.common.extensions.isNotEmpty
import com.jiwondev.seoul_diningtable.presenter.common.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>({ActivitySignUpBinding.inflate(it)}) {
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        observe()
        goToLoginActivity()
        signUp()
    }


    private fun signUp() {
        binding.signUpTextView.setOnClickListener {
            if()
        }
    }

    private fun goToLoginActivity() { binding.signupLeftArrowButton.setOnClickListener { finish() } }

    private fun init() {
        intent.apply {
            signUpViewModel.type = getStringExtra("type") ?: ""
            signUpViewModel.userEmail = getStringExtra("userEmail") ?: ""
        }

        if(signUpViewModel.type == OWNER) binding.ownerConstraintLayout.visible()
    }

    private fun observe() {

    }

    private fun validate() : Boolean {
        return when(signUpViewModel.type) {
            OWNER -> {
                val editTextIsNotEmpty = binding.nicknameEditText.isNotEmpty()
                        && binding.storeNameEditText.isNotEmpty()
                        && binding.storeNumberEditText.isNotEmpty()

                val checkBoxIsChecked = binding.checkBoxAllTime.isChecked
                false
            }
            else -> binding.nicknameEditText.isNotEmpty()
        }
    }

    private fun
}