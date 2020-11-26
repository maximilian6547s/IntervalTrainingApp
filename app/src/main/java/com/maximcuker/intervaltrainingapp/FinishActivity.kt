package com.maximcuker.intervaltrainingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        setSupportActionBar(toolbarFinishActivity)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true) //set back button
        }
        toolbarFinishActivity.setNavigationOnClickListener{
            onBackPressed() //press back when press button on toolbar
        }

        btnFinish.setOnClickListener {
            finish()
        }
    }
}