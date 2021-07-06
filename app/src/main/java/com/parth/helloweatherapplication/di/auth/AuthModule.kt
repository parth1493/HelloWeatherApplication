package com.parth.helloweatherapplication.di.auth

import com.parth.helloweatherapplication.api.auth.OpenApiAuthService
import com.parth.helloweatherapplication.persistence.AccountPropertiesDao
import com.parth.helloweatherapplication.persistence.AuthTokenDao
import com.parth.helloweatherapplication.repository.auth.AuthRepository
import com.parth.helloweatherapplication.session.SessionManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule{

    @AuthScope
    @Provides
    fun provideOpenApiAuthService(retrofitBuilder: Retrofit.Builder): OpenApiAuthService {
        return retrofitBuilder
            .build()
            .create(OpenApiAuthService::class.java)
    }

    @AuthScope
    @Provides
    fun provideAuthRepository(
        sessionManager: SessionManager,
        authTokenDao: AuthTokenDao,
        accountPropertiesDao: AccountPropertiesDao,
        openApiAuthService: OpenApiAuthService
    ): AuthRepository {
        return AuthRepository(
            authTokenDao,
            accountPropertiesDao,
            openApiAuthService,
            sessionManager
        )
    }

}