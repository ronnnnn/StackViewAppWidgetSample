package com.ronnnnn.stackviewappwidgetsample.model.likes

import com.ronnnnn.stackviewappwidgetsample.data.remote.likes.DribbbleLikesApi
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by kokushiseiya on 2017/12/23.
 */
class DribbbleLikesModel @Inject constructor(private val dribbbleLikesApi: DribbbleLikesApi) {

    fun isLiked(id: String): Single<Boolean> = dribbbleLikesApi.isLiked(id)
            .map { response -> response.id.isNotEmpty() }

    fun likeShot(id: String): Completable = dribbbleLikesApi.likeShot(id)

    fun unlikeShot(id: String): Completable = dribbbleLikesApi.unlikeShot(id)
}