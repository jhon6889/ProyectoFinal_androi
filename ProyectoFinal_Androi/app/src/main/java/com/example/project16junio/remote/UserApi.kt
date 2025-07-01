package com.example.project16junio.remote

import com.example.project16junio.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApi {

    @POST("user/login")
    suspend fun login(@Body credentials: LoginRequest): Response<User>

    @POST("user/register")
    suspend fun register(@Body user: User): Response<User>

    @GET("user")
    suspend fun getUsers(): Response<List<User>>

    @PUT("user/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: User): Response<User>

    @DELETE("user/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
}
