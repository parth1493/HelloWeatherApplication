package com.parth.helloweatherapplication.session

import android.app.Application
import com.parth.helloweatherapplication.persistence.AuthTokenDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager
@Inject
constructor(
    val authTokenDao: AuthTokenDao,
    val application: Application
)
{
}