package com.example.rockpaperscissors

import android.content.Context

class RPSRepository(context: Context) {
    private var rpsDao : RPSDao

    init {
        val rpsRoomDatabase = RPSDatabase.getDatabase(context)
        rpsDao = rpsRoomDatabase!!.rpsDao()
    }

    suspend fun getAllMatches(): List<RPS> {
        return rpsDao.getAllMatches()
    }

    suspend fun insertMatch(rps: RPS) {
        rpsDao.insertMatch(rps)
    }

    suspend fun deleteAllMatches(){
        rpsDao.deleteAllMatches()
    }

    suspend fun getAllWins(): Int{
        return rpsDao.getWins()
    }

    suspend fun getAllDraws(): Int{
        return rpsDao.getDraws()
    }

    suspend fun getAllLoses(): Int{
        return rpsDao.getLosses()
    }

}