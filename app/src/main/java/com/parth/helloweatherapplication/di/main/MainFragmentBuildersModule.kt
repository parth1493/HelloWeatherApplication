package com.parth.helloweatherapplication.di.main

import com.parth.helloweatherapplication.ui.main.account.AccountFragment
import com.parth.helloweatherapplication.ui.main.account.ChangePasswordFragment
import com.parth.helloweatherapplication.ui.main.account.UpdateAccountFragment
import com.parth.helloweatherapplication.ui.main.blog.BlogFragment
import com.parth.helloweatherapplication.ui.main.blog.UpdateBlogFragment
import com.parth.helloweatherapplication.ui.main.blog.ViewBlogFragment
import com.parth.helloweatherapplication.ui.main.create_blog.CreateBlogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeBlogFragment(): BlogFragment

    @ContributesAndroidInjector()
    abstract fun contributeAccountFragment(): AccountFragment

    @ContributesAndroidInjector()
    abstract fun contributeChangePasswordFragment(): ChangePasswordFragment

    @ContributesAndroidInjector()
    abstract fun contributeCreateBlogFragment(): CreateBlogFragment

    @ContributesAndroidInjector()
    abstract fun contributeUpdateBlogFragment(): UpdateBlogFragment

    @ContributesAndroidInjector()
    abstract fun contributeViewBlogFragment(): ViewBlogFragment

    @ContributesAndroidInjector()
    abstract fun contributeUpdateAccountFragment(): UpdateAccountFragment
}