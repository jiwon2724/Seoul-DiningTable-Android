package com.jiwondev.seoul_diningtable.presenter.login

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jiwondev.seoul_diningtable.R
import com.jiwondev.seoul_diningtable.databinding.ActivityLoginBinding
import com.jiwondev.seoul_diningtable.presenter.base.BaseActivity
import com.jiwondev.seoul_diningtable.presenter.common.toast
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>({ActivityLoginBinding.inflate(it)}) {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observe()
        setClickListener()
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
            override fun onSuccess(response: NidProfileResponse) {
                Log.d("getUserInfo : ", response.profile?.id.toString())
            }
            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
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
                Log.i("Kakao Success", "카카오계정으로 로그인 성공 ${token.accessToken}")
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) return@loginWithKakaoTalk
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
                    // TODO 로그인 후 로직 실행
                    Log.i("KakaoLoginSuccess", "카카오톡으로 로그인 성공 ${token.accessToken}")
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

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.testFlow.collect {
                    Log.d("getUser : ", it.toString())
                }
            }
        }
    }
}
