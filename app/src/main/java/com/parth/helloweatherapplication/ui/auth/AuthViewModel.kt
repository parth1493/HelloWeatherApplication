package com.parth.helloweatherapplication.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.parth.helloweatherapplication.api.auth.network_responses.LoginResponse
import com.parth.helloweatherapplication.api.auth.network_responses.RegistrationResponse
import com.parth.helloweatherapplication.repository.auth.AuthRepository
import com.parth.helloweatherapplication.util.GenericApiResponse
import javax.inject.Inject

class AuthViewModel
@Inject
constructor(
    val authRepository: AuthRepository
): ViewModel(){

    fun testLogin(): LiveData<GenericApiResponse<LoginResponse>> {
        return authRepository.testLoginRequest(
            "parth1493@gmail.com",
            "Shlok1493"
        )
    }

    fun testRegister(): LiveData<GenericApiResponse<RegistrationResponse>> {
        return authRepository.testRegistrationRequest(
            "mitchelltabian1234@gmail.com",
            "mitchelltabian1234",
            "codingwithmitch1",
            "codingwithmitch1"
        )
    }

}