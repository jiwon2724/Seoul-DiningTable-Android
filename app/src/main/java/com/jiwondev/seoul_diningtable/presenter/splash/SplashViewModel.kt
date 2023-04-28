package com.jiwondev.seoul_diningtable.presenter.splash

import androidx.lifecycle.ViewModel
import com.jiwondev.seoul_diningtable.data.user.datasource.local.UserPreferenceRepository
import com.jiwondev.seoul_diningtable.data.user.model.UserPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val userPreferenceRepository: UserPreferenceRepository) : ViewModel() {
    val userPreferenceFlow: Flow<UserPreference>
        get() = userPreferenceRepository.userPreferencesFlow
}