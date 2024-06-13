package com.example.ultimaterider

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_over_activity)

        val finalScoreTextView = findViewById<TextView>(R.id.finalScore)
        val restartButton = findViewById<Button>(R.id.restartButton)
        val quitButton = findViewById<Button>(R.id.quitButton)

        val finalScore = intent.getIntExtra("finalScore", 0)
        finalScoreTextView.text = "Final Score: $finalScore"

        restartButton.setOnClickListener {
            val restartIntent = Intent(this, MainActivity::class.java)
            restartIntent.putExtra("restartGame", true)  // Passing a flag to indicate restart
            startActivity(restartIntent)
            finish()
        }


        quitButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
