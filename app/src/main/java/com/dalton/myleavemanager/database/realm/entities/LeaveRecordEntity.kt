package com.dalton.myleavemanager.database.realm.entities

import com.dalton.myleavemanager.LeaveRecord
import io.realm.kotlin.schema.RealmClass
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

/*
class LeaveRecordEntity(
    @PrimaryKey
    var id: ObjectId = ObjectId(),
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
) : RealmObject



fun LeaveRecordEntity.toLeaveRecord(): LeaveRecord  {
    return LeaveRecord(
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
}*/
