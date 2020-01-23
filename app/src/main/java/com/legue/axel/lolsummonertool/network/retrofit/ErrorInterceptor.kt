package com.legue.axel.lolsummonertool.network.retrofit

import android.util.Log

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.internal.EverythingIsNonNull

class ErrorInterceptor : Interceptor {
    private val TAG = ErrorInterceptor::class.java.name

    @Throws(IOException::class)
    override fun intercept(@EverythingIsNonNull chain: Interceptor.Chain): Response {

        val request = chain.request()
        // Access server response
        val response = chain.proceed(request)
        //TODO Open specific Activity / Fragment for error 400 and 500
        //TODO : move to xml resource
        when (response.code()) {
            400 -> {
                Log.i(TAG, "Bad request : 400")
                return response
            }
            403 -> {
                Log.i(TAG, "Forbidden : 403")
                return response
            }
            404 -> {
                Log.i(TAG, "Not found : 404")
                return response
            }
            415 -> {
                Log.i(TAG, "unsupported Media Type : 415")
                return response
            }
            429 -> {
                Log.i(TAG, "Rate Limit exceed : 429")
                return response
            }
            500 -> {
                Log.i(TAG, "Internal server error : 500")
                return response
            }
            503 -> {
                Log.i(TAG, "Service unavailable : 503")
                return response
            }
        }
        return response
    }

}
