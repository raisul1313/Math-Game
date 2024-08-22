package com.company.mathgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.internal.SafeIterableMap.IteratorWithAdditions
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.company.mathgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.buttonAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("actionTitle", "Addition")
            startActivity(intent)
        }

        mainBinding.buttonSub.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("actionTitle", "Subtraction")
            startActivity(intent)

        }

        mainBinding.buttonMulti.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("actionTitle", "Multiplication")
            startActivity(intent)

        }
    }
}