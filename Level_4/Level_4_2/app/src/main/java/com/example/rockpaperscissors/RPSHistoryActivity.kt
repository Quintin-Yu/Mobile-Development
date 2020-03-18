package com.example.rockpaperscissors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_match_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RPSHistoryActivity : AppCompatActivity() {

    private val matchList = arrayListOf<RPS>()
    private val matchAdapter = RPSAdapter(matchList)
    private lateinit var rpsRepository: RPSRepository
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history)

        rpsRepository = RPSRepository(this)
        initViews()
    }

    private fun initViews(){
        rvHistory.adapter = matchAdapter
        rvHistory.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvHistory.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        getAllMatches()

        btnReturn.setOnClickListener{ onReturnClick() }
        btnDelete.setOnClickListener{ clearHistory() }
    }


    private fun getAllMatches(){
        scope.launch {
            val list = withContext(Dispatchers.IO){
                rpsRepository.getAllMatches()
            }
            this@RPSHistoryActivity.matchList.clear()
            this@RPSHistoryActivity.matchList.addAll(list)
            this@RPSHistoryActivity.runOnUiThread(java.lang.Runnable { matchAdapter.notifyDataSetChanged() })
        }
    }

    private fun onReturnClick(){
        val returnIntent = Intent(this, RPSActivity::class.java)
        startActivity(returnIntent)
    }

    private fun clearHistory() {
        scope.launch {
            withContext(Dispatchers.IO) {
                rpsRepository.deleteAllMatches()
            }
            getAllMatches()
        }
    }
}
