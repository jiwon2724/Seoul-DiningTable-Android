package com.jiwondev.seoul_diningtable.presenter.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jiwondev.seoul_diningtable.R
import com.jiwondev.seoul_diningtable.data.user.model.UserPreference
import com.jiwondev.seoul_diningtable.databinding.ActivitySignUpBinding
import com.jiwondev.seoul_diningtable.presenter.base.BaseActivity
import com.jiwondev.seoul_diningtable.presenter.common.Constant.Companion.OWNER
import com.jiwondev.seoul_diningtable.presenter.common.extensions.isNotEmpty
import com.jiwondev.seoul_diningtable.presenter.common.extensions.visible
import com.jiwondev.seoul_diningtable.presenter.common.toast
import com.jiwondev.seoul_diningtable.presenter.map.MapActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    signUpViewModel.signUpUiState.collect { state ->
                        when(state) {
                            is SignUpUiState.Init -> Unit
                            is SignUpUiState.IsSuccess -> saveUserInfo()
                            is SignUpUiState.IsFailed -> toast(resources.getString(R.string.signup_failed))
                            is SignUpUiState.IsLoading -> {}
                        }
                    }
                }

                launch {
                    signUpViewModel.userPreferenceFlow.collect { preference ->
                        if(preference.autoLogin) goToMapActivity()
                    }
                }
            }
        }
    }

    private fun init() {
        intent.apply {
            signUpViewModel.type = getStringExtra("type") ?: ""
            signUpViewModel.userEmail = getStringExtra("userEmail") ?: ""
        }

        if(signUpViewModel.type == OWNER) binding.ownerConstraintLayout.visible()
    }

    private fun signUp() {
        binding.signUpTextView.setOnClickListener {
            if(validate()) {
                signUpViewModel.postRegister(binding.nicknameEditText.text.trim().toString())
            } else toast(resources.getString(R.string.signup_validate_failed))
        }
    }

    private fun validate() : Boolean {
        return when(signUpViewModel.type) {
            OWNER -> {
                val checkBoxIsChecked = isCheckBoxChecked()

                // TODO : 좀 더 좋은방법 생각해보기.
                val editTextIsNotEmpty = binding.nicknameEditText.isNotEmpty()
                        && binding.storeNameEditText.isNotEmpty()
                        && binding.storeNumberEditText.isNotEmpty()

                return if(editTextIsNotEmpty) { checkBoxIsChecked } else false
            }
            else -> binding.nicknameEditText.isNotEmpty()
        }
    }

    // TODO : 좀 더 좋은방법 생각해보기.
    private fun isCheckBoxChecked(): Boolean {
        return (binding.checkBoxAllTime.isChecked
                || binding.checkBox14To16.isChecked
                || binding.checkBox110To12.isChecked
                || binding.checkBox16To18.isChecked
                || binding.checkBox18To20.isChecked)
    }

    private fun goToMapActivity() {
        startActivity(Intent(this, MapActivity::class.java))
    }

    private fun goToLoginActivity() {
        binding.signupLeftArrowButton.setOnClickListener { finish() }
    }

    private fun saveUserInfo() {
        val userPreference = UserPreference(
            autoLogin = true,
            userEmail = signUpViewModel.userEmail,
            lastLoginType = signUpViewModel.type,
            lastLoginSns = signUpViewModel.lastLoginSns
        )
        signUpViewModel.updateUserPreference(userPreference)
    }
}