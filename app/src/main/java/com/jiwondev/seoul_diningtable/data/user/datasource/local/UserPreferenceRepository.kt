package com.jiwondev.seoul_diningtable.data.user.datasource.local

import android.content.Context
import com.jiwondev.seoul_diningtable.data.user.model.UserPreference
import com.jiwondev.seoul_diningtable.presenter.common.App.Companion.dataStore
import com.jiwondev.seoul_diningtable.presenter.common.Constant
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferenceRepository @Inject constructor(@ApplicationContext private val applicationContext: Context) {
    val userPreferencesFlow: Flow<UserPreference> = applicationContext.dataStore.data
        .map { preferences ->
            UserPreference(
                autoLogin = preferences[Constant.AUTO_LOGIN] ?: false,
                accessToken = preferences[Constant.ACCESS_TOKEN] ?: "",
                lastLoginSns = preferences[Constant.ACCESS_TOKEN] ?: "",
                lastLoginType = preferences[Constant.ACCESS_TOKEN] ?: "",
            )
        }
}