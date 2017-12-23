package com.ronnnnn.stackviewappwidgetsample.appwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import com.ronnnnn.stackviewappwidgetsample.App
import com.ronnnnn.stackviewappwidgetsample.AppComponent
import com.ronnnnn.stackviewappwidgetsample.R
import com.ronnnnn.stackviewappwidgetsample.di.ReceiverScope
import com.ronnnnn.stackviewappwidgetsample.domain.likes.LikeShot
import com.ronnnnn.stackviewappwidgetsample.domain.likes.UnlikeShot
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by kokushiseiya on 2017/11/25.
 */
class StackViewAppWidgetProvider : AppWidgetProvider() {

    companion object {
        const val KEY_SHOT_ID = "key_shot_id"
        const val KEY_SHOT_URL = "key_shot_url"

        const val ACTION_CLICK_SHOT = "com.ronnnnn.stackviewappwidgetsample.widget.CLICK_SHOT"
        const val ACTION_LIKE = "com.ronnnnn.stackviewappwidgetsample.widget.LIKE"
        const val ACTION_UNLIKE = "com.ronnnnn.stackviewappwidgetsample.widget.UNLIKE"
    }

    @Inject
    lateinit var likeShot: LikeShot
    @Inject
    lateinit var unlikeShot: UnlikeShot

    private var component: Component? = null

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        appWidgetIds.forEach { appWidgetId ->
            val intent = Intent(context, StackViewAppWidgetService::class.java).apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
            }

            RemoteViews(context.packageName, R.layout.appwidget_stack_view).apply {
                setRemoteAdapter(R.id.stack_view, intent)
            }.let {
                val clickIntent = Intent(context, StackViewAppWidgetProvider::class.java).apply {
                    putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                }
                val clickPendingIntent = PendingIntent.getBroadcast(context, 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT)
                it.setPendingIntentTemplate(R.id.stack_view, clickPendingIntent)

                appWidgetManager.updateAppWidget(appWidgetId, it)
            }
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        component ?: kotlin.run {
            DaggerStackViewAppWidgetProvider_Component.builder()
                    .appComponent(App.get(context).component)
                    .build()
        }.inject(this)

        when (intent.action) {
            ACTION_CLICK_SHOT -> {
                val shotUrl = intent.getStringExtra(KEY_SHOT_URL)
                val uri = Uri.parse(shotUrl)
                Intent(Intent.ACTION_VIEW, uri).let {
                    context.startActivity(it)
                }
            }

            ACTION_LIKE -> {
                val shotId = intent.getStringExtra(KEY_SHOT_ID)
                likeShot.execute(shotId)
                        .subscribeOn(Schedulers.io())
                        .subscribe({ updateAppWidget(context) }, Timber::e)
            }

            ACTION_UNLIKE -> {
                val shotId = intent.getStringExtra(KEY_SHOT_ID)
                unlikeShot.execute(shotId)
                        .subscribeOn(Schedulers.io())
                        .subscribe({ updateAppWidget(context) }, Timber::e)
            }
        }
    }

    private fun updateAppWidget(context: Context) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetComponentName = ComponentName(context.applicationContext, StackViewAppWidgetProvider::class.java)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(appWidgetComponentName)
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.stack_view)
    }

    @ReceiverScope
    @dagger.Component(
            dependencies = arrayOf(AppComponent::class)
    )
    interface Component {
        fun inject(stackViewAppWidgetProvider: StackViewAppWidgetProvider)
    }
}