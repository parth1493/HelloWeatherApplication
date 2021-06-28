package com.parth.helloweatherapplication.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.parth.helloweatherapplication.R
import com.parth.helloweatherapplication.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
    }
}