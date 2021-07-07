package com.parth.helloweatherapplication.util

class Constants {

    companion object{

        const val BASE_URL = "https://open-api.xyz/api/"

        var isNetworkConnected = false

        const val NETWORK_TIMEOUT = 3000L
        const val TESTING_NETWORK_DELAY = 0L // fake network delay for testing
        const val TESTING_CACHE_DELAY = 0L // fake cache delay for testing
    }

}