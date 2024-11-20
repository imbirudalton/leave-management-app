package com.dalton.myleavemanager

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.realm.kotlin.Realm

@Composable
fun Dashboard(
    modifier: Modifier = Modifier,
    onNavigateTo: (String) -> Unit
    ) {


    Scaffold(modifier = modifier.fillMaxSize()
    ) { innerPadding ->

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .padding(32.dp)
                    .fillMaxSize()
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.Top),
                    modifier = Modifier
                ) {
                    Text(
                        text = "My Leave Manager",
                        style = TextStyle(
                            fontWeight = FontWeight.W900,
                            fontSize = 24.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.Top),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Hello! Dalton.",
                            color = Color(0xff9a8719),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )

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
                            Column(
                                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top)
                            ) {
                                Text(
                                    text = "Leave Days:",
                                    color = Color(0xff9a8719),
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    text = "120d",
                                    style = TextStyle(
                                        fontWeight = FontWeight.W900,
                                        fontSize = 24.sp
                                    )
                                )
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.Top),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "History",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(
                                        22.dp,
                                        Alignment.Top
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = "2024",
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    )
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(
                                            16.dp,
                                            Alignment.Top
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    24.dp,
                                                    Alignment.Start
                                                ),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.ic_hash),
                                                    contentDescription = "Vector",
                                                    modifier = Modifier
                                                        .requiredWidth(width = 26.dp)
                                                        .requiredHeight(height = 20.dp)
                                                )
                                                Column(
                                                    verticalArrangement = Arrangement.spacedBy(
                                                        8.dp,
                                                        Alignment.Top
                                                    )
                                                ) {
                                                    Text(
                                                        text = "September 12 - October 12",
                                                        style = TextStyle(
                                                            fontSize = 14.sp,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                    )
                                                    Row(
                                                        horizontalArrangement = Arrangement.spacedBy(
                                                            8.dp,
                                                            Alignment.Start
                                                        )
                                                    ) {
                                                        Text(
                                                            text = "Annual",
                                                            color = Color(0xff9a8719),
                                                            style = TextStyle(
                                                                fontSize = 14.sp,
                                                                fontWeight = FontWeight.Bold
                                                            )
                                                        )
                                                        Box(
                                                            modifier = Modifier
                                                                .requiredWidth(width = 71.dp)
                                                                .requiredHeight(height = 21.dp)
                                                        ) {
                                                            Row(
                                                                horizontalArrangement = Arrangement.spacedBy(
                                                                    8.dp,
                                                                    Alignment.Start
                                                                )
                                                            ) {
                                                                Box(
                                                                    modifier = Modifier
                                                                        .requiredWidth(width = 71.dp)
                                                                        .requiredHeight(height = 21.dp)
                                                                        .clip(
                                                                            shape = RoundedCornerShape(
                                                                                6.dp
                                                                            )
                                                                        )
                                                                        .background(
                                                                            color = Color(
                                                                                0xffe5e5e5
                                                                            )
                                                                        )
                                                                )
                                                            }
                                                            Text(
                                                                text = "pending",
                                                                color = Color(0xff848484),
                                                                style = TextStyle(
                                                                    fontSize = 12.sp
                                                                ),
                                                                modifier = Modifier
                                                                    .align(alignment = Alignment.Center)
                                                                    .offset(
                                                                        x = (-0.5).dp,
                                                                        y = 0.dp
                                                                    )
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                            Text(
                                                text = "31 days",
                                                style = TextStyle(
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Medium
                                                )
                                            )
                                        }
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    24.dp,
                                                    Alignment.Start
                                                ),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.ic_hash),
                                                    contentDescription = "Vector",
                                                    modifier = Modifier
                                                        .requiredWidth(width = 26.dp)
                                                        .requiredHeight(height = 20.dp)
                                                )
                                                Column(
                                                    verticalArrangement = Arrangement.spacedBy(
                                                        8.dp,
                                                        Alignment.Top
                                                    )
                                                ) {
                                                    Text(
                                                        text = "June 30 - July 2",
                                                        style = TextStyle(
                                                            fontSize = 14.sp,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                    )
                                                    Row(
                                                        horizontalArrangement = Arrangement.spacedBy(
                                                            8.dp,
                                                            Alignment.Start
                                                        )
                                                    ) {
                                                        Text(
                                                            text = "Sick",
                                                            color = Color(0xff9a5519),
                                                            style = TextStyle(
                                                                fontSize = 14.sp,
                                                                fontWeight = FontWeight.Bold
                                                            )
                                                        )
                                                        Box(
                                                            modifier = Modifier
                                                                .requiredWidth(width = 71.dp)
                                                                .requiredHeight(height = 21.dp)
                                                        ) {
                                                            Row(
                                                                horizontalArrangement = Arrangement.spacedBy(
                                                                    8.dp,
                                                                    Alignment.Start
                                                                )
                                                            ) {
                                                                Box(
                                                                    modifier = Modifier
                                                                        .requiredWidth(width = 71.dp)
                                                                        .requiredHeight(height = 21.dp)
                                                                        .clip(
                                                                            shape = RoundedCornerShape(
                                                                                6.dp
                                                                            )
                                                                        )
                                                                        .background(
                                                                            color = Color(0xff22cc00).copy(
                                                                                alpha = 0.46f
                                                                            )
                                                                        )
                                                                )
                                                            }
                                                            Text(
                                                                text = "complete",
                                                                color = Color(0xff2f9a19),
                                                                style = TextStyle(
                                                                    fontSize = 12.sp
                                                                ),
                                                                modifier = Modifier
                                                                    .align(alignment = Alignment.Center)
                                                                    .offset(
                                                                        x = 0.dp,
                                                                        y = 0.dp
                                                                    )
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                            Text(
                                                text = "3 days",
                                                color = Color.Black,
                                                style = TextStyle(
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Medium
                                                )
                                            )
                                        }
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    24.dp,
                                                    Alignment.Start
                                                ),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.ic_hash),
                                                    contentDescription = "Vector",
                                                    modifier = Modifier
                                                        .requiredWidth(width = 26.dp)
                                                        .requiredHeight(height = 20.dp)
                                                )
                                                Column(
                                                    verticalArrangement = Arrangement.spacedBy(
                                                        8.dp,
                                                        Alignment.Top
                                                    )
                                                ) {
                                                    Text(
                                                        text = "March 12 -Match 16",
                                                        style = TextStyle(
                                                            fontSize = 14.sp,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                    )
                                                    Row(
                                                        horizontalArrangement = Arrangement.spacedBy(
                                                            8.dp,
                                                            Alignment.Start
                                                        )
                                                    ) {
                                                        Text(
                                                            text = "Sick",
                                                            color = Color(0xff9a5519),
                                                            style = TextStyle(
                                                                fontSize = 14.sp,
                                                                fontWeight = FontWeight.Bold
                                                            )
                                                        )
                                                        Box(
                                                            modifier = Modifier
                                                                .requiredWidth(width = 71.dp)
                                                                .requiredHeight(height = 21.dp)
                                                        ) {
                                                            Row(
                                                                horizontalArrangement = Arrangement.spacedBy(
                                                                    8.dp,
                                                                    Alignment.Start
                                                                )
                                                            ) {
                                                                Box(
                                                                    modifier = Modifier
                                                                        .requiredWidth(width = 71.dp)
                                                                        .requiredHeight(height = 21.dp)
                                                                        .clip(
                                                                            shape = RoundedCornerShape(
                                                                                6.dp
                                                                            )
                                                                        )
                                                                        .background(
                                                                            color = Color(0xff22cc00).copy(
                                                                                alpha = 0.46f
                                                                            )
                                                                        )
                                                                )
                                                            }
                                                            Text(
                                                                text = "complete",
                                                                color = Color(0xff2f9a19),
                                                                style = TextStyle(
                                                                    fontSize = 12.sp
                                                                ),
                                                                modifier = Modifier
                                                                    .align(alignment = Alignment.Center)
                                                                    .offset(
                                                                        x = 0.dp,
                                                                        y = 0.dp
                                                                    )
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                            Text(
                                                text = "5 days",
                                                color = Color.Black,
                                                style = TextStyle(
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Medium
                                                )
                                            )
                                        }
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    24.dp,
                                                    Alignment.Start
                                                ),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.ic_hash),
                                                    contentDescription = "Vector",
                                                    modifier = Modifier
                                                        .requiredWidth(width = 26.dp)
                                                        .requiredHeight(height = 20.dp)
                                                )
                                                Column(
                                                    verticalArrangement = Arrangement.spacedBy(
                                                        8.dp,
                                                        Alignment.Top
                                                    )
                                                ) {
                                                    Text(
                                                        text = "January 6",
                                                        style = TextStyle(
                                                            fontSize = 14.sp,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                    )
                                                    Row(
                                                        horizontalArrangement = Arrangement.spacedBy(
                                                            8.dp,
                                                            Alignment.Start
                                                        )
                                                    ) {
                                                        Text(
                                                            text = "Sick",
                                                            color = Color(0xff9a5519),
                                                            style = TextStyle(
                                                                fontSize = 14.sp,
                                                                fontWeight = FontWeight.Bold
                                                            )
                                                        )
                                                        Box(
                                                            modifier = Modifier
                                                                .requiredWidth(width = 71.dp)
                                                                .requiredHeight(height = 21.dp)
                                                        ) {
                                                            Row(
                                                                horizontalArrangement = Arrangement.spacedBy(
                                                                    8.dp,
                                                                    Alignment.Start
                                                                )
                                                            ) {
                                                                Box(
                                                                    modifier = Modifier
                                                                        .requiredWidth(width = 71.dp)
                                                                        .requiredHeight(height = 21.dp)
                                                                        .clip(
                                                                            shape = RoundedCornerShape(
                                                                                6.dp
                                                                            )
                                                                        )
                                                                        .background(
                                                                            color = Color(0xffffbb00).copy(
                                                                                alpha = 0.49f
                                                                            )
                                                                        )
                                                                )
                                                            }
                                                            Text(
                                                                text = "denied",
                                                                color = Color(0xffbe8b00),
                                                                style = TextStyle(
                                                                    fontSize = 12.sp
                                                                ),
                                                                modifier = Modifier
                                                                    .align(alignment = Alignment.Center)
                                                                    .offset(
                                                                        x = (-0.5).dp,
                                                                        y = 0.dp
                                                                    )
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                            Text(
                                                text = "1 days",
                                                color = Color.Black,
                                                style = TextStyle(
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Medium
                                                )
                                            )
                                        }
                                    }
                                }
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(
                                        22.dp,
                                        Alignment.Top
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = "2023",
                                        color = Color.Black,
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    )
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(
                                            16.dp,
                                            Alignment.Top
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    24.dp,
                                                    Alignment.Start
                                                ),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.ic_hash),
                                                    contentDescription = "Vector",
                                                    modifier = Modifier
                                                        .requiredWidth(width = 26.dp)
                                                        .requiredHeight(height = 20.dp)
                                                )
                                                Column(
                                                    verticalArrangement = Arrangement.spacedBy(
                                                        8.dp,
                                                        Alignment.Top
                                                    )
                                                ) {
                                                    Text(
                                                        text = "February 3 - October 29",
                                                        style = TextStyle(
                                                            fontSize = 14.sp,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                    )
                                                    Row(
                                                        horizontalArrangement = Arrangement.spacedBy(
                                                            8.dp,
                                                            Alignment.Start
                                                        )
                                                    ) {
                                                        Text(
                                                            text = "Paternity",
                                                            color = Color(0xff6d9a19),
                                                            style = TextStyle(
                                                                fontSize = 14.sp,
                                                                fontWeight = FontWeight.Bold
                                                            )
                                                        )
                                                        Box(
                                                            modifier = Modifier
                                                                .requiredWidth(width = 84.dp)
                                                                .requiredHeight(height = 21.dp)
                                                        ) {
                                                            Row(
                                                                horizontalArrangement = Arrangement.spacedBy(
                                                                    8.dp,
                                                                    Alignment.Start
                                                                )
                                                            ) {
                                                                Box(
                                                                    modifier = Modifier
                                                                        .requiredWidth(width = 84.dp)
                                                                        .requiredHeight(height = 21.dp)
                                                                        .clip(
                                                                            shape = RoundedCornerShape(
                                                                                6.dp
                                                                            )
                                                                        )
                                                                        .background(
                                                                            color = Color(0xff22cc00).copy(
                                                                                alpha = 0.46f
                                                                            )
                                                                        )
                                                                )
                                                            }
                                                            Text(
                                                                text = "complete",
                                                                color = Color(0xff2f9a19),
                                                                style = TextStyle(
                                                                    fontSize = 12.sp
                                                                ),
                                                                modifier = Modifier
                                                                    .align(alignment = Alignment.Center)
                                                                    .offset(
                                                                        x = (-3.5).dp,
                                                                        y = 0.dp
                                                                    )
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                            Text(
                                                text = "9 months",
                                                color = Color.Black,
                                                style = TextStyle(
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Medium
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 43.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        modifier = Modifier
                            .requiredWidth(width = 232.dp)
                            .requiredHeight(height = 43.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xff9a8719)
                        ),
                        onClick = {
                            onNavigateTo("LeaveApplicationForm")
                        }
                    ) {

                        Text(
                            text = "Apply Leave",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 20.sp
                            ),
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}

@Preview()
@Composable
private fun DashboardPreview() {
    Dashboard(Modifier) {

    }
}