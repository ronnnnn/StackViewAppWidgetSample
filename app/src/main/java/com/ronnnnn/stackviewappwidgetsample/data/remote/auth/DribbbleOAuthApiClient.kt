package com.ronnnnn.stackviewappwidgetsample.data.remote.auth

import android.content.Context
import com.ronnnnn.stackviewappwidgetsample.R
import com.ronnnnn.stackviewappwidgetsample.data.entity.auth.RegisterCodeResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Singleton

/**
 * Created by kokushiseiya on 2017/11/21.
 */
@Singleton
class DribbbleOAuthApiClient(private val context: Context, authRetrofit: Retrofit) : DribbbleOAuthApi {

    private val service: Service = authRetrofit.create(Service::class.java)

    override fun registerCode(
            code: String
    ): Single<RegisterCodeResponse> =
            service.registerCode(
                    context.getString(R.string.dribbble_client_id),
                    context.getString(R.string.dribbble_client_secret),
                    code
            )

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