package com.edwinyosua.fishdiseasesapp.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingPreference private constructor(
    private val dataStore: DataStore<Preferences>
) {

    private val userId = stringPreferencesKey("userId")
//    private val userName = stringPreferencesKey("userName")

    fun getUserId(): Flow<String?> {
        return dataStore.data.map { pref ->
            pref[userId]
        }
    }

    suspend fun saveUserLoginData(userId: String) {
        dataStore.edit { pref ->
            pref[this.userId] = userId
        }
    }

    suspend fun clearUserLoginData() {
        dataStore.edit { pref ->
            pref.remove(userId)
        }
    }

}