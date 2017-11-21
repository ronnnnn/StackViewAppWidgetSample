package com.ronnnnn.stackviewappwidgetsample.ui.auth

import com.ronnnnn.stackviewappwidgetsample.domain.auth.RegisterCode
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by kokushiseiya on 2017/11/21.
 */
class AuthViewModel(private val listener: Listener, private val registerCode: RegisterCode) {

    fun onCodePassed(code: String) {
        registerCode(code)
    }

    private fun registerCode(code: String) {
        registerCode.execute(code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener.onRegisterSucceeded()
                }, {
                    listener.onRegisterFailed()
                })
    }

    interface Listener {

        fun onRegisterSucceeded()

        fun onRegisterFailed()
    }
}