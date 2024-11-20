
package com.dalton.myleavemanager.admin

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dalton.myleavemanager.LeaveRecord
import com.dalton.myleavemanager.StatusBox
import com.dalton.myleavemanager.YearHeader
import com.dalton.myleavemanager.calculateDuration
import com.dalton.myleavemanager.extractYear
import com.dalton.myleavemanager.generateTestLeaveRecords
import com.dalton.myleavemanager.getLeaveColor
import com.dalton.myleavemanager.getStatusColor

@SuppressLint("NewApi")
@Composable
fun AdminScreen(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit
) {

    val leaveRecords = remember { mutableStateListOf<LeaveRecord>().apply { addAll(generateTestLeaveRecords().sortedBy { it.startDate }.reversed()) } }

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->

        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {
                    Text(
                        text = "Admin",
                        style = TextStyle(
                            fontWeight = FontWeight.W900,
                            fontSize = 20.sp
                        ),
                        modifier = modifier
                    )
                    Text(
                        text = "Leave Application Records",
                        style = TextStyle(
                            fontWeight = FontWeight.Light,
                        ),
                        modifier = Modifier
                    )
                }


                IconButton(
                    onClick = {
                        onNavigate("Account")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Account Icon"
                    )
                }
            }

            LeaveRecords(
                leaveRecords,
                onRecordClicked = {
                    onNavigate("LeaveDetails")
                },
                onNavigate = {
                    onNavigate("LeaveDetails")
                }
            )
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LeaveRecords(
    leaveRecords: List<LeaveRecord>,
    onRecordClicked: (LeaveRecord) -> Unit,
    onNavigate: (String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {


        leaveRecords.groupBy { extractYear(it.startDate) }.forEach { (year, records) ->
            item {
                YearHeader(year = year)
            }
            items(records) { record ->
                LeaveRecordListItem(record) {
                    onRecordClicked(record)
                }
            }
        }
    }
}




@SuppressLint("NewApi")
@Composable
fun LeaveRecordListItem(
    record: LeaveRecord,
    onCLick: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        modifier = Modifier.fillMaxWidth().clickable{
            onCLick()
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
            ) {
                Text(
                    text = record.employeeName,
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
                    Text(
                        text = calculateDuration(
                            startDate = record.startDate,
                            endDate = record.endDate
                        ),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }

            }

            StatusBox(
                    text = record.status,
            color = getStatusColor(record.status)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview()
@Composable
private fun AdminScreenPreview() {
    AdminScreen (Modifier) {

    }
}
