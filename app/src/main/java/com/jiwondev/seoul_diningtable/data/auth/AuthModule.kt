package com.jiwondev.seoul_diningtable.data.auth

import com.jiwondev.seoul_diningtable.data.auth.datasource.reomote.AuthApi
import com.jiwondev.seoul_diningtable.data.auth.repository.AuthImpl
import com.jiwondev.seoul_diningtable.data.common.NetworkModule
import com.jiwondev.seoul_diningtable.domain.auth.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)

object AuthModule {
    @Singleton
    @Provides
    fun provideAuthApi(@Named("default") retrofit: Retrofit) : AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(authApi: AuthApi) : AuthRepository {
        return AuthImpl(authApi)
    }
}