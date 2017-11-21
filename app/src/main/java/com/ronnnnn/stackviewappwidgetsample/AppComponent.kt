package com.ronnnnn.stackviewappwidgetsample

import com.ronnnnn.stackviewappwidgetsample.data.AppDataModule
import com.ronnnnn.stackviewappwidgetsample.domain.AppDomainModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by kokushiseiya on 2017/11/21.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        AppDataModule::class,
        AppDomainModule::class
))
interface AppComponent :
        AppModule.Provider,
        AppDataModule.Provider,
        AppDomainModule.Provider {

    fun inject(app: App)
}