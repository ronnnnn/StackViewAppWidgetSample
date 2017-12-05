package com.ronnnnn.stackviewappwidgetsample.appwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.ronnnnn.stackviewappwidgetsample.R

/**
 * Created by kokushiseiya on 2017/11/25.
 */
class StackViewAppWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        appWidgetIds.forEach { appWidgetId ->
            RemoteViews(context.packageName, R.layout.appwidget_stack_view).let {
                appWidgetManager.updateAppWidget(appWidgetId, it)
            }
        }
    }
}