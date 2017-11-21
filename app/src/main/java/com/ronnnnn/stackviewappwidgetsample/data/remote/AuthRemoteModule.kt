package com.ronnnnn.stackviewappwidgetsample.data.remote

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
    fun provideDribbbleOAuthApi(@ForAuth authRetrofit: Retrofit): DribbbleOAuthApi =
            DribbbleOAuthApiClient(authRetrofit)

    interface Provider {

        fun dribbbleOAuthApi(): DribbbleOAuthApi
    }
}