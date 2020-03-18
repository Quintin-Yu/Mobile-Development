package com.example.rockpaperscissors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.content_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

const val HISTORY_REQUEST_CODE = 100

class RPSActivity : AppCompatActivity() {
    private lateinit var rpsRepository: RPSRepository
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        rpsRepository = RPSRepository(this)
        initViews()
    }

    private fun initViews() {
        btnHistory.setOnClickListener { openMatchHistory() }

        btnPlayRock.setOnClickListener { playedInput(RPS.Choice.ROCK) }
        btnPlayPaper.setOnClickListener { playedInput(RPS.Choice.PAPER) }
        btnPlayScissor.setOnClickListener { playedInput(RPS.Choice.SCISSORS) }

        updateStats()
    }

    private fun openMatchHistory() {
        var historyIntent = Intent(this, RPSHistoryActivity::class.java)
        startActivity(historyIntent)
    }

    private fun playedInput(playerChoice: RPS.Choice) {
        val computerChoice = RPS.Choice.values()[(RPS.Choice.values().indices).random()]
        val match = createMatch(playerChoice, computerChoice)
        showResult(match)
        instertMatch(match)
        updateStats()
    }

    private fun getResult(playerChoice: RPS.Choice, computerChoice: RPS.Choice): RPS.Result {
        return if (playerChoice == computerChoice) {
            RPS.Result.DRAW
        } else if (playerChoice == RPS.Choice.ROCK && computerChoice == RPS.Choice.SCISSORS ||
            playerChoice == RPS.Choice.PAPER && computerChoice == RPS.Choice.ROCK ||
            playerChoice == RPS.Choice.SCISSORS && computerChoice == RPS.Choice.PAPER
        ) {
            RPS.Result.WIN
        } else {
            RPS.Result.LOSS
        }

    }

    private fun showResult(match: RPS) {
        ivComputer.setImageDrawable(getDrawable(match.computerChoice))
        ivPlayer.setImageDrawable(getDrawable(match.playerChoice))

        when (match.result) {
            RPS.Result.WIN ->
                tvResults.text = getString(R.string.playerWins)
            RPS.Result.DRAW ->
                tvResults.text = getString(R.string.draw)
            RPS.Result.LOSS ->
                tvResults.text = getString(R.string.computerWin)
        }
    }

    private fun instertMatch(match: RPS) {
        scope.launch {
            withContext(Dispatchers.IO) {
                rpsRepository.insertMatch(match)
            }
            updateStats()
        }
    }

    private fun createMatch(playerChoice: RPS.Choice, computerChoice: RPS.Choice): RPS {
        return RPS(
            getDrawable(playerChoice),
            getDrawable(computerChoice),
            getResult(playerChoice, computerChoice),
            Date()
        )
    }

    private fun getDrawable(choice: RPS.Choice): Int {
        return when (choice) {
            RPS.Choice.ROCK ->
                R.drawable.rock
            RPS.Choice.PAPER ->
                R.drawable.paper
            RPS.Choice.SCISSORS ->
                R.drawable.scissors
        }
    }

    private fun updateStats() {
        scope.launch {
            var win = 0
            var draw = 0
            var lose = 0

            withContext(Dispatchers.IO) {
                win = rpsRepository.getAllWins()
                draw = rpsRepository.getAllDraws()
                lose = rpsRepository.getAllLoses()
            }
            tvWLDRatio.text = getString(R.string.win_loss_draw_ratio, win, draw, lose)
        }
    }
}
