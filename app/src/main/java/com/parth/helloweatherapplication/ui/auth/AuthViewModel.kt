package com.parth.helloweatherapplication.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.parth.helloweatherapplication.api.auth.network_responses.LoginResponse
import com.parth.helloweatherapplication.api.auth.network_responses.RegistrationResponse
import com.parth.helloweatherapplication.model.AuthToken
import com.parth.helloweatherapplication.repository.auth.AuthRepository
import com.parth.helloweatherapplication.ui.BaseViewModel
import com.parth.helloweatherapplication.ui.DataState
import com.parth.helloweatherapplication.ui.auth.state.AuthStateEvent
import com.parth.helloweatherapplication.ui.auth.state.AuthViewState
import com.parth.helloweatherapplication.ui.auth.state.LoginFields
import com.parth.helloweatherapplication.ui.auth.state.RegistrationFields
import com.parth.helloweatherapplication.util.AbsentLiveData
import com.parth.helloweatherapplication.util.GenericApiResponse
import javax.inject.Inject

class AuthViewModel
@Inject
constructor(
    val authRepository: AuthRepository
): BaseViewModel<AuthStateEvent, AuthViewState>()
{
    override fun handleStateEvent(stateEvent: AuthStateEvent): LiveData<DataState<AuthViewState>> {
        when(stateEvent){

            is AuthStateEvent.LoginAttemptEvent -> {
                return authRepository.attemptLogin(
                    stateEvent.email,
                    stateEvent.password
                )
            }

            is AuthStateEvent.RegisterAttemptEvent -> {
                return authRepository.attemptRegistration(
                    stateEvent.email,
                    stateEvent.username,
                    stateEvent.password,
                    stateEvent.confirm_password
                )
            }

            is AuthStateEvent.CheckPreviousAuthEvent -> {
                return AbsentLiveData.create()
            }


        }
    }

    override fun initNewViewState(): AuthViewState {
        return AuthViewState()
    }

    fun setRegistrationFields(registrationFields: RegistrationFields){
        val update = getCurrentViewStateOrNew()
        if(update.registrationFields == registrationFields){
            return
        }
        update.registrationFields = registrationFields
        _viewState.value = update
    }

    fun setLoginFields(loginFields: LoginFields){
        val update = getCurrentViewStateOrNew()
        if(update.loginFields == loginFields){
            return
        }
        update.loginFields = loginFields
        _viewState.value = update
    }

    fun setAuthToken(authToken: AuthToken){
        val update = getCurrentViewStateOrNew()
        if(update.authToken == authToken){
            return
        }
        update.authToken = authToken
        _viewState.value = update
    }
}