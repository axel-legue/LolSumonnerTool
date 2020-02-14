package com.legue.axel.lolsummonertool.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.legue.axel.lolsummonertool.database.model.champion.*
import com.legue.axel.lolsummonertool.repository.ChampionRepository
import kotlin.math.roundToInt

class ChampionViewModel(application: Application) : AndroidViewModel(application) {

    fun start(championKey: Int) {
        key = championKey
    }

    private val championRepository: ChampionRepository = ChampionRepository.getInstance(application)
    private var key: Int = 0

    /**
     *  Champion
     */
    private val name: LiveData<String?> = Transformations.map(championRepository.getChampionByKey(key)) { champion ->
        champion.name
    }
    private val nickName: LiveData<String?> = Transformations.map(championRepository.getChampionByKey(key)) { champion ->
        champion.title
    }
    private val role: LiveData<String?> = Transformations.map(championRepository.getChampionByKey(key)) { champion ->
        champion.tags?.get(0) ?: ""
    }
    private val description: LiveData<String?> = Transformations.map(championRepository.getChampionByKey(key)) { champion ->
        champion.lore
    }


    /**
     *  Champion Stats
     */
    private val range: LiveData<String?> = Transformations.map(championRepository.getChampionStat(key)) { championStat ->
        championStat.attackRange?.roundToInt()?.toString()
    }
    private val mpRegen: LiveData<String?> = Transformations.map(championRepository.getChampionStat(key)) { championStat ->
        championStat.mpRegen?.roundToInt()?.toString()
    }
    private val mp: LiveData<String?> = Transformations.map(championRepository.getChampionStat(key)) { championStat ->
        championStat.mp?.roundToInt()?.toString()
    }
    private val hp: LiveData<String?> = Transformations.map(championRepository.getChampionStat(key)) { championStat ->
        championStat.hp?.roundToInt()?.toString()
    }
    private val hpRegen: LiveData<String?> = Transformations.map(championRepository.getChampionStat(key)) { championStat ->
        championStat.hpRegen?.roundToInt()?.toString()
    }
    private val attackSpeed: LiveData<String?> = Transformations.map(championRepository.getChampionStat(key)) { championStat ->
        championStat.attackSpeed?.roundToInt()?.toString()
    }
    private val armor: LiveData<String?> = Transformations.map(championRepository.getChampionStat(key)) { championStat ->
        championStat.armor?.roundToInt()?.toString()
    }
    private val magicResist: LiveData<String?> = Transformations.map(championRepository.getChampionStat(key)) { championStat ->
        championStat.spellBlock?.roundToInt()?.toString()
    }
    private val crit: LiveData<String?> = Transformations.map(championRepository.getChampionStat(key)) { championStat ->
        championStat.crit?.roundToInt()?.toString()
    }
    private val attackDamage: LiveData<String?> = Transformations.map(championRepository.getChampionStat(key)) { championStat ->
        championStat.attackDamage?.roundToInt()?.toString()
    }
    private val movementSpeed: LiveData<String?> = Transformations.map(championRepository.getChampionStat(key)) { championStat ->
        championStat.moveSpeed?.roundToInt()?.toString()
    }

    fun getChampions(): LiveData<List<Champion>> = championRepository.getChampions()
    fun getChampionByKey(): LiveData<Champion> = championRepository.getChampionByKey(key)
    fun getChampionImage(): LiveData<ChampionImage> = championRepository.getChampionImage(key)
    fun getChampionInfo(): LiveData<ChampionInfo> = championRepository.getChampionInfo(key)
    fun getChampionPassive(): LiveData<Passive> = championRepository.getChampionPassive(key)

    fun getChampionSpells(): LiveData<List<Spell>> = championRepository.getChampionSpells(key)


    fun getRange(): LiveData<String?> = range
    fun getMpRegen(): LiveData<String?> = mpRegen
    fun getMp(): LiveData<String?> = mp
    fun getHp(): LiveData<String?> = hp
    fun getHpRegen(): LiveData<String?> = hpRegen
    fun getAttackSpeed(): LiveData<String?> = attackSpeed
    fun getArmor(): LiveData<String?> = armor
    fun getMagicResist(): LiveData<String?> = magicResist
    fun getCrit(): LiveData<String?> = crit
    fun getAttackDamage(): LiveData<String?> = attackDamage
    fun getMovementSpeed(): LiveData<String?> = movementSpeed

    fun getName(): LiveData<String?> = name
    fun getNickName(): LiveData<String?> = nickName
    fun getRole(): LiveData<String?> = role
    fun getDescription(): LiveData<String?> = description

}
