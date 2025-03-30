package com.bookxpert.assignment.home.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.bookxpert.assignment.core.networking.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
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
}