package com.ronnnnn.stackviewappwidgetsample.ui.auth

import android.os.Build
import android.support.annotation.RequiresApi
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.ronnnnn.stackviewappwidgetsample.R

/**
 * Created by kokushiseiya on 2017/11/21.
 */
class AuthWebViewClient(private val listener: Listener) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
        url ?: kotlin.run {
            listener.onError()
            return false
        }

        return isUrlStartedWithValidUrl(view, url)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest?): Boolean {
        request ?: kotlin.run {
            listener.onError()
            return false
        }

        val url = request.url.toString()

        return isUrlStartedWithValidUrl(view, url)
    }

    private fun isUrlStartedWithValidUrl(view: WebView, url: String): Boolean {
        val callbackUrl = view.context.getString(R.string.dribbble_redirect_url)

        if (url.contains("code") && url.startsWith(callbackUrl)) {
            val splitString = url.split("=")
            if (splitString.size > 1) {
                listener.onCodePassed(splitString[1])

                return true
            }
        }

        return false
    }

    interface Listener {

        fun onCodePassed(code: String)

        fun onError()
    }
}