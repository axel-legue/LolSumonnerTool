package com.legue.axel.lolsummonertool.network.retrofit;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.legue.axel.lolsummonertool.AppExecutors;
import com.legue.axel.lolsummonertool.SuperApplication;
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.database.model.summoner.Summoner;
import com.legue.axel.lolsummonertool.network.response.champion.ChampionInfoResponse;
import com.legue.axel.lolsummonertool.network.response.champion.ChampionsResponse;
import com.legue.axel.lolsummonertool.network.response.item.ItemsResponse;
import com.legue.axel.lolsummonertool.network.response.mastery.MasteryResponse;
import com.legue.axel.lolsummonertool.network.response.match.MatchDto;
import com.legue.axel.lolsummonertool.network.response.match.MatchReferenceDto;
import com.legue.axel.lolsummonertool.network.response.match.MatchlistDto;
import com.legue.axel.lolsummonertool.network.response.summonerspell.SummonerSpellsResponse;
import com.legue.axel.lolsummonertool.utils.ChampionInfoUtils;
import com.legue.axel.lolsummonertool.utils.ChampionUtils;
import com.legue.axel.lolsummonertool.utils.ItemUtils;
import com.legue.axel.lolsummonertool.utils.MasteryUtils;
import com.legue.axel.lolsummonertool.utils.SummonerSpellUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class RetrofitHelper {
    private static final String TAG = RetrofitHelper.class.getSimpleName();

    public static void getChampions(final int action, final Handler handlerMessage, final SuperApplication application) {

        application.getRetrofitManager().getChampions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChampionsResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe :" + d.toString());
                    }

                    @Override
                    public void onNext(ChampionsResponse championsResponse) {
                        Log.i(TAG, "onNext: " + championsResponse);
                        ChampionUtils.insertChampionResponseInDB(
                                championsResponse,
                                SummonerToolDatabase.getInstance(application));

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

    public static void getChampionByName(final int action, final String championId, final int championKey, final Handler handlerMessage, final SuperApplication application) {

        application.getRetrofitManager().getChampionByName(championId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChampionInfoResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe :" + d.toString());
                    }

                    @Override
                    public void onNext(ChampionInfoResponse championInfoResponse) {
                        Log.i(TAG, "onNext: " + championInfoResponse);
                        ChampionInfoUtils.updateChampionResponseInDB(
                                championInfoResponse,
                                SummonerToolDatabase.getInstance(application));

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
                        message.arg1 = championKey;
                        handlerMessage.sendMessage(message);
                    }
                });
    }

    public static void getItems(final int action, final Handler handlerMessage, final SuperApplication application) {

        application.getRetrofitManager().getItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ItemsResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe :" + d.toString());
                    }

                    @Override
                    public void onNext(ItemsResponse itemsResponse) {

                        Log.i(TAG, "onNext: " + itemsResponse);
                        ItemUtils.insertItemResponseInDB(
                                itemsResponse,
                                SummonerToolDatabase.getInstance(application));
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MasteryResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe :" + d.toString());
                    }

                    @Override
                    public void onNext(MasteryResponse masteryResponse) {
                        Log.i(TAG, "onNext: " + masteryResponse);
                        MasteryUtils.insertMasteryResponseInDB(
                                masteryResponse,
                                SummonerToolDatabase.getInstance(application));
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SummonerSpellsResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe :" + d.toString());
                    }

                    @Override
                    public void onNext(SummonerSpellsResponse summonerSpellsResponse) {

                        Log.i(TAG, "onNext: " + summonerSpellsResponse);

                        SummonerSpellUtils.insertSummonerSpellResponseInDB(
                                summonerSpellsResponse,
                                SummonerToolDatabase.getInstance(application));

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

    public static void getSummonerName(final int action, final Activity activity, final String summonerName, final Handler handlerMessage, final SuperApplication application) {
        application.getRetrofitManager().getSummonerProfil(activity, summonerName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Summoner>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe :" + d.toString());
                    }

                    @Override
                    public void onNext(Summoner summoner) {
                        Log.i(TAG, "onNext: " + summoner);
                        AppExecutors.getInstance().getDiskIO().execute(() -> {
                            try {
                                SummonerToolDatabase database = SummonerToolDatabase.getInstance(application);
                                database.summonerDao().deleteAll();
                                database.summonerDao().insertSummoner(summoner);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
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

    public static void getSummonerMatches(final int action, final Activity activity, final String accountId, final int endIndex, final int beginIndex, final Handler handlerMessage, final SuperApplication application) {
        application.getRetrofitManager().getSummonerMatches(activity, accountId, endIndex, beginIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable(matchlistDto -> matchlistDto.matches)
                .flatMap(matchReferenceDto -> application.getRetrofitManager().getMatchInformations(activity, matchReferenceDto)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()))
                .subscribe(new Observer<MatchDto>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe :" + d.toString());
                    }

                    @Override
                    public void onNext(MatchDto matchDto) {
                        Log.i(TAG, "onNext: " + matchDto);
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
