package com.ronnnnn.stackviewappwidgetsample.data.remote.likes

import android.support.annotation.CheckResult
import com.ronnnnn.stackviewappwidgetsample.data.entity.likes.CheckLikeResponse
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by kokushiseiya on 2017/12/23.
 */
interface DribbbleLikesApi {

    @CheckResult
    fun isLiked(id: String): Single<CheckLikeResponse>

    @CheckResult
    fun likeShot(id: String): Completable

    @CheckResult
    fun unlikeShot(id: String): Completable
}