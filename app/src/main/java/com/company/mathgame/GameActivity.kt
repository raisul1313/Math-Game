package com.company.mathgame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.company.mathgame.databinding.ActivityGameBinding
import java.util.Locale
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var gameBinding: ActivityGameBinding

    private var correctAnswer = 0
    private var userScore = 0
    private var userLife = 3

    private lateinit var timer: CountDownTimer
    private val startTimerInMillis: Long = 60000L
    var timeLeftInMillis: Long = startTimerInMillis

    private lateinit var actionTitle: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameBinding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(gameBinding.root)

        actionTitle = intent.getStringExtra("actionTitle").toString()

        when (actionTitle) {
            "Addition" -> supportActionBar!!.title = "Addition"
            "Subtraction" -> supportActionBar!!.title = "Subtraction"
            "Multiplication" -> supportActionBar!!.title = "Multiplication"
        }

        gameContinue()
        onOkButtonPressed()
        onNextButtonPressed()
    }

    private fun onOkButtonPressed() {
        gameBinding.buttonOk.setOnClickListener {

            val input = gameBinding.editTextAnswer.text.toString()

            if (input == "") {
                Toast.makeText(
                    applicationContext,
                    "Please write an answer or click the next button",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                gameBinding.buttonOk.isClickable = false
                pauseTimer()

                val userAnswer = input.toInt()

                if (userAnswer == correctAnswer) {
                    userScore += 10
                    gameBinding.textViewQuestion.text = "Congratulations, your answer is correct"
                    gameBinding.textViewScore.text = userScore.toString()
                } else {
                    userLife--
                    gameBinding.textViewQuestion.text = "Sorry, you answer in wrong"
                    gameBinding.textViewLife.text = userLife.toString()
                }
            }

        }
    }

    private fun onNextButtonPressed() {
        gameBinding.buttonNext.setOnClickListener {

            gameBinding.editTextAnswer.setText("")
            gameBinding.buttonOk.isClickable = true
            pauseTimer()
            resetTimer()

            if (userLife == 0) {
                Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity, ResultActivity::class.java)
                intent.putExtra("score", gameBinding.textViewScore.text.toString().toInt())
                startActivity(intent)
                finish()
            } else {
                gameContinue()
            }
        }
    }

    private fun gameContinue() {
        val number1 = Random.nextInt(0, 100)
        val number2 = Random.nextInt(0, 100)

        if (actionTitle == "Addition") {

            gameBinding.textViewQuestion.text = "$number1 + $number2"
            correctAnswer = number1 + number2

        } else if (actionTitle == "Subtraction") {

            if (number1 >= number2) {
                gameBinding.textViewQuestion.text = "$number1 - $number2"
                correctAnswer = number1 - number2

            } else {
                gameBinding.textViewQuestion.text = "$number2 - $number1"
                correctAnswer = number2 - number1

            }
        } else {
            val number3 = Random.nextInt(0, 20)
            val number4 = Random.nextInt(0, 20)

            gameBinding.textViewQuestion.text = "$number3 * $number4"
            correctAnswer = number3 * number4
        }

        startTimer()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()

                userLife--
                gameBinding.textViewLife.text = userLife.toString()
                gameBinding.textViewQuestion.text = "Sorry, Time is up!"
            }

        }.start()
    }

    fun updateText() {
        val remainingTime: Int = (timeLeftInMillis / 1000).toInt()
        gameBinding.textViewTime.text = String.format(Locale.getDefault(), "%02d", remainingTime)
    }

    fun pauseTimer() {
        timer.cancel()
    }

    fun resetTimer() {
        timeLeftInMillis = startTimerInMillis
        updateText()
    }
}