package com.weijun.assuarancetechnologyassessment.view

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.weijun.assuarancetechnologyassessment.R
import com.weijun.assuarancetechnologyassessment.databinding.LoginViewBinding
import com.weijun.assuarancetechnologyassessment.utilities.SnackBarManager

class LoginFragment: Fragment() {
    private val email: MutableLiveData<String> = MutableLiveData()
    private val password: MutableLiveData<String> = MutableLiveData()

    private lateinit var loginViewBinding: LoginViewBinding

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewBinding = LoginViewBinding.bind(view)
        loginViewBinding.loginViewBackButton.setOnClickListener {
            navController.navigateUp()
        }

        val emailEditText = loginViewBinding.emailEditText
        val passwordEditText = loginViewBinding.passwordEditText

        emailEditText.addTextChangedListener { text ->
            email.value = text.toString()
        }

        passwordEditText.addTextChangedListener { text ->
            password.value = text.toString()
        }

        setupLoginButton()
    }

    private fun setupLoginButton() {
        loginViewBinding.loginViewLoginButton.setOnClickListener {
            val emailValue = email.value
            val passwordValue = password.value

            if (emailValue.isNullOrEmpty() || passwordValue.isNullOrEmpty()) {
                return@setOnClickListener
            }

            // Perform login
            if (emailValue == "VVVBB" && passwordValue == "@bcd1234") {
                (requireActivity() as? FragmentResultListener)?.onFragmentResult("login", Bundle().apply {
                    putBoolean("hasLogin", true)
                })
                return@setOnClickListener
            }

            SnackBarManager.showErrorSnackBar(loginViewBinding.root, "Invalid email or password")
        }
    }

}