package com.ronnnnn.stackviewappwidgetsample.data

import android.content.Context
import android.content.SharedPreferences
import com.ronnnnn.stackviewappwidgetsample.R
import com.ronnnnn.stackviewappwidgetsample.data.local.auth.DribbbleOAuthPrefClient
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

    @Singleton
    @Provides
    fun provideOkHttpClient(sharedPreferences: SharedPreferences): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor { chain ->
                        sharedPreferences.getString(DribbbleOAuthPrefClient.KEY_ACCESS_TOKEN, null).let {
                            chain.request().newBuilder()
                                    .addHeader("Authorization", "Bearer $it")
                                    .build()
                                    .let { chain.proceed(it) }
                        }
                    }
                    .build()

    @Singleton
    @Provides
    @ForGeneral
    fun provideRetrofit(context: Context, client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(context.getString(R.string.dribbble_end_point))
                    .client(client)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Singleton
    @Provides
    @ForAuth
    fun provideAuthRetrofit(context: Context, client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(context.getString(R.string.dribbble_auth_end_point))
                    .client(client)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    interface Provider : AuthModelModule.Provider {

        fun sharedPreferences(): SharedPreferences

        fun okHttpClient(): OkHttpClient

        @ForGeneral
        fun retrofit(): Retrofit

        @ForAuth
        fun authRetrofit(): Retrofit
    }
}