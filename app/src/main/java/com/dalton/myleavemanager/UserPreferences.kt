package com.dalton.myleavemanager

import android.content.Context
import androidx.datastore.preferences.core.edit
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

    suspend fun  clearUserDetails() {
        context.dataStore.edit { prefs ->
            prefs[USERNAME_KEY] = ""
            prefs[EMAIL_KEY] = ""
            prefs[TYPE_KEY] = ""
        }
    }
}

// Data class for User
data class User(val username: String, val email: String, val type: String)
