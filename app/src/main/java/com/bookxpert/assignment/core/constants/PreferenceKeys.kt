package com.bookxpert.assignment.core.constants

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferenceKeys {
    val NOTIFICATION_TOGGLE = booleanPreferencesKey(AppConstants.NOTIFICATION_TOGGLE_KEY)
    val IS_USER_LOGGED_IN = booleanPreferencesKey(AppConstants.IS_USER_LOGGED_IN_KEY)
}