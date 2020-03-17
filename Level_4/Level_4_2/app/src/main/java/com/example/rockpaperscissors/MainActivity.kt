package com.example.rockpaperscissors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_game.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        initViews()
    }

    private fun initViews(){
        btnHistory.setOnClickListener{ openMatchHistory() }
    }

    private fun openMatchHistory(){
        var historyIntent = Intent(this, MatchHistoryActivity::class.java)
        startActivity(historyIntent)
    }
}
