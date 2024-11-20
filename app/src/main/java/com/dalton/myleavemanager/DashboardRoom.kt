package com.dalton.myleavemanager

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dalton.myleavemanager.database.room.entities.toLeaveRecord
import com.dalton.myleavemanager.ui.theme.MyLeaveManagerTheme
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.LocalDateTime
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardRoom(
    modifier: Modifier = Modifier,
    userPreferences: UserPreferences,
    viewModel: LeaveRecordViewModel?,
    onNavigateTo: (String) -> Unit
) {

    val user = userPreferences.userDetails.collectAsState(initial = null)
    val leaveRecords by viewModel!!.allLeaveRecords.collectAsState(initial = emptyList())

    var showForm = remember { mutableStateOf(false) }


    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            if (showForm.value) {
                LeaveApplicationForm(
                    modifier = modifier,
                    userPreferences = userPreferences,
                    onAddLeaveRecord = { newRecord ->
//                        leaveRecords.add(0, newRecord)
                        viewModel.insert(newRecord.toLeaveRecordEntity())
                        showForm.value = false
                    },

                )
            } else {

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .padding(16.dp)
                        .fillMaxSize()
                ) {

                    Column(
                        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.Top),
                        modifier = Modifier.weight(1f)
                    ) {

                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "My Leave Manager",
                                style = TextStyle(
                                    fontWeight = FontWeight.W900,
                                    fontSize = 24.sp
                                ),
                                modifier = Modifier
                            )

                            IconButton(
                                onClick = {
                                    onNavigateTo("Account")
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = "Account Icon"
                                )
                            }
                        }


                        Column(
                            verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.Top),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {

                            Row(
                                modifier = modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Hello! ${user.value?.username ?: "Dalton"}.",
                                    color = Color(0xff9a8719),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    ),
                                    modifier = Modifier
                                )
                                Box(
                                    modifier = Modifier,
                                    contentAlignment = Alignment.Center
                                ) {
                                    Button(
                                        modifier = Modifier,
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xff9a8719)
                                        ),
                                        onClick = {
                                            showForm.value = true
                                        }
                                    ) {

                                        Text(
                                            text = "Apply Leave",
                                            color = Color.White,
                                            textAlign = TextAlign.Center,
                                            style = TextStyle(
                                                fontSize = 12.sp
                                            ),
                                            modifier = Modifier
                                        )
                                    }
                                }
                            }

                            /*Column(
                                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(shape = RoundedCornerShape(5.dp))
                                    .border(
                                        border = BorderStroke(1.dp, Color(0xff9a8719)),
                                        shape = RoundedCornerShape(5.dp)
                                    )
                                    .padding(
                                        horizontal = 28.dp,
                                        vertical = 32.dp
                                    )
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top)
                                ) {
                                    Text(
                                        text = "Annual leave Countdown:",
                                        color = Color(0xff9a8719),
                                        style = TextStyle(
                                            fontSize = 14.sp
                                        )
                                    )
                                    Text(
                                        text = "120d : 12h: 55m: 09s",
                                        style = TextStyle(
                                            fontWeight = FontWeight.W900,
                                            fontSize = 24.sp
                                        )
                                    )
                                }


                            }*/

                            LeaveCountdownCard(
                                totalLeaveDays = 30,
                                leaveUsed = 20,
                                nextLeaveDate = LocalDateTime.of(2024, 12, 25, 0, 0)
                            )

//                        val sampleData = generateTestLeaveRecords()
                            LeaveHistoryScreenRoom(leaveRecords.map { it.toLeaveRecord() })
                        }
                    }

                }
            }
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun LeaveCountdownCard(
    totalLeaveDays: Int,
    leaveUsed: Int,
    nextLeaveDate: LocalDateTime
) {
    val timeRemaining = remember { mutableStateOf(Duration.ZERO) }
    val leaveLeft = totalLeaveDays - leaveUsed
    val leavePercentage = ((leaveLeft.toFloat() / totalLeaveDays) * 100).coerceAtLeast(0f)

    // Timer logic
    LaunchedEffect(nextLeaveDate) {
        while (true) {
            val now = LocalDateTime.now()
            timeRemaining.value = Duration.between(now, nextLeaveDate)
            delay(1000) // Update every second
        }
    }

    /*val days = timeRemaining.value.toDays()
    val hours = timeRemaining.value.toHoursPart()
    val minutes = timeRemaining.value.toMinutesPart()
    val seconds = timeRemaining.value.toSecondsPart()*/

    val days = timeRemaining.value.toDays()
    val totalHours = timeRemaining.value.toHours() // Total hours including days
    val hours = totalHours - days * 24
    val totalMinutes = timeRemaining.value.toMinutes() // Total minutes including hours and days
    val minutes = totalMinutes - totalHours * 60
    val seconds = timeRemaining.value.seconds % 60

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp))
                .border(
                    border = BorderStroke(1.dp, Color(0xff9a8719)),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 28.dp, vertical = 32.dp)
        ) {
            // Countdown Section
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top)
            ) {
                Text(
                    text = "Next leave Countdown:",
                    color = Color(0xff9a8719),
                    style = TextStyle(fontSize = 14.sp)
                )
                Text(
                    text = String.format(
                        Locale.getDefault(),
                        "%02dd : %02dh : %02dm : %02ds",
                        days, hours, minutes, seconds
                    ),
                    style = TextStyle(
                        fontWeight = FontWeight.W900,
                        fontSize = 24.sp
                    )
                )
            }

            // Leave Days and Percentage Section
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top)
            ) {
                Text(
                    text = "Leave Days Left: $leaveLeft days",
                    color = Color.Black,
                    style = TextStyle(fontSize = 14.sp)
                )
                Text(
                    text = "Leave Utilization: ${leavePercentage.toInt()}%",
                    color = Color(0xff9a8719),
                    style = TextStyle(fontSize = 14.sp)
                )
                // Progress bar
                LinearProgressIndicator(
                    progress = leavePercentage / 100f,
                    color = Color(0xff9a8719),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                )
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LeaveHistoryScreenRoom(leaveRecords: List<LeaveRecord>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            Text(
                text = "History",
                style = TextStyle(
                    fontWeight = FontWeight.W900,
                    fontSize = 20.sp
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        leaveRecords.reversed().groupBy { extractYear(it.startDate) }.forEach { (year, records) ->
            item {
                YearHeaderRoom(year = year)
            }
            items(records) { record ->
                LeaveHistoryListItemRoom(record)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LeaveHistoryListItemRoom(record: LeaveRecord) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
            ) {
                Text(
                    text = formatDateRange(startDate = record.startDate, endDate = record.endDate),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W900
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
                ) {
                    Text(
                        text = record.leaveType,
                        color = getLeaveColor(record.leaveType),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    StatusBoxRoom(
                        text = record.status,
                        color = getStatusColor(record.status.lowercase())
                    )
                }
            }
            Text(
                text = calculateDuration(startDate = record.startDate, endDate = record.endDate),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Composable
fun StatusBoxRoom(text: String, color: Color) {
    Box(
        modifier = Modifier
            .requiredWidth(71.dp)
            .requiredHeight(21.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color.copy(alpha = 0.5f))
    ) {
        Text(
            text = text,
            color = color,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun YearHeaderRoom(year: String) {
    Text(
        text = year,
        style = TextStyle(
            fontWeight = FontWeight.W900,
            fontSize = 14.sp
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewLeaveHistoryScreenRoom() {
    LeaveHistoryScreen(leaveRecords = emptyList())
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview()
@Composable
private fun DashboardPreview() {
    MyLeaveManagerTheme {
        val context = LocalContext.current
        DashboardRoom(
            Modifier,
            userPreferences = UserPreferences(context),
            viewModel = null
        ) {

        }
    }
}


