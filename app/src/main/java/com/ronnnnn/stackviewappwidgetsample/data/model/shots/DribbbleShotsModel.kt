package com.ronnnnn.stackviewappwidgetsample.data.model.shots

import android.support.annotation.CheckResult
import com.ronnnnn.stackviewappwidgetsample.data.entity.Shot
import com.ronnnnn.stackviewappwidgetsample.data.remote.shots.DribbbleShotsApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

/**
 * Created by kokushiseiya on 2017/11/23.
 */
@Singleton
class DribbbleShotsModel(private val api: DribbbleShotsApi) {

    @CheckResult
    fun getShots(): Single<List<Shot>> =
            api.getShots()
                    .subscribeOn(Schedulers.io())
}