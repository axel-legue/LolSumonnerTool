package com.legue.axel.lolsummonertool.retrofit;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.legue.axel.lolsummonertool.SuperApplication;
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.network.ChampionsResponse;
import com.legue.axel.lolsummonertool.network.ItemsResponse;
import com.legue.axel.lolsummonertool.utils.ChampionUtils;
import com.legue.axel.lolsummonertool.utils.ItemUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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

                            ChampionUtils.insertChampionResponseInDB(
                                    championsResponse,
                                    SummonerToolDatabase.getInstance(application));

                        } else {
                            Log.i(TAG, "onNext: getChampions response is null");
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
                        msg.what = RetrofitConstants.ACTION_ERROR;
                        msg.obj = RetrofitConstants.ERROR;
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


    public static void getItems(final int action, final Handler handlerMessage, final SuperApplication application) {

        application.getRetrofitManager().getItems()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ItemsResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe :" + d.toString());
                    }

                    @Override
                    public void onNext(ItemsResponse itemsResponse) {
                        if (itemsResponse != null) {
                            Log.i(TAG, "onNext: " + itemsResponse);

                            ItemUtils.insertItemResponseInDB(
                                    itemsResponse,
                                    SummonerToolDatabase.getInstance(application));

                        } else {
                            Log.i(TAG, "onNext: getItems response is null");
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
                        msg.what = RetrofitConstants.ACTION_ERROR;
                        msg.obj = RetrofitConstants.ERROR;
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
