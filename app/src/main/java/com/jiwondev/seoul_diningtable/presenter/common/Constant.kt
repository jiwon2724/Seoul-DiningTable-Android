package com.jiwondev.seoul_diningtable.presenter.common

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

class Constant {
    companion object {
        const val GRANT_REQUEST_CODE = 1000 // 권한 승인 코드

        // api endpoint
        const val GET_MART = "mart"
        const val POST_LOGIN_AND_SIGN_UP = "users/insert"
        const val GET_VALIDATION = "users/search"

        // store code
        const val JONGROGU = 1
        const val JOONGU = 2
        const val YONGSANGU = 3
        const val SUNGDONGU = 4
        const val GWANGJINGU = 5
        const val DONGDAEMUNGU = 6
        const val JOONGRANGU = 7
        const val SUNGBUKGU = 8
        const val GANGBUKGU = 9
        const val DOBONGU = 10
        const val NOWONGU = 11
        const val EUNPYEONGU = 12
        const val SEODAEMUNGU = 13
        const val MAPOGU = 14
        const val YANGCHUNGU = 15
        const val GANGSEOGU = 16
        const val GUROGU = 17
        const val GUMCHEONGU = 18
        const val YOUNGDEUNGPOGU = 19
        const val DONGJAKGU = 20
        const val GWANAKGU = 21
        const val SEOCHOGU = 22
        const val GANGNAMGU = 23
        const val SONGPAGU = 24
        const val GANGDONGU = 25

        val AUTO_LOGIN = booleanPreferencesKey("auto_login")
        val LAST_LOGIN_SNS = stringPreferencesKey("last_login_sns")
        val LAST_LOGIN_TYPE = stringPreferencesKey("last_login_type")
        val USER_EMAIL = stringPreferencesKey("user_email")
    }
}