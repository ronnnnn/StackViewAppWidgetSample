package com.ronnnnn.stackviewappwidgetsample.data.model

import com.ronnnnn.stackviewappwidgetsample.data.local.AuthLocalModule
import com.ronnnnn.stackviewappwidgetsample.data.local.auth.DribbbleOAuthDb
import com.ronnnnn.stackviewappwidgetsample.data.model.auth.DribbbleOAuthModel
import com.ronnnnn.stackviewappwidgetsample.data.remote.AuthRemoteModule
import com.ronnnnn.stackviewappwidgetsample.data.remote.auth.DribbbleOAuthApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by kokushiseiya on 2017/11/21.
 */
@Module(includes = arrayOf(AuthRemoteModule::class, AuthLocalModule::class))
class AuthModelModule {

    @Singleton
    @Provides
    fun provideDribbbleOAuthModel(
            dribbbleOAuthApi: DribbbleOAuthApi,
            dribbbleOAuthDb: DribbbleOAuthDb
    ): DribbbleOAuthModel =
            DribbbleOAuthModel(dribbbleOAuthApi, dribbbleOAuthDb)

    interface Provider : AuthRemoteModule.Provider, AuthLocalModule.Provider {

        fun dribbbleOAuthModel(): DribbbleOAuthModel
    }
}