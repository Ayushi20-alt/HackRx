package com.example.leadiify.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.leadiify.R
import kotlinx.coroutines.Runnable

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed(object : Runnable {
            override fun run() {
                var intent = Intent(this@SplashScreen,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        },3000 )
    }
}