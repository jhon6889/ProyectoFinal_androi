package com.example.project16junio.viewmodel

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
class UserViewModel @Inject constructor(
    private val repo: UserRepository
) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users = _users.asStateFlow()

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            try {
                _users.value = repo.getUsers()
            } catch (e: Exception) {

            }
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            try {
                repo.deleteUser(id)
                loadUsers()
            } catch (e: Exception) {
                // Manejar error
            }
        }
    }
}