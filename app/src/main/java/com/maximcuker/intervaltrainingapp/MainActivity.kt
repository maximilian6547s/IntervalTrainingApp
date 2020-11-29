package com.maximcuker.intervaltrainingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llStart.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        llBMI.setOnClickListener {
            val intentBMI = Intent(this@MainActivity, BMIActivity::class.java)
            startActivity(intentBMI)
        }

        llHistory.setOnClickListener {
            val intentHistory = Intent(this@MainActivity, HistoryActivity::class.java)
            startActivity(intentHistory)
        }
    }
}