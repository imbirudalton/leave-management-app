package com.dalton.myleavemanager.database.room

import android.content.Context
import com.dalton.myleavemanager.DBPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun initializeDatabaseIfNeeded(context: Context, dao: LeaveRecordDao) {
    val preferences = DBPreferences(context)
    CoroutineScope(Dispatchers.IO).launch {
        if (!preferences.isDatabaseInitialized()) {
            predefinedLeaveRecords.forEach { record ->
                dao.insertLeaveRecord(record)
            }
            preferences.setDatabaseInitialized()
        }
    }
}
