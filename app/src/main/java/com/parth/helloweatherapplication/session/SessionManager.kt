package com.parth.helloweatherapplication.session

import android.app.Application
import com.parth.helloweatherapplication.persistence.AuthTokenDao
import javax.inject.Inject

class SessionManager
@Inject
constructor(
    val authTokenDao: AuthTokenDao,
    val application: Application
)
{
}