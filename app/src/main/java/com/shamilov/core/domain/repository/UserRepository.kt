package com.shamilov.core.domain.repository

interface UserRepository {
    val isAuthorize: Boolean
    val getToken: String?
    fun saveToken(token: String)
    fun removeToken(token: String)
    fun getUUID(): String
}