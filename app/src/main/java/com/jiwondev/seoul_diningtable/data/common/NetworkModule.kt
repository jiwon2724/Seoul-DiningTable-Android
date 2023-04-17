package com.jiwondev.seoul_diningtable.data.common

import com.jiwondev.seoul_diningtable.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideGeocodingBaseRetrofit() : Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            client(client)
            baseUrl("https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/")
        }.build()
    }

    fun provideDefaultRetorfit() : Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            client(client)
            baseUrl(BuildConfig.)
        }.build()
    }
}