package com.weijun.assuarancetechnologyassessment.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.weijun.assuarancetechnologyassessment.R
import com.weijun.assuarancetechnologyassessment.databinding.WelcomeFragmentBinding

class WelcomeFragment: Fragment() {
    private lateinit var welcomeFragmentBinding: WelcomeFragmentBinding

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.welcome_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        welcomeFragmentBinding = WelcomeFragmentBinding.bind(view)

        welcomeFragmentBinding.loginButton.setOnClickListener {
            navController.navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
    }
}