package com.ronnnnn.stackviewappwidgetsample.data.remote.auth

import com.ronnnnn.stackviewappwidgetsample.data.entity.auth.RegisterCodeResponse
import io.reactivex.Single

/**
 * Created by kokushiseiya on 2017/11/21.
 */
interface DribbbleOAuthApi {

    fun registerCode(
            code: String
    ): Single<RegisterCodeResponse>
}