package com.legue.axel.lolsummonertool.retrofit;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.legue.axel.lolsummonertool.SuperApplication;
import com.legue.axel.lolsummonertool.network.ChampionsResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class RetrofitHelper {
    private static final String TAG = RetrofitHelper.class.getSimpleName();

    public static void getChampions(final int action, final Handler handlerMessage, final SuperApplication application) {

        application.getRetrofitManager().getChampions()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChampionsResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe :" + d.toString());
                    }

                    @Override
                    public void onNext(ChampionsResponse championsResponse) {
                        if (championsResponse != null) {
                            Log.i(TAG, "onNext: " + championsResponse);
                            application.setChampionsResponse(championsResponse);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            int code = httpException.code();
                            Log.i(TAG, "Server respond with code : " + code);
                            Log.i(TAG, "Response : " + httpException.getMessage());
                        } else {
                            Log.i(TAG, e.getMessage() == null ? "unknown error" : e.getMessage());
                            e.printStackTrace();
                        }
                        // Send message for send image
                        Message msg = new Message();
                        msg.what = Constants.ACTION_ERROR;
                        msg.obj = Constants.ERROR;
                        handlerMessage.sendMessage(msg);
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete");
                        Message message = new Message();
                        message.what = action;
                        handlerMessage.sendMessage(message);
                    }
                });
    }

}
