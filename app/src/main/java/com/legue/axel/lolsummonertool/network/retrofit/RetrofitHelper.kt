package com.legue.axel.lolsummonertool.network.retrofit

import android.app.Activity
import android.os.Handler
import android.os.Message
import android.util.Log
import com.legue.axel.lolsummonertool.AppExecutors
import com.legue.axel.lolsummonertool.SuperApplication
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.database.model.summoner.Summoner
import com.legue.axel.lolsummonertool.network.response.champion.ChampionInfoResponse
import com.legue.axel.lolsummonertool.network.response.champion.ChampionsResponse
import com.legue.axel.lolsummonertool.network.response.item.ItemsResponse
import com.legue.axel.lolsummonertool.network.response.mastery.MasteryResponse
import com.legue.axel.lolsummonertool.network.response.match.MatchDto
import com.legue.axel.lolsummonertool.network.response.match.MatchReferenceDto
import com.legue.axel.lolsummonertool.network.response.summonerspell.SummonerSpellsResponse
import com.legue.axel.lolsummonertool.utils.*
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

object RetrofitHelper {
    private val TAG = RetrofitHelper::class.java.simpleName

    fun getChampions(action: Int, handlerMessage: Handler, application: SuperApplication) {

        application.retrofitManager!!.champions
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ChampionsResponse> {

                    override fun onSubscribe(d: Disposable) {
                        Log.i(TAG, "onSubscribe :$d")
                    }

                    override fun onNext(championsResponse: ChampionsResponse) {
                        Log.i(TAG, "onNext: $championsResponse")
                        ChampionUtils.insertChampionResponseInDB(
                                championsResponse,
                                SummonerToolDatabase.getInstance(application))

                    }

                    override fun onError(e: Throwable) {
                        if (e is HttpException) {
                            val code = e.code()
                            Log.i(TAG, "Server respond with code : $code")
                            Log.i(TAG, "Response : " + e.message)
                        } else {
                            Log.i(TAG, if (e.message == null) "unknown error" else e.message!!)
                            e.printStackTrace()
                        }
                        // Send message for send image
                        val msg = Message()
                        msg.what = RetrofitConstants.ACTION_ERROR
                        msg.obj = RetrofitConstants.ERROR
                        handlerMessage.sendMessage(msg)
                    }

                    override fun onComplete() {
                        Log.i(TAG, "onComplete")
                        val message = Message()
                        message.what = action
                        handlerMessage.sendMessage(message)
                    }
                })
    }

    fun getChampionByName(action: Int, championId: String, championKey: Int, handlerMessage: Handler, application: SuperApplication) {

        application.retrofitManager!!.getChampionByName(championId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ChampionInfoResponse> {

                    override fun onSubscribe(d: Disposable) {
                        Log.i(TAG, "onSubscribe :$d")
                    }

                    override fun onNext(championInfoResponse: ChampionInfoResponse) {
                        Log.i(TAG, "onNext: $championInfoResponse")
                        ChampionInfoUtils.updateChampionResponseInDB(
                                championInfoResponse,
                                SummonerToolDatabase.getInstance(application))

                    }

                    override fun onError(e: Throwable) {
                        if (e is HttpException) {
                            val code = e.code()
                            Log.i(TAG, "Server respond with code : $code")
                            Log.i(TAG, "Response : " + e.message)
                        } else {
                            Log.i(TAG, if (e.message == null) "unknown error" else e.message!!)
                            e.printStackTrace()
                        }
                        // Send message for send image
                        val msg = Message()
                        msg.what = RetrofitConstants.ACTION_ERROR
                        msg.obj = RetrofitConstants.ERROR
                        handlerMessage.sendMessage(msg)
                    }

                    override fun onComplete() {
                        Log.i(TAG, "onComplete")
                        val message = Message()
                        message.what = action
                        message.arg1 = championKey
                        handlerMessage.sendMessage(message)
                    }
                })
    }

    fun getItems(action: Int, handlerMessage: Handler, application: SuperApplication) {

        application.retrofitManager!!.items
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ItemsResponse> {

                    override fun onSubscribe(d: Disposable) {
                        Log.i(TAG, "onSubscribe :$d")
                    }

                    override fun onNext(itemsResponse: ItemsResponse) {

                        Log.i(TAG, "onNext: $itemsResponse")
                        ItemUtils.insertItemResponseInDB(
                                itemsResponse,
                                SummonerToolDatabase.getInstance(application))
                    }

                    override fun onError(e: Throwable) {
                        if (e is HttpException) {
                            val code = e.code()
                            Log.i(TAG, "Server respond with code : $code")
                            Log.i(TAG, "Response : " + e.message)
                        } else {
                            Log.i(TAG, if (e.message == null) "unknown error" else e.message!!)
                            e.printStackTrace()
                        }
                        // Send message for send image
                        val msg = Message()
                        msg.what = RetrofitConstants.ACTION_ERROR
                        msg.obj = RetrofitConstants.ERROR
                        handlerMessage.sendMessage(msg)
                    }

                    override fun onComplete() {
                        Log.i(TAG, "onComplete")
                        val message = Message()
                        message.what = action
                        handlerMessage.sendMessage(message)
                    }
                })
    }

    fun getMasteries(action: Int, handlerMessage: Handler, application: SuperApplication) {

        application.retrofitManager!!.masteries
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<MasteryResponse> {

                    override fun onSubscribe(d: Disposable) {
                        Log.i(TAG, "onSubscribe :$d")
                    }

                    override fun onNext(masteryResponse: MasteryResponse) {
                        Log.i(TAG, "onNext: $masteryResponse")
                        MasteryUtils.insertMasteryResponseInDB(
                                masteryResponse,
                                SummonerToolDatabase.getInstance(application))
                    }

                    override fun onError(e: Throwable) {
                        if (e is HttpException) {
                            val code = e.code()
                            Log.i(TAG, "Server respond with code : $code")
                            Log.i(TAG, "Response : " + e.message)
                        } else {
                            Log.i(TAG, if (e.message == null) "unknown error" else e.message!!)
                            e.printStackTrace()
                        }
                        // Send message for send image
                        val msg = Message()
                        msg.what = RetrofitConstants.ACTION_ERROR
                        msg.obj = RetrofitConstants.ERROR
                        handlerMessage.sendMessage(msg)
                    }

                    override fun onComplete() {
                        Log.i(TAG, "onComplete")
                        val message = Message()
                        message.what = action
                        handlerMessage.sendMessage(message)
                    }
                })
    }

    fun getSummonerSpells(action: Int, handlerMessage: Handler, application: SuperApplication) {

        application.retrofitManager!!.summonerSpells
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<SummonerSpellsResponse> {

                    override fun onSubscribe(d: Disposable) {
                        Log.i(TAG, "onSubscribe :$d")
                    }

                    override fun onNext(summonerSpellsResponse: SummonerSpellsResponse) {

                        Log.i(TAG, "onNext: $summonerSpellsResponse")

                        SummonerSpellUtils.insertSummonerSpellResponseInDB(
                                summonerSpellsResponse,
                                SummonerToolDatabase.getInstance(application))

                    }

                    override fun onError(e: Throwable) {
                        if (e is HttpException) {
                            val code = e.code()
                            Log.i(TAG, "Server respond with code : $code")
                            Log.i(TAG, "Response : " + e.message)
                        } else {
                            Log.i(TAG, if (e.message == null) "unknown error" else e.message!!)
                            e.printStackTrace()
                        }
                        // Send message for send image
                        val msg = Message()
                        msg.what = RetrofitConstants.ACTION_ERROR
                        msg.obj = RetrofitConstants.ERROR
                        handlerMessage.sendMessage(msg)
                    }

                    override fun onComplete() {
                        Log.i(TAG, "onComplete")
                        val message = Message()
                        message.what = action
                        handlerMessage.sendMessage(message)
                    }
                })
    }

    fun getSummonerName(action: Int, activity: Activity, summonerName: String, handlerMessage: Handler, application: SuperApplication) {
        application.retrofitManager!!.getSummonerProfil(activity, summonerName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Summoner> {

                    override fun onSubscribe(d: Disposable) {
                        Log.i(TAG, "onSubscribe :$d")
                    }

                    override fun onNext(summoner: Summoner) {
                        Log.i(TAG, "onNext: $summoner")
                        AppExecutors.instance.diskIO.execute {
                            try {
                                val database = SummonerToolDatabase.getInstance(application)
                                database.summonerDao().deleteAll()
                                database.summonerDao().insertSummoner(summoner)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        if (e is HttpException) {
                            val code = e.code()
                            Log.i(TAG, "Server respond with code : $code")
                            Log.i(TAG, "Response : " + e.message)
                        } else {
                            Log.i(TAG, if (e.message == null) "unknown error" else e.message!!)
                            e.printStackTrace()
                        }
                        // Send message for send image
                        val msg = Message()
                        msg.what = RetrofitConstants.ACTION_ERROR
                        msg.obj = RetrofitConstants.ERROR
                        handlerMessage.sendMessage(msg)
                    }

                    override fun onComplete() {
                        Log.i(TAG, "onComplete")
                        val message = Message()
                        message.what = action
                        handlerMessage.sendMessage(message)
                    }
                })
    }

    fun getSummonerMatches(action: Int, activity: Activity, accountId: String, endIndex: Int, beginIndex: Int, handlerMessage: Handler, application: SuperApplication) {
        application.retrofitManager!!.getSummonerMatches(activity, accountId, endIndex, beginIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable<MatchReferenceDto> { matchlistDto -> matchlistDto.matches }
                .flatMap { matchReferenceDto ->
                    application.retrofitManager!!.getMatchInformations(activity, matchReferenceDto)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                }
                .subscribe(object : Observer<MatchDto> {
                    override fun onSubscribe(d: Disposable) {
                        Log.i(TAG, "onSubscribe :$d")
                    }

                    override fun onNext(matchDto: MatchDto) {
                        Log.i(TAG, "onNext: $matchDto")
                    }

                    override fun onError(e: Throwable) {
                        if (e is HttpException) {
                            val code = e.code()
                            Log.i(TAG, "Server respond with code : $code")
                            Log.i(TAG, "Response : " + e.message)
                        } else {
                            Log.i(TAG, if (e.message == null) "unknown error" else e.message!!)
                            e.printStackTrace()
                        }
                        // Send message for send image
                        val msg = Message()
                        msg.what = RetrofitConstants.ACTION_ERROR
                        msg.obj = RetrofitConstants.ERROR
                        handlerMessage.sendMessage(msg)
                    }

                    override fun onComplete() {
                        Log.i(TAG, "onComplete")
                        val message = Message()
                        message.what = action
                        handlerMessage.sendMessage(message)
                    }
                })
    }

}
