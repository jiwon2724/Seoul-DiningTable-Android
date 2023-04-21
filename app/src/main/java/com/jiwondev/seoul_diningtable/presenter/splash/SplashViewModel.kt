package com.jiwondev.seoul_diningtable.presenter.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiwondev.seoul_diningtable.data.user.datasource.local.UserPreferenceRepository
import com.jiwondev.seoul_diningtable.data.user.model.UserPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val userPreferenceRepository: UserPreferenceRepository) : ViewModel() {
    val userPreferenceFlow: Flow<UserPreference>
        get() = userPreferenceRepository.userPreferencesFlow
}