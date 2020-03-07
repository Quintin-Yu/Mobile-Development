package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_question.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Questions>()
    private val viewAdapter = ViewAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questions.add(Questions("A \'val\' and \'var\' are the same."))
        questions.add(Questions("Mobile Application Development grants 12 ECTS"))
        questions.add(Questions("A Unit in Kotlin corresponds to a void in Java"))
        questions.add(Questions("In Kotlin \'when\' replaces the \'switch\' operator in Java"))

        initViews()
    }

    private fun initViews(){
        tvRecycler.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        tvRecycler.adapter = viewAdapter
        tvRecycler.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
    }
}
