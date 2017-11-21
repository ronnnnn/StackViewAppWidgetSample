package com.ronnnnn.stackviewappwidgetsample.data

import android.content.Context
import android.content.SharedPreferences
import com.ronnnnn.stackviewappwidgetsample.data.model.AuthModelModule
import com.ronnnnn.stackviewappwidgetsample.di.ForAuth
import com.ronnnnn.stackviewappwidgetsample.di.ForGeneral
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by kokushiseiya on 2017/11/21.
 */
@Module(includes = arrayOf(AuthModelModule::class))
class AppDataModule {

    companion object {
        private const val SHARED_PREFERENCE_NAME = "stack_view_app_widget_sample_shared_preference_name"
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

    @Provides
    @ForGeneral
    fun provideEndpoint(): String = "https://api.dribbble.com/v1/"

    @Provides
    @ForAuth
    fun provideAuthEndpoint(): String = "https://dribbble.com/oauth/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()

    @Provides
    @ForGeneral
    fun provideRetrofit(@ForGeneral endpoint: String, client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(endpoint)
                    .client(client)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Provides
    @ForAuth
    fun provideAuthRetrofit(@ForAuth authEndpoint: String, client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(authEndpoint)
                    .client(client)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    interface Provider : AuthModelModule.Provider {

        fun sharedPreferences(): SharedPreferences

        @ForGeneral
        fun endpoint(): String

        @ForAuth
        fun authEndpoint(): String

        fun okHttpClient(): OkHttpClient

        @ForGeneral
        fun retrofit(): Retrofit

        @ForAuth
        fun authRetrofit(): Retrofit
    }
}