package com.legue.axel.lolsummonertool.network.retrofit;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ErrorInterceptor implements Interceptor {
    private final String TAG = ErrorInterceptor.class.getName();

    @Override
    public Response intercept(@EverythingIsNonNull Chain chain) throws IOException {

        Request request = chain.request();
        // Access server response
        Response response = chain.proceed(request);
        //TODO Open specific Activity / Fragment for error 400 and 500
        //TODO : move to xml resource
        switch (response.code()) {
            case 400:
                Log.i(TAG, "Bad request : 400");
                return response;
            case 403:
                Log.i(TAG, "Forbidden : 403");
                return response;
            case 404:
                Log.i(TAG, "Not found : 404");
                return response;
            case 415:
                Log.i(TAG, "unsupported Media Type : 415");
                return response;
            case 429:
                Log.i(TAG, "Rate Limit exceed : 429");
                return response;
            case 500:
                Log.i(TAG, "Internal server error : 500");
                return response;
            case 503:
                Log.i(TAG, "Service unavailable : 503");
                return response;
        }
        return response;
    }

}
