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
import javax.inject.Named
import javax.inject.Singleton

// TODO : 보일러 플레이트 코드 제거해야해.

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    @Named("geocoding")
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

    @Singleton
    @Provides
    @Named("default")
    fun provideDefaultRetrofit() : Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            client(client)
            baseUrl(BuildConfig.BASE_URL)
        }.build()
    }

    @Singleton
    @Provides
    @Named("store")
    fun provideStoreRetrofit() : Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            client(client)
            baseUrl(BuildConfig.BASE_URL)
        }.build()
    }
}