package com.dalton.myleavemanager.database.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dalton.myleavemanager.LeaveRecord

@Entity(tableName = "LeaveRecords")
data class LeaveRecordEntity(
    @PrimaryKey
    var id: Long? = null,
    var employeeId: Int  = 0,
    var department: String = "",
    var leaveDays: Int = 0,
    var leaveHours: Int = 0,
    var employeeName: String = "",
    var leaveType: String = "",
    var startDate: String = "",
    var endDate: String = "",
    var reason: String = "",
    var additionalNotes: String = "",
    var status: String = ""
)


fun LeaveRecordEntity.toLeaveRecord(): LeaveRecord  {
    return LeaveRecord(
        id = this.id,
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