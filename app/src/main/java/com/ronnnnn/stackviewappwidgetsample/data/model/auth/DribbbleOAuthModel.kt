package com.ronnnnn.stackviewappwidgetsample.data.model.auth

import android.support.annotation.CheckResult
import com.ronnnnn.stackviewappwidgetsample.data.entity.auth.RegisterCodeResponse
import com.ronnnnn.stackviewappwidgetsample.data.local.auth.DribbbleOAuthDb
import com.ronnnnn.stackviewappwidgetsample.data.remote.auth.DribbbleOAuthApi
import io.reactivex.Completable
import io.reactivex.Single
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
    fun registerCode(clientId: String, clientSecret: String, code: String): Single<RegisterCodeResponse> =
            api.registerCode(clientId, clientSecret, code)

    @CheckResult
    fun registerAccessToken(accessToken: String): Completable =
            db.registerAccessToken(accessToken)
}