package com.jiwondev.seoul_diningtable.presenter.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.jiwondev.seoul_diningtable.R
import com.navercorp.nid.NaverIdLoginSDK

class LoginActivity : AppCompatActivity() {

    /**
     * launcher나 OAuthLoginCallback을 authenticate() 메서드 호출 시 파라미터로 전달하거나 NidOAuthLoginButton 객체에 등록하면 인증이 종료되는 것을 확인할 수 있습니다.
     */
    // TODO 클릭 리스너
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        when(result.resultCode) {
            RESULT_OK -> {
                // 네이버 로그인 인증이 성공했을 때 수행할 코드 추가

            }
            RESULT_CANCELED -> {
                // 실패 or 에러

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        NaverIdLoginSDK.authenticate(this, launcher)

    }
}