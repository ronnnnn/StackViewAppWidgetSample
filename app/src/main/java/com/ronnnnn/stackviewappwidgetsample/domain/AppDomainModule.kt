package com.ronnnnn.stackviewappwidgetsample.domain

import com.ronnnnn.stackviewappwidgetsample.data.model.auth.DribbbleOAuthModel
import com.ronnnnn.stackviewappwidgetsample.data.model.shots.DribbbleShotsModel
import com.ronnnnn.stackviewappwidgetsample.domain.auth.RegisterCode
import com.ronnnnn.stackviewappwidgetsample.domain.shots.GetPopularShots
import dagger.Module
import dagger.Provides

/**
 * Created by kokushiseiya on 2017/11/21.
 */
@Module
class AppDomainModule {

    @Provides
    fun provideRegisterCode(dribbbleOAuthModel: DribbbleOAuthModel): RegisterCode =
            RegisterCode(dribbbleOAuthModel)

    @Provides
    fun provideGetPopularShots(dribbbleShotsModel: DribbbleShotsModel): GetPopularShots =
            GetPopularShots(dribbbleShotsModel)

    interface Provider {

        fun registerCode(): RegisterCode

        fun getPopularShots(): GetPopularShots
    }
}