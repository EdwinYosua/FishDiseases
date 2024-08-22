package com.edwinyosua.fishdiseasesapp.data.local

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.edwinyosua.fishdiseasesapp.utils.ConstVal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

class SettingPreference(
    private val context: Context
) {

    private val userId = stringPreferencesKey("userId")


    fun getUserId(): Flow<String?> {
        return context.dataStore.data.map { pref ->
            reloadKoin()
            pref[this.userId]
        }
    }


    suspend fun saveUserLoginData(userId: String) {
        context.dataStore.edit { pref ->
            pref[this.userId] = userId
            reloadKoin()
        }
        Log.d("TokenCheck", "SettingPreference : savedUserLoginData")
    }

    suspend fun clearUserLoginData() {
        context.dataStore.edit { pref ->
            pref.remove(this.userId)
            reloadKoin()
        }
        Log.d("TokenCheck", "SettingPreference : clearUserLoginData")
    }

    private fun reloadKoin() {
        unloadKoinModules(ConstVal.allModules)
        loadKoinModules(ConstVal.allModules)
    }
}


