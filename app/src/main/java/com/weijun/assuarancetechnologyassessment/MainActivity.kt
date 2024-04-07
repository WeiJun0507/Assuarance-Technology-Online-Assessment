package com.weijun.assuarancetechnologyassessment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weijun.assuarancetechnologyassessment.view.HomeActivity
import com.weijun.assuarancetechnologyassessment.view.WelcomeActivity

class MainActivity : AppCompatActivity() {
    companion object {
        fun hasLogin(context: Context): Boolean {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences("assurance_tech", MODE_PRIVATE)
            return sharedPreferences.getBoolean("hasLogin", false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Check if user is logged in
        if (hasLogin(this)) {
            // If logged in, start HomeActivity
            startActivity(Intent(this, HomeActivity::class.java))
        } else {
            // If not logged in, start LoginActivity or perform other actions
            val loginIntent = Intent(this, WelcomeActivity::class.java)
            startActivity(loginIntent)
        }
        finish()
    }
}