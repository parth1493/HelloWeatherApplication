package com.parth.helloweatherapplication.di.main

import androidx.lifecycle.ViewModel
import com.parth.helloweatherapplication.di.ViewModelKey
import com.parth.helloweatherapplication.ui.main.account.AccountViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(accoutViewModel: AccountViewModel): ViewModel
}