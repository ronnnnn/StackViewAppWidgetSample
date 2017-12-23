package com.ronnnnn.stackviewappwidgetsample.data.remote.likes

import android.support.annotation.CheckResult
import com.ronnnnn.stackviewappwidgetsample.data.entity.likes.CheckLikeResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Inject

/**
 * Created by kokushiseiya on 2017/12/23.
 */
class DribbbleLikesApiClient @Inject constructor(retrofit: Retrofit) : DribbbleLikesApi {

    private val service: Service = retrofit.create(Service::class.java)

    @CheckResult
    override fun isLiked(id: String): Single<CheckLikeResponse> =
            service.isLiked(id)
                    .onErrorResumeNext { Single.just(CheckLikeResponse()) }

    @CheckResult
    override fun likeShot(id: String): Completable = service.likeShot(id)

    @CheckResult
    override fun unlikeShot(id: String): Completable = service.unlikeShot(id)

    interface Service {

        @GET("shots/{id}/like")
        fun isLiked(@Path("id") id: String): Single<CheckLikeResponse>

        @POST("shots/{id}/like")
        fun likeShot(@Path("id") id: String): Completable

        @DELETE("shots/{id}/like")
        fun unlikeShot(@Path("id") id: String): Completable
    }
}