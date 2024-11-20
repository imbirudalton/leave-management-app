package com.dalton.myleavemanager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

// Extension property for creating a DataStore instance
private val Context.dataStore by preferencesDataStore("db_prefs")

class DBPreferences(private val context: Context) {

    companion object {
        private val DB_INITIALIZED_KEY = booleanPreferencesKey("db_initialized")
        private val SELECTED_RECORD_KEY = longPreferencesKey("selected_record")
    }

    /**
     * Checks if the database has been initialized.
     */
    suspend fun isDatabaseInitialized(): Boolean {
        return context.dataStore.data.first()[DB_INITIALIZED_KEY] ?: false
    }

    /**
     * Marks the database as initialized.
     */
    suspend fun setDatabaseInitialized() {
        context.dataStore.edit { preferences ->
            preferences[DB_INITIALIZED_KEY] = true
        }
    }

    /**
     * Saves the selected record ID to DataStore.
     */
    suspend fun saveSelectedRecordId(id: Long) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_RECORD_KEY] = id
        }
    }

    /**
     * Retrieves the selected record ID from DataStore.
     */
    val selectedRecordId: Long
        get() = runBlocking {
            context.dataStore.data.first()[SELECTED_RECORD_KEY] ?: 0L
        }
}

