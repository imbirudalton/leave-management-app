package com.dalton.myleavemanager

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import org.mongodb.kbson.ObjectId
import kotlin.random.Random
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun generateTestLeaveRecords(): List<LeaveRecord> {
    val employeeNames = listOf("Alice Smith", "Bob Johnson", "Charlie Brown", "David Wilson", "Eva Martinez")
    val leaveTypes = listOf("Annual", "Sick", "Casual", "Maternity", "Paternity")
    val statuses = listOf("pending", "complete", "approved", "rejected")
    val reasons = listOf("Personal reasons", "Health issues", "Family matters", "Vacation", "Parental leave")
    val notes = listOf("Approved by supervisor", "Requires HR approval", "Pending manager review", "Already reviewed", "Follow-up needed")

    // Define date format
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    return List(20) {
        val employeeName = employeeNames.random()
        val leaveType = leaveTypes.random()
        val status = statuses.random()
        val reason = reasons.random()
        val additionalNotes = notes.random()

        // Randomly generate start and end dates within a year range
        val startYear = Random.nextInt(2020, 2024)
        val startMonth = Random.nextInt(1, 12)
        val startDay = Random.nextInt(1, 28)
        val startDate = LocalDate.of(startYear, startMonth, startDay)

        // Set end date 1 to 5 days after the start date
        val endDate = startDate.plusDays(Random.nextLong(1, 5))

        LeaveRecord(
            employeeId = 105,
            department = "IT",
            leaveDays = 50,
            leaveHours = 120,
            employeeName = employeeName,
            leaveType = leaveType,
            startDate = startDate.format(dateFormatter),
            endDate = endDate.format(dateFormatter),
            reason = reason,
            additionalNotes = additionalNotes,
            status = status,
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun extractYear(date: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val localDate = LocalDate.parse(date, formatter)
    return localDate.year.toString()
}


@RequiresApi(Build.VERSION_CODES.O)
fun calculateDuration(startDate: String, endDate: String): String {
    return try {
        // Define the date format used
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        // Parse start and end dates
        val start = LocalDate.parse(startDate, formatter)
        val end = LocalDate.parse(endDate, formatter)

        // Calculate days between
        val days = ChronoUnit.DAYS.between(start, end).toInt()

        // Return the duration as a string
        "$days days"
    } catch (e: Exception) {
        "Invalid dates" // Return error message if parsing fails
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateRange(startDate: String, endDate: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val start = LocalDate.parse(startDate, formatter)
    val end = LocalDate.parse(endDate, formatter)

    return if (start.month == end.month) {
        "${start.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)} ${start.dayOfMonth} - ${end.dayOfMonth}"
    } else {
        "${start.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)} ${start.dayOfMonth} - " +
                "${end.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)} ${end.dayOfMonth}"
    }
}

fun getLeaveColor(leaveType: String): Color {
    return when (leaveType) {
        "Annual" -> Color(0xff9a8719)
        "Sick" -> Color(0xff9a5519)
        "Casual" -> Color(0xff4a7ba4)
        "Maternity" -> Color(0xffe57373)
        "Paternity" -> Color(0xff64b5f6)
        else -> Color.Black
    }
}

fun getStatusColor(status: String): Color {
    return when (status) {
        "pending" -> Color(0xff848484)
        "complete" -> Color(0xff2f9a19)
        "approved" -> Color(0xff009688)
        "rejected" -> Color(0xffff0000)
        else -> Color.Gray
    }
}
