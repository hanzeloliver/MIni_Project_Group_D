package com.example.miniprojectgroupd

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val nim: String,
    val phone: String
)
