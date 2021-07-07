package com.parth.helloweatherapplication.ui.main

import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.parth.helloweatherapplication.R
import com.parth.helloweatherapplication.databinding.ActivityMainBinding
import com.parth.helloweatherapplication.ui.BaseActivity
import com.parth.helloweatherapplication.ui.auth.AuthActivity

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolBar.setOnClickListener {
            sessionManager.logout()
            navAuthActivity()
        }

        subscribeObservers()
    }

    fun subscribeObservers(){
        sessionManager.cachedToken.observe(this, Observer{ authToken ->
            Log.d("Main Activity", "MainActivity, subscribeObservers: ViewState: ${authToken}")
            if(authToken == null || authToken.account_pk == -1 || authToken.token == null){
                navAuthActivity()
                finish()
            }
        })
    }

    private fun navAuthActivity(){
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}