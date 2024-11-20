package com.dalton.myleavemanager

import com.dalton.myleavemanager.database.room.entities.LeaveRecordEntity

data class LeaveRecord(
    val id: Long? = null,
    val employeeId: Int,
    val department: String,
    val leaveDays: Int,
    val leaveHours: Int,
    val employeeName: String,
    val leaveType: String,
    val startDate: String,
    val endDate: String,
    val reason: String,
    val additionalNotes: String,
    val status: String,
)

fun LeaveRecord.toLeaveRecordEntity(): LeaveRecordEntity {
    return LeaveRecordEntity(
        employeeId = this.employeeId,
        department = this.department,
        leaveDays = this.leaveDays,
        leaveHours = this.leaveHours,
        employeeName = this.employeeName,
        leaveType = this.leaveType,
        startDate = this.startDate,
        endDate = this.endDate,
        reason = this.reason,
        additionalNotes = this.additionalNotes,
        status = this.status,
        )
}

