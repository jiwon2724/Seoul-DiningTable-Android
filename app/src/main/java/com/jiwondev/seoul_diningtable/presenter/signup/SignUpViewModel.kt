package com.jiwondev.seoul_diningtable.presenter.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiwondev.seoul_diningtable.domain.auth.entity.register.RegisterRequest
import com.jiwondev.seoul_diningtable.domain.auth.usecase.RegisterUseCase
import com.jiwondev.seoul_diningtable.domain.auth.usecase.ValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val registerUseCase: RegisterUseCase): ViewModel() {
    var type: String = ""
    var userEmail: String = ""

    fun postRegister(userName: String) {
        val request = RegisterRequest(
            userEmail = userEmail,
            userType = type,
            userName = userName
        )

        viewModelScope.launch {
            registerUseCase.postUserRegister(request)
                .onStart {  }
                .catch {  }
                .collectLatest {

                }
        }
    }
}