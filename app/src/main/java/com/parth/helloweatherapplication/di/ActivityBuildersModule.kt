package com.parth.helloweatherapplication.di

import com.parth.helloweatherapplication.di.auth.AuthFragmentBuildersModule
import com.parth.helloweatherapplication.di.auth.AuthModule
import com.parth.helloweatherapplication.di.auth.AuthScope
import com.parth.helloweatherapplication.di.auth.AuthViewModelModule
import com.parth.helloweatherapplication.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
        modules = [AuthModule::class, AuthFragmentBuildersModule::class, AuthViewModelModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity
}