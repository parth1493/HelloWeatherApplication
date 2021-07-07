package com.parth.helloweatherapplication.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.parth.helloweatherapplication.R
import com.parth.helloweatherapplication.ui.BaseActivity
import com.parth.helloweatherapplication.ui.main.MainActivity
import com.parth.helloweatherapplication.util.Constants
import com.parth.helloweatherapplication.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class AuthActivity : BaseActivity() {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.viewState.observe(this, Observer{
            Log.d("Auth Activity", "AuthActivity, subscribeObservers: AuthViewState: ${it}")

            it.authToken?.let{
                sessionManager.login(it)
            }
        })

        sessionManager.cachedToken.observe(this, Observer{ dataState ->
            Log.d("Auth Activity", "AuthActivity, subscribeObservers: AuthDataState: ${dataState}")
            dataState.let{ authToken ->
                if(authToken != null && authToken.account_pk != -1 && authToken.token != null){
                    navMainActivity()
                }
            }
        })
    }

    fun navMainActivity(){
        Log.d("Auth Activity", "navMainActivity: called.")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}