package com.parth.helloweatherapplication.repository.auth

import androidx.lifecycle.LiveData
import com.parth.helloweatherapplication.api.auth.OpenApiAuthService
import com.parth.helloweatherapplication.api.auth.network_responses.LoginResponse
import com.parth.helloweatherapplication.api.auth.network_responses.RegistrationResponse
import com.parth.helloweatherapplication.persistence.AccountPropertiesDao
import com.parth.helloweatherapplication.persistence.AuthTokenDao
import com.parth.helloweatherapplication.session.SessionManager
import com.parth.helloweatherapplication.util.GenericApiResponse
import javax.inject.Inject

class AuthRepository
@Inject
constructor(
    val authTokenDao: AuthTokenDao,
    val accountPropertiesDao: AccountPropertiesDao,
    val openApiAuthService: OpenApiAuthService,
    val sessionManager: SessionManager
){

    fun testLoginRequest(email: String, password: String): LiveData<GenericApiResponse<LoginResponse>> {
        return openApiAuthService.login(email, password)
    }

    fun testRegistrationRequest(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): LiveData<GenericApiResponse<RegistrationResponse>> {
        return openApiAuthService.register(email, username, password, confirmPassword)
    }

}