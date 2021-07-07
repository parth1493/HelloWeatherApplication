package com.parth.helloweatherapplication.repository.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import com.parth.helloweatherapplication.api.auth.OpenApiAuthService
import com.parth.helloweatherapplication.model.AuthToken
import com.parth.helloweatherapplication.persistence.AccountPropertiesDao
import com.parth.helloweatherapplication.persistence.AuthTokenDao
import com.parth.helloweatherapplication.session.SessionManager
import com.parth.helloweatherapplication.ui.DataState
import com.parth.helloweatherapplication.ui.Response
import com.parth.helloweatherapplication.ui.ResponseType
import com.parth.helloweatherapplication.ui.auth.state.AuthViewState
import com.parth.helloweatherapplication.util.ApiEmptyResponse
import com.parth.helloweatherapplication.util.ApiErrorResponse
import com.parth.helloweatherapplication.util.ApiSuccessResponse
import com.parth.helloweatherapplication.util.ErrorHandling.Companion.ERROR_UNKNOWN
import javax.inject.Inject

class AuthRepository
@Inject
constructor(
    val authTokenDao: AuthTokenDao,
    val accountPropertiesDao: AccountPropertiesDao,
    val openApiAuthService: OpenApiAuthService,
    val sessionManager: SessionManager
){
    fun attemptLogin(email: String, password: String): LiveData<DataState<AuthViewState>>{
        return openApiAuthService.login(email, password)
            .switchMap { response ->
                object: LiveData<DataState<AuthViewState>>(){
                    override fun onActive() {
                        super.onActive()
                        when(response){
                            is ApiSuccessResponse ->{
                                value = DataState.data(
                                    AuthViewState(
                                        authToken = AuthToken(response.body.pk, response.body.token)
                                    ),
                                    response = null
                                )
                            }
                            is ApiErrorResponse ->{
                                value = DataState.error(
                                    Response(
                                        message = response.errorMessage,
                                        responseType = ResponseType.Dialog()
                                    )
                                )
                            }
                            is ApiEmptyResponse ->{
                                value = DataState.error(
                                    Response(
                                        message = ERROR_UNKNOWN,
                                        responseType = ResponseType.Dialog()
                                    )
                                )
                            }
                        }
                    }
                }
            }
    }

    fun attemptRegistration(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): LiveData<DataState<AuthViewState>>{
        return openApiAuthService.register(email, username, password, confirmPassword)
            .switchMap { response ->
                object: LiveData<DataState<AuthViewState>>(){
                    override fun onActive() {
                        super.onActive()
                        when(response){
                            is ApiSuccessResponse ->{
                                value = DataState.data(
                                    AuthViewState(
                                        authToken = AuthToken(response.body.pk, response.body.token)
                                    ),
                                    response = null
                                )
                            }
                            is ApiErrorResponse ->{
                                value = DataState.error(
                                    Response(
                                        message = response.errorMessage,
                                        responseType = ResponseType.Dialog()
                                    )
                                )
                            }
                            is ApiEmptyResponse ->{
                                value = DataState.error(
                                    Response(
                                        message = ERROR_UNKNOWN,
                                        responseType = ResponseType.Dialog()
                                    )
                                )
                            }
                        }
                    }
                }
            }
    }
}