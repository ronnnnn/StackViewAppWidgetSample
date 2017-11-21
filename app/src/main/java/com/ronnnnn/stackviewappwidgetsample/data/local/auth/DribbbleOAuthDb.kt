package com.ronnnnn.stackviewappwidgetsample.data.local.auth

import io.reactivex.Completable

/**
 * Created by kokushiseiya on 2017/11/21.
 */
interface DribbbleOAuthDb {

    fun registerAccessToken(accessToken: String): Completable
}