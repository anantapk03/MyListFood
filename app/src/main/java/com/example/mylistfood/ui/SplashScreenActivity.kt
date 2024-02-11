package com.example.mylistfood.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mylistfood.R
import com.example.mylistfood.databinding.ActivitySplashScreenBinding
import com.example.mylistfood.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    //Initiation binding
    private lateinit var binding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //Animation splash screen
        binding.ivSplashScreen.alpha = 0f
        binding.ivSplashScreen.animate().setDuration(1500).alpha(1f).withEndAction{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}