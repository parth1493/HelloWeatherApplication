package com.parth.helloweatherapplication.di.auth

import androidx.lifecycle.ViewModel
import com.parth.helloweatherapplication.di.ViewModelKey
import com.parth.helloweatherapplication.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel

}