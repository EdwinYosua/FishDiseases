package com.edwinyosua.fishdiseasesapp.data.local

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

class SettingPreference(
    private val context: Context
) {

    private val userId = stringPreferencesKey("userId")


    suspend fun getUserId(): String? {
        val userId = context.dataStore.data.first()
        try {
            Log.d("SettingPreference", userId.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return userId[this.userId]
    }

    suspend fun saveUserLoginData(userId: String) {
        context.dataStore.edit { pref ->
            pref[this.userId] = userId
        }
    }

    suspend fun clearUserLoginData() {
        context.dataStore.edit { pref ->
            pref.remove(userId)
        }
    }

}


