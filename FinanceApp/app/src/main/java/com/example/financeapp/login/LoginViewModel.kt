package com.example.financeapp.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val email = MutableLiveData("")
    val password = MutableLiveData("")

    private val _loginResult = MutableLiveData("")
    val loginResult: LiveData<String>
        get() = _loginResult

    fun handleLogin(email: String, password: String) {
        if (!validateLoginFields(email, password)) {
            return
        }
        doLogin()
    }

    private fun validateLoginFields(email: String, password: String): Boolean {
        if (email.isBlank()) {
            _loginResult.value = "Invalid email"
            return false
        }
        if (password.isBlank()) {
            _loginResult.value = "Invalid password"
            return false
        }
        return true
    }

    private fun doLogin() {
        Thread.sleep(500)

        _loginResult.value = "Login successful"
    }
}
