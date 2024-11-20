package com.dalton.myleavemanager


import com.dalton.myleavemanager.database.room.LeaveRecordDao
import com.dalton.myleavemanager.database.room.entities.LeaveRecordEntity
import kotlinx.coroutines.flow.Flow

class LeaveRecordRepository(private val dao: LeaveRecordDao) {
    val allLeaveRecords: Flow<List<LeaveRecordEntity>> = dao.getAllLeaveRecords()

    suspend fun insert(record: LeaveRecordEntity) {
        dao.insertLeaveRecord(record)
    }

    suspend fun getLeaveRecordById(id: Long): LeaveRecordEntity? {
        return dao.getLeaveRecordById(id)
    }

    suspend fun delete(record: LeaveRecordEntity) {
        dao.deleteLeaveRecord(record)
    }

    suspend fun update(record: LeaveRecordEntity) {
        dao.update(record)
    }
}
