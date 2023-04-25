package com.jiwondev.seoul_diningtable.presenter.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiwondev.seoul_diningtable.domain.auth.validation.entity.ValidationDto
import com.jiwondev.seoul_diningtable.domain.auth.validation.usecase.ValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validationUserCase: ValidationUseCase
    ): ViewModel() {

    // TODO : StateFlow로 바꿔야해.
    var type: String = "guest"

    var testFlow = flowOf<ValidationDto>()

    fun getValidation(userEmail: String) = viewModelScope.launch {
        Log.d("userEmail  : ", userEmail.toString())
        testFlow = validationUserCase.getUserValidation(userEmail)
    }
}