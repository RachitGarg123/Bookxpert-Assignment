package com.bookxpert.assignment.notification.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.bookxpert.assignment.core.constants.PreferenceKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    val isNotificationEnabled: Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[PreferenceKeys.NOTIFICATION_TOGGLE] ?: true
        }

    suspend fun updateNotificationState(notificationState: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.NOTIFICATION_TOGGLE] = notificationState
        }
    }
}