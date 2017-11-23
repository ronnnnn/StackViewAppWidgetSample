package com.ronnnnn.stackviewappwidgetsample.data.model.auth

import android.support.annotation.CheckResult
import com.ronnnnn.stackviewappwidgetsample.data.entity.auth.RegisterCodeResponse
import com.ronnnnn.stackviewappwidgetsample.data.local.auth.DribbbleOAuthDb
import com.ronnnnn.stackviewappwidgetsample.data.remote.auth.DribbbleOAuthApi
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by kokushiseiya on 2017/11/21.
 */
@Singleton
class DribbbleOAuthModel @Inject constructor(
        private val api: DribbbleOAuthApi,
        private val db: DribbbleOAuthDb
) {

    @CheckResult
    fun registerCode(code: String): Single<RegisterCodeResponse> =
            api.registerCode(code)
                    .subscribeOn(Schedulers.io())

    @CheckResult
    fun registerAccessToken(accessToken: String): Completable =
            db.registerAccessToken(accessToken)
                    .subscribeOn(Schedulers.io())

    @CheckResult
    fun getAccessToken(): Single<String?> =
            db.getAccessToken()
                    .subscribeOn(Schedulers.io())
}