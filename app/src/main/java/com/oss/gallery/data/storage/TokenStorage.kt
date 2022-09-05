package com.oss.gallery.data.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.oss.gallery.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class TokenStorage(
    private val context: Context,
    @IoDispatcher
    private val IoDispatcher: CoroutineDispatcher
) {

    companion object {
        @JvmStatic
        private val TOKEN_STORAGE = "TOKEN_STORAGE"

        @JvmStatic
        private val TOKEN_KEY = "TOKEN_KEY"
    }

    private val Context.dataStore by preferencesDataStore(name = TOKEN_STORAGE)
    private val tokenKey = stringPreferencesKey(TOKEN_KEY)

    suspend fun saveToken(token: String): Boolean = withContext(IoDispatcher) {
        return@withContext try {
            context.dataStore.edit { prefs ->
                prefs[tokenKey] = token
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getToken(): String = withContext(IoDispatcher) {
        val data = context.dataStore.data
            .map { it[tokenKey] }
            .first()
        return@withContext data ?: ""
    }
}
