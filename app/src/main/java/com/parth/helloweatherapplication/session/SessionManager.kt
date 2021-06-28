package com.parth.helloweatherapplication.session

import android.app.Application
import com.parth.helloweatherapplication.persistence.AuthTokenDao

class SessionManager
constructor(
    val authTokenDao: AuthTokenDao,
    val application: Application
)
{
}