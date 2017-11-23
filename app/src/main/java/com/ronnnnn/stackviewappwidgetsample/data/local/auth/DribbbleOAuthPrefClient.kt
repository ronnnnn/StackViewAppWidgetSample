package com.ronnnnn.stackviewappwidgetsample.data.local.auth

import android.content.SharedPreferences
import io.reactivex.Completable
import javax.inject.Singleton

/**
 * Created by kokushiseiya on 2017/11/21.
 */
@Singleton
class DribbbleOAuthPrefClient(private val sharedPreferences: SharedPreferences) : DribbbleOAuthDb {

    companion object {
        private const val KEY_ACCESS_TOKEN = "key_access_token"
    }

    override fun registerAccessToken(accessToken: String): Completable =
            Completable.fromAction {
                sharedPreferences.edit()
                        .putString(KEY_ACCESS_TOKEN, accessToken)
                        .apply()
            }
}