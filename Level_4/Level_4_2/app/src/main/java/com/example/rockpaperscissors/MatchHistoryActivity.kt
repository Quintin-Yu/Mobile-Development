package com.example.rockpaperscissors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_match_history.*

class MatchHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history)

        initViews()
    }

    private fun initViews(){
        btnReturn.setOnClickListener{ onReturnClick() }
    }

    private fun onReturnClick(){
        val returnIntent = Intent(this, MainActivity::class.java)
        startActivity(returnIntent)
    }
}
