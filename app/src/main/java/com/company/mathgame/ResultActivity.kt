package com.company.mathgame

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.company.mathgame.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var resultBinding: ActivityResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(resultBinding.root)

        val score = intent.getIntExtra("score", 0)
        resultBinding.textViewResult.text = "Your score: $score"

        onPlayAgainButtonPressed()
        onExitButtonPressed()
    }

    private fun onPlayAgainButtonPressed() {
        resultBinding.buttonAgain.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun onExitButtonPressed() {
        resultBinding.buttonExit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}