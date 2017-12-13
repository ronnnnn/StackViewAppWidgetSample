package com.ronnnnn.stackviewappwidgetsample.appwidget

import android.content.Intent
import android.widget.RemoteViewsService
import com.ronnnnn.stackviewappwidgetsample.App
import com.ronnnnn.stackviewappwidgetsample.AppComponent
import com.ronnnnn.stackviewappwidgetsample.di.ServiceScope
import com.ronnnnn.stackviewappwidgetsample.domain.shots.GetPopularShots
import javax.inject.Inject

/**
 * Created by kokushiseiya on 2017/12/05.
 */
class StackViewAppWidgetService : RemoteViewsService() {

    private val component: Component by lazy {
        DaggerStackViewAppWidgetService_Component.builder()
                .appComponent(App.get(applicationContext).component)
                .build()
    }

    @Inject
    lateinit var getPopularShots: GetPopularShots

    override fun onCreate() {
        super.onCreate()

        component.inject(this)
    }

    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory =
            StackViewAppWidgetRemoteViewsFactory(applicationContext, getPopularShots)

    @ServiceScope
    @dagger.Component(dependencies = arrayOf(AppComponent::class))
    interface Component {
        fun inject(stackViewAppWidgetService: StackViewAppWidgetService)
    }
}