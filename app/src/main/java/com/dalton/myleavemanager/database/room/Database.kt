package com.dalton.myleavemanager.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dalton.myleavemanager.database.room.entities.LeaveRecordEntity

@Database(entities = [LeaveRecordEntity::class], version = 1, exportSchema = false)
abstract class LeaveDatabase : RoomDatabase() {
    abstract fun leaveRecordDao(): LeaveRecordDao

    companion object {
        @Volatile
        private var INSTANCE: LeaveDatabase? = null

        fun getDatabase(context: Context): LeaveDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LeaveDatabase::class.java,
                    "leave_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
