package com.dalton.myleavemanager

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Create DataStore instance
private val Context.dataStore by preferencesDataStore("user_prefs")
class UserPreferences(private val context: Context) {

    companion object {
        val USERNAME_KEY = stringPreferencesKey("username")
        val EMAIL_KEY = stringPreferencesKey("email")
        val TYPE_KEY = stringPreferencesKey("type")
        val LEAVE_DAYS_KEY = intPreferencesKey("leave_days")
    }

    // Save user details
    suspend fun saveUser(username: String, email: String, type: String) {
        context.dataStore.edit { prefs ->
            prefs[USERNAME_KEY] = username
            prefs[EMAIL_KEY] = email
            prefs[TYPE_KEY] = type
        }
    }

    // Retrieve user details
    val userDetails: Flow<User?> = context.dataStore.data
        .map { prefs ->
            val username = prefs[USERNAME_KEY]
            val email = prefs[EMAIL_KEY]
            val type = prefs[TYPE_KEY]
            if (username != null && email != null && type != null) {
                User(username, email, type)
            } else null
        }

    // Clear user details
    suspend fun clearUserDetails() {
        context.dataStore.edit { prefs ->
            prefs[USERNAME_KEY] = ""
            prefs[EMAIL_KEY] = ""
            prefs[TYPE_KEY] = ""
            prefs[LEAVE_DAYS_KEY] = 0
        }
    }

    // Save leave days
    suspend fun saveLeaveDays(leaveDays: Int) {
        context.dataStore.edit { prefs ->
            prefs[LEAVE_DAYS_KEY] = leaveDays
        }
    }

    // Retrieve leave days
    val leaveDays: Flow<Int> = context.dataStore.data
        .map { prefs ->
            prefs[LEAVE_DAYS_KEY] ?: 0
        }

    // Update leave days
    suspend fun updateLeaveDays(days: Int) {
        context.dataStore.edit { prefs ->
            val currentLeaveDays = prefs[LEAVE_DAYS_KEY] ?: 0
            prefs[LEAVE_DAYS_KEY] = currentLeaveDays + days
        }
    }
}


// Data class for User
data class User(val username: String, val email: String, val type: String)
