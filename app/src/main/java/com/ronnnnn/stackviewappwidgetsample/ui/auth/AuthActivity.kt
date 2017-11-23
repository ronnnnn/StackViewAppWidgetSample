package com.ronnnnn.stackviewappwidgetsample.ui.auth

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.ronnnnn.stackviewappwidgetsample.App
import com.ronnnnn.stackviewappwidgetsample.R
import com.ronnnnn.stackviewappwidgetsample.databinding.ActivityAuthBinding
import com.ronnnnn.stackviewappwidgetsample.domain.auth.RegisterCode
import javax.inject.Inject

class AuthActivity : AppCompatActivity(), AuthWebViewClient.Listener, AuthViewModel.Listener {

    @Inject
    lateinit var registerCode: RegisterCode

    private val component: Component by lazy {
        DaggerComponent.builder()
                .appComponent(App.get(this).component)
                .build()
    }
    private val binding: ActivityAuthBinding by lazy {
        DataBindingUtil.setContentView<ActivityAuthBinding>(this, R.layout.activity_auth)
    }
    private val viewModel: AuthViewModel by lazy {
        AuthViewModel(this, registerCode)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component.inject(this)

        binding.authWebView.run {
            isVerticalScrollBarEnabled = false
            isHorizontalScrollBarEnabled = false
            settings.javaScriptEnabled = true
            webViewClient = AuthWebViewClient(this@AuthActivity)
            loadUrl("${context.getString(R.string.dribbble_auth_end_point)}authorize" +
                    "?client_id=${context.getString(R.string.dribbble_client_id)}" +
                    "&scope=public+write")
        }
    }

    override fun onCodePassed(code: String) {
        viewModel.onCodePassed(code)
    }

    override fun onError() {
        showAuthErrorToast(getString(R.string.auth_problem_message))
    }

    override fun onRegisterSucceeded() {
        showAuthSucceededToast()
    }

    override fun onRegisterFailed() {
        showAuthErrorToast(getString(R.string.auth_error_message))
    }

    private fun showAuthSucceededToast() {
        showToast(getString(R.string.auth_success_message))
    }

    private fun showAuthErrorToast(message: String) {
        showToast(message)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
