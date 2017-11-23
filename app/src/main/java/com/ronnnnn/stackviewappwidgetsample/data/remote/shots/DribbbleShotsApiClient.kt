package com.ronnnnn.stackviewappwidgetsample.data.remote.shots

import com.ronnnnn.stackviewappwidgetsample.data.entity.Shot
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Singleton

/**
 * Created by kokushiseiya on 2017/11/23.
 */
@Singleton
class DribbbleShotsApiClient(retrofit: Retrofit) : DribbbleShotsApi {

    private val service: Service = retrofit.create(Service::class.java)

    override fun getShots(): Single<List<Shot>> =
            service.getShots()

    interface Service {

        @GET("shots")
        fun getShots(): Single<List<Shot>>
    }
}