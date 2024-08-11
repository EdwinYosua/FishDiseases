package com.edwinyosua.fishdiseasesapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

class SettingPreference(
    private val context: Context
) {

    private val userId = stringPreferencesKey("userId")
//    private val isUserLogin = booleanPreferencesKey("isLogin")
//    private val userName = stringPreferencesKey("userName")

//    fun checkUserLogin(): Flow<Boolean?> {
//        return context.dataStore.data.map { pref ->
//            pref[this.isUserLogin] ?: false
//        }
//    }

    fun getUserId(): Flow<String?> {
        return context.dataStore.data.map { pref ->
            pref[this.userId]
        }
    }

    suspend fun saveUserLoginData(userId: String) {
        context.dataStore.edit { pref ->
            pref[this.userId] = userId
//            pref[this.isUserLogin] = true
        }
    }

    suspend fun clearUserLoginData() {
        context.dataStore.edit { pref ->
            pref.remove(userId)
//            pref[this.isUserLogin] = false
        }
    }

}


