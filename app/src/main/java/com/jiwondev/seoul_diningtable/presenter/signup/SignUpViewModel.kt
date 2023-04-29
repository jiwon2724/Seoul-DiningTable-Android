package com.jiwondev.seoul_diningtable.presenter.signup

import androidx.lifecycle.ViewModel
import com.jiwondev.seoul_diningtable.domain.auth.usecase.ValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val useCase: ValidationUseCase): ViewModel() {
    var type: String = ""
    var userEmail: String = ""


}