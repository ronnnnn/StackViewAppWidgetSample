package com.ronnnnn.stackviewappwidgetsample.data.entity

import com.squareup.moshi.Json

/**
 * Created by kokushiseiya on 2017/11/23.
 */
data class Shot(
        val images: Images,
        @Json(name = "views_count")
        val viewsCount: Int,
        @Json(name = "likes_count")
        val likesCount: Int,
        @Json(name = "comments_count")
        val commentsCount: Int
)