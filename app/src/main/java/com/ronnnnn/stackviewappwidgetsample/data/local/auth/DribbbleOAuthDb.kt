package com.ronnnnn.stackviewappwidgetsample.data.local.auth

import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by kokushiseiya on 2017/11/21.
 */
interface DribbbleOAuthDb {

    fun registerAccessToken(accessToken: String): Completable

    fun getAccessToken(): Single<String?>
}