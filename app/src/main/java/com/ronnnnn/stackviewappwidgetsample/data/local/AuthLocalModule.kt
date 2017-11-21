package com.ronnnnn.stackviewappwidgetsample.data.local

import android.content.SharedPreferences
import com.ronnnnn.stackviewappwidgetsample.data.local.auth.DribbbleOAuthDb
import com.ronnnnn.stackviewappwidgetsample.data.local.auth.DribbbleOAuthPrefClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by kokushiseiya on 2017/11/22.
 */
@Module
class AuthLocalModule {

    @Singleton
    @Provides
    fun provideDribbbleOAuthDb(sharedPreferences: SharedPreferences): DribbbleOAuthDb =
            DribbbleOAuthPrefClient(sharedPreferences)

    interface Provider {

        fun dribbbleOAuthDb(): DribbbleOAuthDb
    }
}