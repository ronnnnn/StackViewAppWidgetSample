package com.ronnnnn.stackviewappwidgetsample

import android.app.Application
import android.content.Context
import com.ronnnnn.stackviewappwidgetsample.data.AppDataModule
import com.ronnnnn.stackviewappwidgetsample.domain.AppDomainModule
import timber.log.Timber

/**
 * Created by kokushiseiya on 2017/11/21.
 */
class App : Application() {

    companion object {
        fun get(context: Context) = context.applicationContext as App
    }

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .appDataModule(AppDataModule())
                .appDomainModule(AppDomainModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        component.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}