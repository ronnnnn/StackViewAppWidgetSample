package com.ronnnnn.stackviewappwidgetsample.data.remote.auth

import com.ronnnnn.stackviewappwidgetsample.data.entity.auth.RegisterCodeResponse
import io.reactivex.Single

/**
 * Created by kokushiseiya on 2017/11/21.
 */
interface DribbbleOAuthApi {

    fun registerCode(
            clientId: String,
            clientSecret: String,
            code: String
    ): Single<RegisterCodeResponse>
}