package com.ronnnnn.stackviewappwidgetsample.domain

import android.content.Context
import com.ronnnnn.stackviewappwidgetsample.data.model.auth.DribbbleOAuthModel
import com.ronnnnn.stackviewappwidgetsample.domain.auth.RegisterCode
import dagger.Module
import dagger.Provides

/**
 * Created by kokushiseiya on 2017/11/21.
 */
@Module
class AppDomainModule {

    @Provides
    fun provideRegisterCode(context: Context, dribbbleOAuthModel: DribbbleOAuthModel): RegisterCode =
            RegisterCode(context, dribbbleOAuthModel)

    interface Provider {

        fun registerCode(): RegisterCode
    }
}