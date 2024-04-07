package com.weijun.assuarancetechnologyassessment.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.weijun.assuarancetechnologyassessment.MainActivity
import com.weijun.assuarancetechnologyassessment.R

class WelcomeActivity: AppCompatActivity(), FragmentResultListener {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.welcome_view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.welcome_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {
        if (requestKey == "login" && result.getBoolean("hasLogin")) {
            val sharedPreferences: SharedPreferences = getSharedPreferences("assurance_tech", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean("hasLogin", true)
            editor.apply()

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}