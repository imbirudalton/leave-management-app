package com.dalton.myleavemanager

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.dalton.myleavemanager.ui.theme.MyLeaveManagerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Dashboard1(
    modifier: Modifier = Modifier,
//    realm: Realm,
    userPreferences: UserPreferences,
    onNavigateTo: (String) -> Unit
) {

    val leaveRecords = remember { mutableStateListOf<LeaveRecord>() }
    val scope = rememberCoroutineScope()
    val testLeaveRecords = remember { mutableStateListOf<LeaveRecord>().apply { addAll(generateTestLeaveRecords().sortedBy { it.startDate }.reversed()) } }

    var showForm = remember { mutableStateOf(false) }

    // Fetch records
  /*  LaunchedEffect(Unit) {
        leaveRecords.clear()
        leaveRecords.addAll(realm.query<LeaveRecordEntity>()
            .find()
            .map { it.toLeaveRecord() })
    }*/

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
                        scope.launch(Dispatchers.IO) {
                            /*realm.write {
                                this.copyToRealm(LeaveRecordEntity().apply {
                                    this.employeeName = newRecord.employeeName
                                    this.leaveType = newRecord.leaveType
                                    this.startDate = newRecord.startDate
                                    this.endDate = newRecord.endDate
                                    this.reason = newRecord.reason
                                    this.additionalNotes = newRecord.additionalNotes
                                    this.status = newRecord.status
                                })
                            }
                            leaveRecords.clear()
                            leaveRecords.addAll(realm.query<LeaveRecordEntity>(
                                clazz = LeaveRecordEntity::class)
                                .find()
                                .map { it.toLeaveRecord() })*/
                        }
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
                                    text = "Hello! Dalton.",
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

                            Column(
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


                            }

//                        val sampleData = generateTestLeaveRecords()
                            LeaveHistoryScreen(leaveRecords)
                        }
                    }

                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LeaveHistoryScreen(leaveRecords: List<LeaveRecord>) {
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

        leaveRecords.groupBy { extractYear(it.startDate) }.forEach { (year, records) ->
            item {
                YearHeader(year = year)
            }
            items(records) { record ->
                LeaveHistoryListItem(record)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LeaveHistoryListItem(record: LeaveRecord) {
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
                    StatusBox(
                        text = record.status,
                        color = getStatusColor(record.status)
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
fun StatusBox(text: String, color: Color) {
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
fun YearHeader(year: String) {
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
fun PreviewLeaveHistoryScreen() {
    LeaveHistoryScreen(leaveRecords = emptyList())
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview()
@Composable
private fun DashboardPreview() {
    MyLeaveManagerTheme {
        val context = LocalContext.current
        Dashboard1(Modifier,
            userPreferences = UserPreferences(context),
//       realm = MyApp.realm
        ) {

        }
    }
}


