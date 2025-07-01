// AuthViewModel.kt
package com.example.project16junio.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project16junio.model.User
import com.example.project16junio.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val _successMessage = MutableStateFlow<String?>(null)
    val successMessage = _successMessage.asStateFlow()

    private var isSubmitting = false

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        if (isSubmitting) return
        isSubmitting = true
        viewModelScope.launch {
            _error.value = null
            _successMessage.value = null
            try {
                val user = repository.login(email, password)
                Log.d("AuthViewModel", "Login exitoso: $user")
                _successMessage.value = "Inicio de sesión exitoso"
                onSuccess()
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Error en login: ${e.message}")
                _error.value = e.message
            } finally {
                isSubmitting = false
            }
        }
    }

    fun register(user: User, onSuccess: () -> Unit) {
        if (isSubmitting) return
        isSubmitting = true
        viewModelScope.launch {
            _error.value = null
            _successMessage.value = null
            try {
                val registeredUser = repository.register(user)
                Log.d("AuthViewModel", "Registro exitoso: $registeredUser")
                _successMessage.value = "Registro exitoso"
                onSuccess()
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Error en registro: ${e.message}")
                _error.value = e.message
            } finally {
                isSubmitting = false
            }
        }
    }

    fun clearMessages() {
        _error.value = null
        _successMessage.value = null
    }

    fun setError(message: String) {
        _error.value = message
    }
}
