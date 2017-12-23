package com.ronnnnn.stackviewappwidgetsample.appwidget

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.ronnnnn.stackviewappwidgetsample.GlideApp
import com.ronnnnn.stackviewappwidgetsample.R
import com.ronnnnn.stackviewappwidgetsample.data.entity.Shot
import com.ronnnnn.stackviewappwidgetsample.domain.likes.CheckLike
import com.ronnnnn.stackviewappwidgetsample.domain.shots.GetPopularShots
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import timber.log.Timber

/**
 * Created by kokushiseiya on 2017/12/05.
 */
class StackViewAppWidgetRemoteViewsFactory(
        private val context: Context,
        private val getPopularShots: GetPopularShots,
        private val checkLike: CheckLike
) : RemoteViewsService.RemoteViewsFactory {

    private val appWidgetManager: AppWidgetManager by lazy {
        AppWidgetManager.getInstance(context)
    }

    private val remoteViewsList: ArrayList<RemoteViews> = ArrayList()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate() {
        // no-op
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
        remoteViewsList.clear()
        compositeDisposable.clear()
    }

    private fun updateViews() {
        val appWidgetComponentName = ComponentName(context.applicationContext, StackViewAppWidgetProvider::class.java)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(appWidgetComponentName)
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.stack_view)
    }

    private fun fetchShots() {
        val disposable = getPopularShots.execute()
                .flatMapPublisher { shots -> Flowable.fromIterable(shots) }
                .flatMapSingle { shot ->
                    Single.zip<Shot, Boolean, RemoteViews>(Single.just(shot), checkLike.execute(shot.id), BiFunction { shot, isLiked ->
                        RemoteViews(context.packageName, R.layout.item_appwidget_stack_view).apply {
                            setShot(shot, isLiked)
                        }
                    })
                }
                .toList()
                .subscribe({ remoteViews ->
                    remoteViewsList.addAll(remoteViews)
                }, Timber::e)

        compositeDisposable.add(disposable)
    }

    private fun RemoteViews.setShot(shot: Shot, isLiked: Boolean) {
        setShotImage(shot.images.normal)
        setShotBasicInfo(shot)
        setShotLikeState(isLiked)
        setClickEvent(shot, isLiked)
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

    private fun RemoteViews.setShotLikeState(isLiked: Boolean) {
        val imageRes = if (isLiked) R.drawable.ic_like_pink else R.drawable.ic_like_grey
        setImageViewResource(R.id.like_icon_image_view, imageRes)
    }

    private fun RemoteViews.setClickEvent(shot: Shot, isLiked: Boolean) {
        setOnShotClicked(shot.url)
        setOnLikeClicked(shot.id, isLiked)
    }

    private fun RemoteViews.setOnShotClicked(url: String) {
        val bundle = Bundle().apply {
            putString(StackViewAppWidgetProvider.KEY_SHOT_URL, url)
        }
        val intent = Intent().apply {
            action = StackViewAppWidgetProvider.ACTION_CLICK_SHOT
            putExtras(bundle)
        }
        setOnClickFillInIntent(R.id.shot_image_view, intent)
    }

    private fun RemoteViews.setOnLikeClicked(id: String, isLiked: Boolean) {
        val bundle = Bundle().apply {
            putString(StackViewAppWidgetProvider.KEY_SHOT_ID, id)
        }
        val intent = Intent().apply {
            action = if (isLiked) StackViewAppWidgetProvider.ACTION_UNLIKE else StackViewAppWidgetProvider.ACTION_LIKE
            putExtras(bundle)
        }
        setOnClickFillInIntent(R.id.like_icon_image_view, intent)
    }
}