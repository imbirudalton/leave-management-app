package com.dalton.myleavemanager.database.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dalton.myleavemanager.database.room.entities.LeaveRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LeaveRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeaveRecord(record: LeaveRecordEntity)

    @Query("SELECT * FROM LeaveRecords")
    fun getAllLeaveRecords(): Flow<List<LeaveRecordEntity>>

    @Query("SELECT * FROM LeaveRecords WHERE id = :id")
    suspend fun getLeaveRecordById(id: Long): LeaveRecordEntity?

    @Delete
    suspend fun deleteLeaveRecord(record: LeaveRecordEntity)

    @Update
    suspend fun update(record: LeaveRecordEntity)
}
