package com.shamilov.core.domain.repository

interface AuthRepository {
    fun isAuthorize(): Boolean
    fun logout()
}