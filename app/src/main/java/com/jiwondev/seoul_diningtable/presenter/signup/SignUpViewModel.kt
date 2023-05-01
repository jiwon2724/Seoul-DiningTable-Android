package com.jiwondev.seoul_diningtable.presenter.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiwondev.seoul_diningtable.data.user.datasource.local.UserPreferenceRepository
import com.jiwondev.seoul_diningtable.data.user.model.UserPreference
import com.jiwondev.seoul_diningtable.domain.auth.entity.register.RegisterDto
import com.jiwondev.seoul_diningtable.domain.auth.entity.register.RegisterRequest
import com.jiwondev.seoul_diningtable.domain.auth.entity.validation.ValidationDto
import com.jiwondev.seoul_diningtable.domain.auth.usecase.RegisterUseCase
import com.jiwondev.seoul_diningtable.domain.auth.usecase.ValidationUseCase
import com.jiwondev.seoul_diningtable.domain.common.BaseResult
import com.jiwondev.seoul_diningtable.presenter.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val userPreferenceRepository: UserPreferenceRepository
    ): ViewModel() {
    var type: String = ""
    var userEmail: String = ""
    var lastLoginSns: String = ""

    private var _signUpUiState: MutableStateFlow<SignUpUiState> = MutableStateFlow(SignUpUiState.Init)
    val signUpUiState: StateFlow<SignUpUiState> = _signUpUiState.asStateFlow()

    // TODO 다른 뷰모델이랑 중복되는 거 재사용 좋게 만들어야해.
    val userPreferenceFlow: Flow<UserPreference>
        get() = userPreferenceRepository.userPreferencesFlow

    fun postRegister(userName: String) {
        val request = RegisterRequest(
            userEmail = userEmail,
            userType = type,
            userName = userName
        )

        viewModelScope.launch {
            registerUseCase.postUserRegister(request)
                .onStart { setLoading() }
                .catch { hideLoading() }
                .collectLatest { response ->
                    hideLoading()

                    when(response) {
                        is BaseResult.Success -> _signUpUiState.value = SignUpUiState.IsSuccess(response.data)
                        is BaseResult.Error -> {} // TODO : 실패시 response dto 보고 작성.
                    }
                }
        }
    }

    fun updateUserPreference(userPreference: UserPreference) = viewModelScope.launch {
        userPreferenceRepository.updateUserPreference(userPreference)
    }

    // TODO : State 공통으로 사용하는 부분 생각.
    private fun setLoading() { _signUpUiState.value = SignUpUiState.IsLoading(true) }
    private fun hideLoading() { _signUpUiState.value = SignUpUiState.IsLoading(false) }
}

// TODO : State 공통으로 사용하는 부분 생각.
sealed class SignUpUiState {
    object Init : SignUpUiState()
    data class IsLoading(val isLoading: Boolean = false) : SignUpUiState()
    data class IsSuccess(val successDto: RegisterDto) : SignUpUiState()
    data class IsFailed(val failedDto: String) : SignUpUiState()
}