package com.example.studentportal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_portal.*

class AddPortalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_portal)

        initViews()
    }

    private fun initViews(){
        btnAdd.setOnClickListener{ onAddClick() }
    }

    private fun onAddClick(){
        val addPortalIntent = Intent(this, AddPortalActivity::class.java)
    }
}
