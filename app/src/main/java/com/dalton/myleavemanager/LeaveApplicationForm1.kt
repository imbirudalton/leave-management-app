package com.dalton.myleavemanager

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dalton.myleavemanager.ui.theme.MyLeaveManagerTheme
import java.time.LocalDate
import java.time.format.DateTimeParseException
import kotlin.math.exp
import kotlin.random.Random

@Composable
fun LeaveApplicationForm1(
    modifier: Modifier,
    onAddLeaveRecord: (LeaveRecord) -> Unit
) {
    var employeeName = remember { mutableStateOf("") }
    var leaveType = remember { mutableStateOf("") }
    var startDate = remember { mutableStateOf("") }
    var endDate = remember { mutableStateOf("") }
    var reason = remember { mutableStateOf("") }
    var additionalNotes = remember { mutableStateOf("") }
    var expanded = remember { mutableStateOf(false) }
    var formError = remember { mutableStateOf("") }

    val leaveTypes = listOf("Sick Leave", "Casual Leave", "Annual Leave", "Maternity Leave", "Paternity Leave", "Compassionate Leave")

    @RequiresApi(Build.VERSION_CODES.O)
    fun validateForm(): Boolean {
        formError.value = ""

        // Basic empty field check
        if (employeeName.value.isBlank() || leaveType.value.isBlank() || startDate.value.isBlank() || endDate.value.isBlank() || reason.value.isBlank()) {
            formError.value = "Please fill in all required fields."
            return false
        }

        // Date validation (basic check: end date is after or same as start date)
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val start = LocalDate.parse(startDate.value)
            val end = LocalDate.parse(endDate.value)
            if (end.isBefore(start)) {
                formError.value = "End date cannot be before start date."
                return false
            }
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        } catch (e: DateTimeParseException) {
            formError.value = "Please enter valid dates in yyyy-mm-dd format."
            return false
        }

        return true
    }

    Scaffold(modifier = modifier.fillMaxSize()
    ) { innerPadding ->

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Leave Application Form", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = employeeName.value,
            onValueChange = { employeeName.value = it },
            label = { Text("Employee Name") },
            modifier = Modifier.fillMaxWidth(),
            isError = employeeName.value.isBlank() && formError.value.isNotEmpty()
        )

        // Dropdown for Leave Type
        Box(modifier = modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = leaveType.value,
                onValueChange = { /* leaveType is set by dropdown */ },
                label = { Text("Leave Type") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded.value = true },
                readOnly = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown",
                        modifier = modifier.clickable{
                            expanded.value = true
                        }
                    )
                },
                isError = leaveType.value.isBlank() && formError.value.isNotEmpty()
            )
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = modifier.fillMaxWidth()
            ) {
                leaveTypes.forEach { type ->
                    DropdownMenuItem(
                        onClick = {
                            leaveType.value = type
                            expanded.value = false
                        },
                        text = {
                            Text(type)
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }

        OutlinedTextField(
            value = startDate.value,
            onValueChange = { startDate.value = it },
            label = { Text("Start Date") },
            placeholder = { Text("yyyy-mm-dd") },
            modifier = Modifier.fillMaxWidth(),
            isError = startDate.value.isBlank() && formError.value.isNotEmpty()
        )

        OutlinedTextField(
            value = endDate.value,
            onValueChange = { endDate.value = it },
            label = { Text("End Date") },
            placeholder = { Text("yyyy-mm-dd") },
            modifier = Modifier.fillMaxWidth(),
            isError = endDate.value.isBlank() && formError.value.isNotEmpty()
        )

        OutlinedTextField(
            value = reason.value,
            onValueChange = { reason.value = it },
            label = { Text("Reason for Leave") },
            modifier = Modifier.fillMaxWidth(),
            isError = reason.value.isBlank() && formError.value.isNotEmpty()
        )

        OutlinedTextField(
            value = additionalNotes.value,
            onValueChange = { additionalNotes.value = it },
            label = { Text("Additional Notes") },
            modifier = Modifier.fillMaxWidth()
        )

        if (formError.value.isNotEmpty()) {
            Text(
                text = formError.value,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            onClick = {
                if (validateForm()) {
                    val newRecord = LeaveRecord(
                        employeeId = Random.nextInt(),
                        department = "IT",
                        leaveDays = 50,
                        leaveHours = 120,
                        employeeName = employeeName.value,
                        leaveType = leaveType.value,
                        startDate = startDate.value,
                        endDate = endDate.value,
                        reason = reason.value,
                        additionalNotes = additionalNotes.value,
                        status = "Pending"
                    )
                    onAddLeaveRecord(newRecord)
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Submit Application")
        }
    }
        }
}

@Preview
@Composable
private fun LeaveApplicationFormPreview() {
    MyLeaveManagerTheme() {
        val context = LocalContext.current
        LeaveApplicationForm (
            modifier = Modifier,
            userPreferences = UserPreferences(context),
            onAddLeaveRecord = {

            })
    }
}
