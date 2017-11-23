package com.ronnnnn.stackviewappwidgetsample.domain.shots

import android.support.annotation.CheckResult
import com.ronnnnn.stackviewappwidgetsample.data.entity.Shot
import com.ronnnnn.stackviewappwidgetsample.data.model.shots.DribbbleShotsModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by kokushiseiya on 2017/11/23.
 */
class GetPopularShots @Inject constructor(private val dribbbleShotsModel: DribbbleShotsModel) {

    @CheckResult
    fun execute(): Single<List<Shot>> =
            dribbbleShotsModel.getShots()
}