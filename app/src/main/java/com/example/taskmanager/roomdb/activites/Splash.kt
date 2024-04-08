package com.example.taskmanager.roomdb.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.taskmanager.databinding.ActivitySplashBinding
import com.example.taskmanager.roomdb.Room.UserDB

class Splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        UserDB.getInstance(this)

     Splashloader()
    }

    private fun Splashloader() {
        object : CountDownTimer(2000, 1000) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                Intent(this@Splash, MainActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }

            }
        }.start()
    }

}