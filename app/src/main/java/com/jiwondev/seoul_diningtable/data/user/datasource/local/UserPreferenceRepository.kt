package com.jiwondev.seoul_diningtable.data.user.datasource.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.jiwondev.seoul_diningtable.data.user.model.UserPreference
import com.jiwondev.seoul_diningtable.presenter.common.App.Companion.dataStore
import com.jiwondev.seoul_diningtable.presenter.common.Constant
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferenceRepository @Inject constructor(@ApplicationContext private val applicationContext: Context) {
    val userPreferencesFlow: Flow<UserPreference> = applicationContext.dataStore.data
        .map { preferences ->
            UserPreference(
                autoLogin = preferences[Constant.AUTO_LOGIN] ?: false,
                userEmail = preferences[Constant.USER_EMAIL] ?: "",
                lastLoginSns = preferences[Constant.LAST_LOGIN_SNS] ?: "",
                lastLoginType = preferences[Constant.LAST_LOGIN_TYPE] ?: ""
            )
        }

    suspend fun updateUserPreference(userPreference: UserPreference) {
        applicationContext.dataStore.edit { preference ->
            preference[Constant.AUTO_LOGIN] = userPreference.autoLogin
            preference[Constant.USER_EMAIL] = userPreference.userEmail
        }
    }
}