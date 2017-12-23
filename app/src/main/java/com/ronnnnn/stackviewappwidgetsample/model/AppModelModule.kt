package com.ronnnnn.stackviewappwidgetsample.model

import com.ronnnnn.stackviewappwidgetsample.data.local.AuthLocalModule
import com.ronnnnn.stackviewappwidgetsample.data.local.auth.DribbbleOAuthDb
import com.ronnnnn.stackviewappwidgetsample.data.remote.AuthRemoteModule
import com.ronnnnn.stackviewappwidgetsample.data.remote.auth.DribbbleOAuthApi
import com.ronnnnn.stackviewappwidgetsample.data.remote.likes.DribbbleLikesApi
import com.ronnnnn.stackviewappwidgetsample.data.remote.shots.DribbbleShotsApi
import com.ronnnnn.stackviewappwidgetsample.model.auth.DribbbleOAuthModel
import com.ronnnnn.stackviewappwidgetsample.model.likes.DribbbleLikesModel
import com.ronnnnn.stackviewappwidgetsample.model.shots.DribbbleShotsModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by kokushiseiya on 2017/11/21.
 */
@Module(includes = arrayOf(AuthRemoteModule::class, AuthLocalModule::class))
class AppModelModule {

    @Singleton
    @Provides
    fun provideDribbbleOAuthModel(
            dribbbleOAuthApi: DribbbleOAuthApi,
            dribbbleOAuthDb: DribbbleOAuthDb
    ): DribbbleOAuthModel =
            DribbbleOAuthModel(dribbbleOAuthApi, dribbbleOAuthDb)

    @Singleton
    @Provides
    fun provideDribbbleShotsModel(dribbbleShotsApi: DribbbleShotsApi): DribbbleShotsModel =
            DribbbleShotsModel(dribbbleShotsApi)

    @Singleton
    @Provides
    fun provideDribbbleLikesModel(dribbbleLikesApi: DribbbleLikesApi): DribbbleLikesModel =
            DribbbleLikesModel(dribbbleLikesApi)

    interface Provider : AuthRemoteModule.Provider, AuthLocalModule.Provider {

        fun dribbbleOAuthModel(): DribbbleOAuthModel

        fun dribbbleShotsModel(): DribbbleShotsModel

        fun dribbbleLikesModel(): DribbbleLikesModel
    }
}