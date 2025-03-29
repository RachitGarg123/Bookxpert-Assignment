package com.bookxpert.assignment.core.utility

import android.util.Log
import android.widget.Toast
import com.bookxpert.assignment.BuildConfig
import com.bookxpert.assignment.core.AssignmentBookxpertApplication
import com.bookxpert.assignment.core.constants.AppConstants

fun showToast(message: String, toastDuration: Int) {
    Toast.makeText(AssignmentBookxpertApplication.appContext, message, toastDuration).show()
}

fun printLog(logType: LogType, tag: String, message: String) {
    if(BuildConfig.BUILD_TYPE == AppConstants.DEBUG) {
        when (logType) {
            LogType.INFO -> {
                Log.i(tag, message)
            }
            LogType.WARN -> {
                Log.w(tag, message)
            }
            LogType.DEBUG -> {
                Log.d(tag, message)
            }
            LogType.ERROR -> {
                Log.e(tag, message)
            }
            LogType.VERBOSE -> {
                Log.v(tag, message)
            }
        }
    }
}

enum class LogType {
    WARN, ERROR, INFO, VERBOSE, DEBUG
}