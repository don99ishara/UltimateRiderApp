package com.example.ultimaterider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.content.SharedPreferences
import android.widget.ImageView

class MainActivity : AppCompatActivity(), GameTask {
    private lateinit var rootLayout: LinearLayout
    private lateinit var startBtn: Button
    private lateinit var mGameView: GameView
    private lateinit var score: TextView
    private lateinit var mainmenu: TextView
    private lateinit var logo: ImageView
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = getSharedPreferences("UltimateRiderPrefs", MODE_PRIVATE)
        startBtn = findViewById(R.id.startBtn)
        rootLayout = findViewById(R.id.rootLayout)
        score = findViewById(R.id.score)
        mainmenu = findViewById(R.id.MainMenu)
        logo = findViewById(R.id.m)
        mGameView = GameView(this, this)

        if (intent.getBooleanExtra("restartGame", false)) {
            // Reset and start the game automatically if restartGame flag is true
            mGameView.resetGame()
            mGameView.setBackgroundResource(R.drawable.road)
            rootLayout.addView(mGameView)
            startBtn.visibility = View.GONE
            score.visibility = View.GONE
            mainmenu.visibility = View.GONE
            logo.visibility = View.GONE
        } else {
            loadHighScore()
        }



        startBtn.setOnClickListener {
            mGameView.resetGame() // Reset game state
            mGameView.setBackgroundResource(R.drawable.road)
            rootLayout.addView(mGameView)
            startBtn.visibility = View.GONE
            score.visibility = View.GONE
            mainmenu.visibility = View.GONE
            logo.visibility = View.GONE
        }
    }

    private fun loadHighScore() {
        val highScore = preferences.getInt("highScore", 0)
        score.text = "High Score: $highScore"
    }

    override fun closeGame(mScore: Int) {
        val highScore = preferences.getInt("highScore", 0)
        if (mScore > highScore) {
            preferences.edit().putInt("highScore", mScore).apply()
        }

        val intent = Intent(this, GameOverActivity::class.java)
        intent.putExtra("finalScore", mScore)
        startActivity(intent)
        finish()  // Close the current game activity
    }



}
