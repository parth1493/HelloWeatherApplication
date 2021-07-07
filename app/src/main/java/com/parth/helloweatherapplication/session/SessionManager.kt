package com.parth.helloweatherapplication.session

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.parth.helloweatherapplication.model.AuthToken
import com.parth.helloweatherapplication.persistence.AuthTokenDao
import com.parth.helloweatherapplication.util.Constants
import kotlinx.coroutines.*
import javax.inject.Inject


class SessionManager
@Inject
constructor(
    val authTokenDao: AuthTokenDao,
    val application: Application
) {

    private val TAG: String = "AppDebug"

    private val _cachedToken = MutableLiveData<AuthToken>()

    private var networkCallback: ConnectivityManager.NetworkCallback

    private var request: NetworkRequest

    private var manager: ConnectivityManager

    init {
        networkCallback = NetworkCallbackImpl()
        request = NetworkRequest.Builder().build()
        manager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    val cachedToken: LiveData<AuthToken>
        get() = _cachedToken

    fun login(newValue: AuthToken) {
        setValue(newValue)
    }

    fun onActiveInternet() {
        manager.registerNetworkCallback(request, networkCallback)
    }

    fun onInactive() {
        manager.unregisterNetworkCallback(networkCallback)
    }

    fun logout() {
        Log.d(TAG, "logout: ")


        CoroutineScope(Dispatchers.IO).launch {
            var errorMessage: String? = null
            try {
                _cachedToken.value!!.account_pk?.let {
                    authTokenDao.nullifyToken(it)
                } ?: throw CancellationException("Token Error. Logging out user.")
            } catch (e: CancellationException) {
                Log.e(TAG, "logout: ${e.message}")
                errorMessage = e.message
            } catch (e: Exception) {
                Log.e(TAG, "logout: ${e.message}")
                errorMessage = errorMessage + "\n" + e.message
            } finally {
                errorMessage?.let {
                    Log.e(TAG, "logout: ${errorMessage}")
                }
                Log.d(TAG, "logout: finally")
                setValue(null)
            }
        }
    }

    fun setValue(newValue: AuthToken?) {
        GlobalScope.launch(Dispatchers.Main) {
            if (_cachedToken.value != newValue) {
                _cachedToken.value = newValue!!
            }
        }
    }

    class NetworkCallbackImpl : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d("onAvailable: ", "Network is connected")
            Constants.isNetworkConnected = true
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d("onLost: ", "Network Disconnection")
            Constants.isNetworkConnected = false
        }
    }
}