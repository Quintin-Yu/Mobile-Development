package com.example.rockpaperscissors

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "match_table")
data class Match(

    @ColumnInfo
    @DrawableRes val playerChoice: Int,

    @ColumnInfo
    @DrawableRes val computerChoice: Int,

    @ColumnInfo
    val result: Result?,

    @ColumnInfo
    val date: Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Long? = null
) {
    enum class Result(val value: String) {
        WIN("win"),
        DRAW("draw"),
        LOSS("loss")
    }
    enum class Choice {
        ROCK,
        PAPER,
        SCISSORS
    }
}