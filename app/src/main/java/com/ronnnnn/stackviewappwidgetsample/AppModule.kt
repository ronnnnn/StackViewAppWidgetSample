package com.ronnnnn.stackviewappwidgetsample

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by kokushiseiya on 2017/11/21.
 */
@Module
class AppModule(private val app: App) {

    @Provides
    fun provideApplication(): Application = app

    @Provides
    fun provideContext(): Context = app.applicationContext

    interface Provider {

        fun application(): Application

        fun context(): Context
    }
}