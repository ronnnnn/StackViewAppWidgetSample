package com.ronnnnn.stackviewappwidgetsample.domain.likes

import android.support.annotation.CheckResult
import com.ronnnnn.stackviewappwidgetsample.model.likes.DribbbleLikesModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by kokushiseiya on 2017/12/23.
 */
class CheckLike @Inject constructor(private val dribbbleLikesModel: DribbbleLikesModel) {

    @CheckResult
    fun execute(id: String): Single<Boolean> = dribbbleLikesModel.isLiked(id)
}