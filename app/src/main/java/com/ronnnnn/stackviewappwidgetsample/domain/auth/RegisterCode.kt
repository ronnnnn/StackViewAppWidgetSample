package com.ronnnnn.stackviewappwidgetsample.domain.auth

import android.content.Context
import android.support.annotation.CheckResult
import com.ronnnnn.stackviewappwidgetsample.R
import com.ronnnnn.stackviewappwidgetsample.data.model.auth.DribbbleOAuthModel
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by kokushiseiya on 2017/11/21.
 */
class RegisterCode @Inject constructor(
        private val context: Context,
        private val dribbbleOAuthModel: DribbbleOAuthModel
) {

    @CheckResult
    fun execute(code: String): Completable =
            dribbbleOAuthModel.registerCode(
                    context.getString(R.string.dribbble_client_id),
                    context.getString(R.string.dribbble_client_secret),
                    code
            ).flatMapCompletable { response ->
                dribbbleOAuthModel.registerAccessToken(response.accessToken)
            }
}