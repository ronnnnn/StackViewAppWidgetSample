package com.ronnnnn.stackviewappwidgetsample.appwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import com.ronnnnn.stackviewappwidgetsample.R

/**
 * Created by kokushiseiya on 2017/11/25.
 */
class StackViewAppWidgetProvider : AppWidgetProvider() {

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
                appWidgetManager.updateAppWidget(appWidgetId, it)
            }
        }
    }
}