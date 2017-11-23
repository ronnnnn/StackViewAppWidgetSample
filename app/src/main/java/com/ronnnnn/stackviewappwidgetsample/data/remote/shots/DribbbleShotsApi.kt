package com.ronnnnn.stackviewappwidgetsample.data.remote.shots

import com.ronnnnn.stackviewappwidgetsample.data.entity.Shot
import io.reactivex.Single

/**
 * Created by kokushiseiya on 2017/11/23.
 */
interface DribbbleShotsApi {

    fun getShots(): Single<List<Shot>>
}