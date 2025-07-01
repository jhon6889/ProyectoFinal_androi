package com.example.project16junio.repository

import com.example.project16junio.model.User
import com.example.project16junio.remote.LoginRequest
import com.example.project16junio.remote.UserApi
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi
) {

    suspend fun login(email: String, password: String): User {
        val response = api.login(LoginRequest(email, password))
        if (response.isSuccessful) {
            return response.body()
                ?: throw Exception("No se recibió el usuario desde la API")
        } else {
            throw Exception("Error al iniciar sesión: ${response.code()} ${response.message()}")
        }
    }

    suspend fun register(user: User): User {
        val response = api.register(user)
        if (response.isSuccessful) {
            return response.body()
                ?: throw Exception("No se recibió el usuario desde la API")
        } else {
            throw Exception("Error al registrar: ${response.code()} ${response.message()}")
        }
    }

    suspend fun getUsers(): List<User> {
        val response = api.getUsers()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Error al obtener usuarios: ${response.code()} ${response.message()}")
        }
    }

    suspend fun deleteUser(id: Int) {
        val response = api.deleteUser(id)
        if (!response.isSuccessful) {
            throw Exception("Error al eliminar usuario: ${response.code()} ${response.message()}")
        }
    }
}
