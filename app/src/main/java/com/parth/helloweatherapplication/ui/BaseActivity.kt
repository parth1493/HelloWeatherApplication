package com.parth.helloweatherapplication.ui

import com.parth.helloweatherapplication.session.SessionManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    private val TAG: String = "AppDebug"

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onStart() {
        super.onStart()
        sessionManager.onActiveInternet()
    }

    override fun onDestroy() {
        super.onDestroy()
        sessionManager.onInactive()
    }
}