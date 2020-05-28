package com.dmt.juniortask.ui

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dmt.juniortask.AppApplication
import com.dmt.juniortask.R
import com.dmt.juniortask.databinding.ActivityLoginBinding
import com.dmt.juniortask.repository.AppRepository
import com.dmt.juniortask.viewmodels.LoginViewModel
import com.dmt.juniortask.viewmodels.factories.DaggerViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class LoginActivity  : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: DaggerViewModelFactory

    @Inject
    lateinit var repo: AppRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)

        val userManager = (application as AppApplication).userManager
        if (!userManager.isUserLoggedIn()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            title = resources.getString(R.string.login)
            val viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

            // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
            binding.lifecycleOwner = this
            binding.viewModel = viewModel

            binding.loginButton.setOnClickListener {
                viewModel.login()
            }

            viewModel.loginState.observe(this, Observer { state ->
                when(state) {
                    LoginSuccess -> {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    LoginError -> {binding.errorMsgTextView.text = resources.getString(R.string.error)}
                }
            })
        }
    }

}

sealed class LoginViewState
object LoginSuccess : LoginViewState()
object LoginError : LoginViewState()