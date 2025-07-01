package com.example.project16junio.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val id: Int? = null,
    val name: String,
    val email: String,
    val password: String,
    val age: Int? = null,
    val state: Boolean = true
)
