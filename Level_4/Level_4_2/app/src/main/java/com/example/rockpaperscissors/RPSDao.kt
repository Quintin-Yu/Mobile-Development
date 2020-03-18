package com.example.rockpaperscissors

import androidx.room.*

@Dao
interface RPSDao {

    @Query("SELECT * FROM match_table")
    suspend fun getAllMatches(): List<RPS>

    @Insert
    suspend fun insertMatch(rps: RPS)

    @Query("DELETE FROM match_table")
    suspend fun deleteAllMatches()

    @Query("SELECT COUNT() FROM match_table WHERE result = 'win'")
    suspend fun getWins(): Int

    @Query("SELECT COUNT() FROM match_table WHERE result = 'draw'")
    suspend fun getDraws(): Int

    @Query("SELECT COUNT() FROM match_table WHERE result = 'loss'")
    suspend fun getLosses(): Int
}
