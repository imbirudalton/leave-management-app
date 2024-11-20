package com.dalton.myleavemanager.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dalton.myleavemanager.LeaveRecord
import com.dalton.myleavemanager.ui.theme.MyLeaveManagerTheme


@Composable
fun LeaveDetailsPage(
    modifier: Modifier = Modifier,
    leaveRecord: LeaveRecord,
    onApprove: () -> Unit,
    onDecline: () -> Unit
) {

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Leave Details",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )

                LeaveDetailItem(label = "Employee Name", value = leaveRecord.employeeName)
                LeaveDetailItem(label = "Leave Type", value = leaveRecord.leaveType)
                LeaveDetailItem(label = "Start Date", value = leaveRecord.startDate)
                LeaveDetailItem(label = "End Date", value = leaveRecord.endDate)
                LeaveDetailItem(label = "Reason", value = leaveRecord.reason)
                LeaveDetailItem(label = "Additional Notes", value = leaveRecord.additionalNotes)
                LeaveDetailItem(label = "Status", value = leaveRecord.status)

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onApprove,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
                    ) {
                        Text("Approve")
                    }
                    Button(
                        onClick = onDecline,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Decline")
                    }
                }
            }
        }
    }
}

@Composable
fun LeaveDetailItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

@Preview
@Composable
private fun LeaveDetailsScreenPreview() {
    MyLeaveManagerTheme {
        val leaveRecord = LeaveRecord(
            employeeId = 12345,
            department = "IT",
            leaveDays = 50,
            leaveHours = 120,
            employeeName = "John Doe",
            leaveType = "Annual Leave",
            startDate = "2024-11-20",
            endDate = "2024-11-25",
            reason = "Family event",
            additionalNotes = "N/A",
            status = "Pending"
        )

        LeaveDetailsPage(
            leaveRecord = leaveRecord,
            onApprove = { /* Handle approval action */ },
            onDecline = { /* Handle decline action */ }
        )
    }
}
