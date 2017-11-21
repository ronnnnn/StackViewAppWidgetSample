package com.ronnnnn.stackviewappwidgetsample.ui.auth

import com.ronnnnn.stackviewappwidgetsample.AppComponent
import com.ronnnnn.stackviewappwidgetsample.di.ActivityScope

/**
 * Created by kokushiseiya on 2017/11/22.
 */
@ActivityScope
@dagger.Component(dependencies = arrayOf(AppComponent::class))
interface Component {

    fun inject(authActivity: AuthActivity)
}