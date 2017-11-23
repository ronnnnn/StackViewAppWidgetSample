package com.ronnnnn.stackviewappwidgetsample.data.remote

import android.content.Context
import com.ronnnnn.stackviewappwidgetsample.data.remote.auth.DribbbleOAuthApi
import com.ronnnnn.stackviewappwidgetsample.data.remote.auth.DribbbleOAuthApiClient
import com.ronnnnn.stackviewappwidgetsample.di.ForAuth
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

    interface Provider {

        fun dribbbleOAuthApi(): DribbbleOAuthApi
    }
}