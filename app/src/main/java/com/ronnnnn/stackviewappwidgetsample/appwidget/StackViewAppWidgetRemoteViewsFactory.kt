package com.ronnnnn.stackviewappwidgetsample.appwidget

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.ronnnnn.stackviewappwidgetsample.GlideApp
import com.ronnnnn.stackviewappwidgetsample.R
import com.ronnnnn.stackviewappwidgetsample.data.entity.Shot
import com.ronnnnn.stackviewappwidgetsample.domain.shots.GetPopularShots
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

/**
 * Created by kokushiseiya on 2017/12/05.
 */
class StackViewAppWidgetRemoteViewsFactory(
        private val context: Context,
        private val getPopularShots: GetPopularShots
) : RemoteViewsService.RemoteViewsFactory {

    private val remoteViewsList: ArrayList<RemoteViews> = ArrayList()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate() {

    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onDataSetChanged() {
        remoteViewsList.clear()
        fetchShots()
    }

    override fun hasStableIds(): Boolean = true

    override fun getViewAt(position: Int): RemoteViews = remoteViewsList[position]

    override fun getCount(): Int = remoteViewsList.size

    override fun getViewTypeCount(): Int = 1

    override fun onDestroy() {
        compositeDisposable.clear()
    }

    private fun fetchShots() {
        val disposable = getPopularShots.execute()
                .subscribe({ shots ->
                    val remoteViews = shots.map { shot ->
                        RemoteViews(context.packageName, R.layout.item_appwidget_stack_view).apply {
                            setShot(shot)
                        }
                    }
                    remoteViewsList.addAll(remoteViews)
                }, Timber::e)

        compositeDisposable.add(disposable)
    }

    private fun RemoteViews.setShot(shot: Shot) {
        setShotImage(shot.images.normal)
        setShotBasicInfo(shot)
    }

    private fun RemoteViews.setShotImage(url: String) {
        val bitmap = GlideApp.with(context)
                .asBitmap()
                .load(url)
                .submit()
                .get()
        setImageViewBitmap(R.id.shot_image_view, bitmap)
    }

    private fun RemoteViews.setShotBasicInfo(shot: Shot) {
        setTextViewText(R.id.view_text_view, shot.viewsCount.toString())
        setTextViewText(R.id.comment_text_view, shot.commentsCount.toString())
        setTextViewText(R.id.like_text_view, shot.likesCount.toString())
    }
}