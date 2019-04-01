package com.legue.axel.lolsummonertool.network.retrofit;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.legue.axel.lolsummonertool.SuperApplication;
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.network.response.champion.ChampionInfoResponse;
import com.legue.axel.lolsummonertool.network.response.champion.ChampionsResponse;
import com.legue.axel.lolsummonertool.network.response.item.ItemsResponse;
import com.legue.axel.lolsummonertool.network.response.mastery.MasteryResponse;
import com.legue.axel.lolsummonertool.network.response.summonerspell.SummonerSpellsResponse;
import com.legue.axel.lolsummonertool.utils.ChampionInfoUtils;
import com.legue.axel.lolsummonertool.utils.ChampionUtils;
import com.legue.axel.lolsummonertool.utils.ItemUtils;
import com.legue.axel.lolsummonertool.utils.MasteryUtils;
import com.legue.axel.lolsummonertool.utils.SummonerSpellUtils;

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

    public static void getChampionByName(final int action, final String championId, final Handler handlerMessage, final SuperApplication application) {

        application.getRetrofitManager().getChampionByName(championId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChampionInfoResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe :" + d.toString());
                    }

                    @Override
                    public void onNext(ChampionInfoResponse championInfoResponse) {
                        if (championInfoResponse != null) {
                            Log.i(TAG, "onNext: " + championInfoResponse);

                            ChampionInfoUtils.updateChampionResponseInDB(
                                    championInfoResponse,
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

    public static void getMasteries(final int action, final Handler handlerMessage, final SuperApplication application) {

        application.getRetrofitManager().getMasteries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MasteryResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe :" + d.toString());
                    }

                    @Override
                    public void onNext(MasteryResponse masteryResponse) {
                        if (masteryResponse != null) {
                            Log.i(TAG, "onNext: " + masteryResponse);

                            MasteryUtils.insertMasteryResponseInDB(
                                    masteryResponse,
                                    SummonerToolDatabase.getInstance(application));

                        } else {
                            Log.i(TAG, "onNext: getMasteries response is null");
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

    public static void getSummonerSpells(final int action, final Handler handlerMessage, final SuperApplication application) {

        application.getRetrofitManager().getSummonerSpells()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SummonerSpellsResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe :" + d.toString());
                    }

                    @Override
                    public void onNext(SummonerSpellsResponse summonerSpellsResponse) {
                        if (summonerSpellsResponse != null) {
                            Log.i(TAG, "onNext: " + summonerSpellsResponse);

                            SummonerSpellUtils.insertSummonerSpellResponseInDB(
                                    summonerSpellsResponse,
                                    SummonerToolDatabase.getInstance(application));

                        } else {
                            Log.i(TAG, "onNext: getMasteries response is null");
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
