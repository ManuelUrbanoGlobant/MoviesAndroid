package com.example.androidHelpers.preferences

import kotlinx.coroutines.flow.Flow

interface StoreHelper {
    suspend fun setBooleanValue(key: String, value: Boolean)
    suspend fun getBooleanValue(key: String): Flow<Boolean>
}