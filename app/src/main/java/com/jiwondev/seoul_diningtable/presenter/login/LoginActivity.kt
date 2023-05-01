package com.jiwondev.seoul_diningtable.presenter.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jiwondev.seoul_diningtable.R
import com.jiwondev.seoul_diningtable.data.user.model.UserPreference
import com.jiwondev.seoul_diningtable.databinding.ActivityLoginBinding
import com.jiwondev.seoul_diningtable.presenter.base.BaseActivity
import com.jiwondev.seoul_diningtable.presenter.common.toast
import com.jiwondev.seoul_diningtable.presenter.common.gone
import com.jiwondev.seoul_diningtable.presenter.common.visible
import com.jiwondev.seoul_diningtable.presenter.map.MapActivity
import com.jiwondev.seoul_diningtable.presenter.signup.SignUpActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>({ActivityLoginBinding.inflate(it)}) {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setClickListener()
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    loginViewModel.loginLoginUiState.collect { state ->
                        Log.d("Current State : ", "Init")
                        when(state) {
                            is LoginUiState.Init -> Unit
                            is LoginUiState.IsSuccess -> state.successDto.data?.let { saveUserInfo() } ?: moveSignUpActivity()
                            is LoginUiState.IsFailed -> {} // TODO : Failed dto 받으면 넣어야해.
                            is LoginUiState.IsLoading -> handleProgressBar(state.isLoading)
                        }
                    }
                }
                launch {
                    loginViewModel.userPreferenceFlow.collect { preference ->
                        Log.d("preference : ", preference.toString())
                        if(preference.autoLogin) startActivity(Intent(this@LoginActivity, MapActivity::class.java))
                    }
                }
            }
        }
    }

    /** Naver **/
    private fun startNaverLogin() {
        val oauthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                getNaverUserInfo()

            }
            override fun onFailure(httpStatus: Int, message: String) {

            }

            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        }
        NaverIdLoginSDK.authenticate(this, oauthLoginCallback)
    }
    private fun getNaverUserInfo() {
        NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse> {
            override fun onSuccess(result: NidProfileResponse) {
                val userEmail = result.profile?.id ?: ""

                when(userEmail.isNotEmpty()) {
                    true -> {
                        loginViewModel.getValidation(userEmail)
                        loginViewModel.lastLoginSns = "naver"
                    }
                    false -> toast(resources.getString(R.string.failed_naver_login))
                }
            }
            override fun onFailure(httpStatus: Int, message: String) {
                toast("$httpStatus : $message")
            }
            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        })
    }

    /** Kakao **/
    private fun startKakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                toast(resources.getString(R.string.failed_kakao_login))
            } else if (token != null) {
                loginViewModel.lastLoginSns = "kakao"
                loginViewModel.getValidation(token.accessToken)
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) return@loginWithKakaoTalk
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
                    loginViewModel.lastLoginSns = "kakao"
                    loginViewModel.getValidation(token.accessToken)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }

    /** UI **/
    private fun setClickListener() {
        binding.naverImageView.setOnClickListener { startNaverLogin() }
        binding.kakaoImageView.setOnClickListener { startKakaoLogin() }

        binding.guestTextView.setOnClickListener {
            loginViewModel.type = "guest"
            changeUserTypeUi(loginViewModel.type)
        }

        binding.ownerTextView.setOnClickListener {
            loginViewModel.type = "owner"
            changeUserTypeUi(loginViewModel.type)
        }
    }
    private fun changeUserTypeUi(type: String) {
        when(type) {
            "guest" -> {
                binding.guestTextView.setBackgroundResource(R.drawable.select_state_raduis_8dp_bg)
                binding.ownerTextView.setBackgroundResource(R.drawable.no_select_state_bg)
                binding.loginWordTextView.text = resources.getString(R.string.login_screen_sentence_for_guest)
                binding.loginTypeTextView.text = resources.getString(R.string.login_screen_guest_login)
            }
            "owner" -> {
                binding.guestTextView.setBackgroundResource(R.drawable.no_select_state_bg)
                binding.ownerTextView.setBackgroundResource(R.drawable.select_state_raduis_8dp_bg)
                binding.loginWordTextView.text = resources.getString(R.string.login_screen_sentence_for_owner)
                binding.loginTypeTextView.text = resources.getString(R.string.login_screen_owner_login)
            }
        }
    }
    private fun handleProgressBar(boolean : Boolean) {
        when(boolean) {
            true -> binding.loginProgressBar.gone()
            false -> binding.loginProgressBar.visible()
        }
    }
    private fun saveUserInfo() {
        val userPreference = UserPreference(
            autoLogin = true,
            userEmail = loginViewModel.userEmail,
            lastLoginType = loginViewModel.type,
            lastLoginSns = loginViewModel.lastLoginSns
        )
        loginViewModel.updateUserPreference(userPreference)
    }
    private fun moveSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        intent.apply {
            putExtra("userEmail", loginViewModel.userEmail)
            putExtra("type", loginViewModel.type)
        }
        startActivity(intent)
    }
}
