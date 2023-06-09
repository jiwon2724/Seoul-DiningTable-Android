package com.jiwondev.seoul_diningtable.presenter.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiwondev.seoul_diningtable.data.user.datasource.local.UserPreferenceRepository
import com.jiwondev.seoul_diningtable.data.user.model.UserPreference
import com.jiwondev.seoul_diningtable.domain.auth.entity.validation.ValidationDto
import com.jiwondev.seoul_diningtable.domain.auth.usecase.ValidationUseCase
import com.jiwondev.seoul_diningtable.domain.common.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validationUserCase: ValidationUseCase,
    private val userPreferenceRepository: UserPreferenceRepository
    ): ViewModel() {

    var type: String = "guest"
    var userEmail: String = ""
    var lastLoginSns = ""

    private val _loginUiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Init)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    val userPreferenceFlow: Flow<UserPreference>
        get() = userPreferenceRepository.userPreferencesFlow

    fun getValidation(userEmail: String) {
        viewModelScope.launch {
            validationUserCase.getUserValidation(userEmail)
                .onStart { setLoading() }
                .catch { hideLoading() }
                .collect { response ->
                    this@LoginViewModel.userEmail = userEmail
                    hideLoading()

                    when(response) {
                        is BaseResult.Success -> _loginUiState.value = LoginUiState.IsSuccess(response.data)
                        is BaseResult.Error -> {} // TODO : 실패시 response dto 보고 작성.
                    }
                }
        }
    }

    fun updateUserPreference(userPreference: UserPreference) = viewModelScope.launch {
        userPreferenceRepository.updateUserPreference(userPreference)
    }

    private fun setLoading() { _loginUiState.value = LoginUiState.IsLoading(true) }
    private fun hideLoading() { _loginUiState.value = LoginUiState.IsLoading(false) }
}

// TODO : State 공통으로 사용하는 부분 생각.
sealed class LoginUiState {
    object Init : LoginUiState()
    data class IsLoading(val isLoading: Boolean = false) : LoginUiState()
    data class IsSuccess(val successDto: ValidationDto) : LoginUiState()
    data class IsFailed(val failedDto: String) : LoginUiState()
}