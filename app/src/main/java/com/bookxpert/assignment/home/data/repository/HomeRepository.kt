package com.bookxpert.assignment.home.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.bookxpert.assignment.core.constants.PreferenceKeys
import com.bookxpert.assignment.core.networking.ApiInterface
import com.bookxpert.assignment.home.data.dataclasses.Objects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val apiInterface: ApiInterface
)  {
    suspend fun getObjects(): Flow<List<Objects>> {
        return flow {
            val response = apiInterface.getObjects()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

    val isUserLoggedIn: Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[PreferenceKeys.IS_USER_LOGGED_IN] ?: false
        }

    suspend fun updateLoggedInState(loggedInState: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.IS_USER_LOGGED_IN] = loggedInState
        }
    }
}