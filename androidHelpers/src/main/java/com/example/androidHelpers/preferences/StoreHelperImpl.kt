package com.example.androidHelpers.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreHelperImpl(private val context: Context) : StoreHelper {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "movies")

    override suspend fun setBooleanValue(key: String, value: Boolean) {
        context.dataStore.edit { settings ->
            settings[booleanPreferencesKey(key)] = value
        }
    }

    override suspend fun getBooleanValue(key: String): Flow<Boolean> =
        context.dataStore.data
            .map { preferences ->
                preferences[booleanPreferencesKey(key)] ?: false
            }
}