package com.ronnnnn.stackviewappwidgetsample.data.remote.auth

import com.ronnnnn.stackviewappwidgetsample.data.entity.auth.RegisterCodeResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Singleton

/**
 * Created by kokushiseiya on 2017/11/21.
 */
@Singleton
class DribbbleOAuthApiClient(authRetrofit: Retrofit) : DribbbleOAuthApi {

    private val service: Service = authRetrofit.create(Service::class.java)

    override fun registerCode(
            clientId: String,
            clientSecret: String,
            code: String
    ): Single<RegisterCodeResponse> =
            service.registerCode(clientId, clientSecret, code)
                    .subscribeOn(Schedulers.io())

    interface Service {

        @POST("token")
        fun registerCode(
                @Query("client_id")
                clientId: String,
                @Query("client_secret")
                clientSecret: String,
                @Query("code")
                code: String
        ): Single<RegisterCodeResponse>
    }
}