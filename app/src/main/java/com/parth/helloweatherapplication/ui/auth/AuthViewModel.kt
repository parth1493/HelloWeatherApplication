package com.parth.helloweatherapplication.ui.auth

import androidx.lifecycle.ViewModel
import com.parth.helloweatherapplication.repository.auth.AuthRepository
import javax.inject.Inject

class AuthViewModel
@Inject
constructor(
    val authRepository: AuthRepository
): ViewModel(){

}