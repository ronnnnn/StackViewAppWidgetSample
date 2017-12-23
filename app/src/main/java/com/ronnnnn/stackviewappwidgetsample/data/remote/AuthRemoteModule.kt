package com.ronnnnn.stackviewappwidgetsample.data.remote

import android.content.Context
import com.ronnnnn.stackviewappwidgetsample.data.remote.auth.DribbbleOAuthApi
import com.ronnnnn.stackviewappwidgetsample.data.remote.auth.DribbbleOAuthApiClient
import com.ronnnnn.stackviewappwidgetsample.data.remote.likes.DribbbleLikesApi
import com.ronnnnn.stackviewappwidgetsample.data.remote.likes.DribbbleLikesApiClient
import com.ronnnnn.stackviewappwidgetsample.data.remote.shots.DribbbleShotsApi
import com.ronnnnn.stackviewappwidgetsample.data.remote.shots.DribbbleShotsApiClient
import com.ronnnnn.stackviewappwidgetsample.di.ForAuth
import com.ronnnnn.stackviewappwidgetsample.di.ForGeneral
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by kokushiseiya on 2017/11/21.
 */
@Module
class AuthRemoteModule {

    @Singleton
    @Provides
    fun provideDribbbleOAuthApi(context: Context, @ForAuth authRetrofit: Retrofit): DribbbleOAuthApi =
            DribbbleOAuthApiClient(context, authRetrofit)

    @Singleton
    @Provides
    fun provideDribbbleShotsApi(@ForGeneral retrofit: Retrofit): DribbbleShotsApi =
            DribbbleShotsApiClient(retrofit)

    @Singleton
    @Provides
    fun provideDribbbleLikesApi(@ForGeneral retrofit: Retrofit): DribbbleLikesApi =
            DribbbleLikesApiClient(retrofit)

    interface Provider {

        fun dribbbleOAuthApi(): DribbbleOAuthApi

        fun dribbbleShotsApi(): DribbbleShotsApi

        fun dribbbleLikesApi(): DribbbleLikesApi
    }
}