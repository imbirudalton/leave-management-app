package com.dalton.myleavemanager

import android.app.Application
import com.dalton.myleavemanager.database.room.LeaveDatabase
import com.dalton.myleavemanager.database.room.initializeDatabaseIfNeeded

class MyApp : Application() {
    companion object {
        /*lateinit var realm: Realm
            private set*/
        lateinit var appDatabase: LeaveDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appDatabase = LeaveDatabase.getDatabase(this);
        val dao = appDatabase.leaveRecordDao()

        initializeDatabaseIfNeeded(this, dao)

        /*// Set up Realm configuration
        val config = RealmConfiguration.Builder(schema = setOf(LeaveRecordEntity::class)) // Add your schema classes
            .build()

        // Open Realm instance
        realm = Realm.open(config)*/
    }
}