package com.parth.helloweatherapplication.di.auth

import com.parth.helloweatherapplication.ui.auth.ForgotPasswordFragment
import com.parth.helloweatherapplication.ui.auth.LauncherFragment
import com.parth.helloweatherapplication.ui.auth.LoginFragment
import com.parth.helloweatherapplication.ui.auth.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeLauncherFragment(): LauncherFragment

    @ContributesAndroidInjector()
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector()
    abstract fun contributeRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector()
    abstract fun contributeForgotPasswordFragment(): ForgotPasswordFragment

}