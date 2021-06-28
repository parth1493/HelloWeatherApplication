package com.parth.helloweatherapplication.ui.auth

import androidx.lifecycle.ViewModel
import com.parth.helloweatherapplication.repository.auth.AuthRepository

class AuthViewModelConstructor(
    val authRepository: AuthRepository
): ViewModel(){

}