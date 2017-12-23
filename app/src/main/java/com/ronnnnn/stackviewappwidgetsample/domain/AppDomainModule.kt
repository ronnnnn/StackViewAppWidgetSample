package com.ronnnnn.stackviewappwidgetsample.domain

import com.ronnnnn.stackviewappwidgetsample.domain.auth.RegisterCode
import com.ronnnnn.stackviewappwidgetsample.domain.likes.CheckLike
import com.ronnnnn.stackviewappwidgetsample.domain.likes.LikeShot
import com.ronnnnn.stackviewappwidgetsample.domain.likes.UnlikeShot
import com.ronnnnn.stackviewappwidgetsample.domain.shots.GetPopularShots
import com.ronnnnn.stackviewappwidgetsample.model.auth.DribbbleOAuthModel
import com.ronnnnn.stackviewappwidgetsample.model.likes.DribbbleLikesModel
import com.ronnnnn.stackviewappwidgetsample.model.shots.DribbbleShotsModel
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

    @Provides
    fun provideCheckLike(dribbbleLikesModel: DribbbleLikesModel): CheckLike =
            CheckLike(dribbbleLikesModel)

    @Provides
    fun provideLikeShot(dribbbleLikesModel: DribbbleLikesModel): LikeShot =
            LikeShot(dribbbleLikesModel)

    @Provides
    fun provideUnlikeShot(dribbbleLikesModel: DribbbleLikesModel): UnlikeShot =
            UnlikeShot(dribbbleLikesModel)

    interface Provider {

        fun registerCode(): RegisterCode

        fun getPopularShots(): GetPopularShots

        fun checkLike(): CheckLike

        fun likeShot(): LikeShot

        fun unlikeShot(): UnlikeShot
    }
}