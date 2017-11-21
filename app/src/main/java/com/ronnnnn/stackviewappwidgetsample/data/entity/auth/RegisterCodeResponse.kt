package com.ronnnnn.stackviewappwidgetsample.data.entity.auth

import com.squareup.moshi.Json

/**
 * Created by kokushiseiya on 2017/11/21.
 */
data class RegisterCodeResponse(
        @Json(name = "access_token")
        val accessToken: String,
        @Json(name = "token_type")
        val tokenType: String,
        val scope: String
)