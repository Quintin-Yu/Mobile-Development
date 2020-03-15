package com.example.studentportal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_portal.*

const val EXTRA_PORTAL = "EXTRA_PORTAL"

class AddPortalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_portal)

        initViews()
    }

    private fun initViews() {
        btnAdd.setOnClickListener { onAddClick() }
    }

    private fun onAddClick() {
        if (etUrl.text?.isBlank()!! || etTitle.text?.isBlank()!!) {
            createErrorMessage("Please fill in all fields")
        } else {
            var portal = Portal(etTitle.text.toString(), etUrl.text.toString())
            var resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PORTAL, portal)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun createErrorMessage(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
}
