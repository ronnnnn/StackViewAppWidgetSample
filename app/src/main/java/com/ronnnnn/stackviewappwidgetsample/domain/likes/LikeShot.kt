package com.ronnnnn.stackviewappwidgetsample.domain.likes

import android.support.annotation.CheckResult
import com.ronnnnn.stackviewappwidgetsample.model.likes.DribbbleLikesModel
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by kokushiseiya on 2017/12/23.
 */
class LikeShot @Inject constructor(private val dribbbleLikesModel: DribbbleLikesModel) {

    @CheckResult
    fun execute(id: String): Completable = dribbbleLikesModel.likeShot(id)
}