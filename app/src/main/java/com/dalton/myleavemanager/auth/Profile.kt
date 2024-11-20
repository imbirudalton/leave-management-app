package com.dalton.myleavemanager.auth

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dalton.myleavemanager.UserPreferences
import com.dalton.myleavemanager.ui.theme.MyLeaveManagerTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    userPreferences: UserPreferences,
//    auth: FirebaseAuth = Firebase.auth,
    onNavigate: (String) -> Unit
) {

    var email by remember { mutableStateOf("") }
    var employeeId by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    var userType by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var formError by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    val userTypes = listOf("Admin", "Employee")


    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Profile") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = {  },
                label = { Text("Email") },
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = employeeId,
                onValueChange = { employeeId = it },
                label = { Text("Employee ID") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = department,
                onValueChange = { department = it },
                label = { Text("Department") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Dropdown for Leave Type
            Box(modifier = modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = userType,
                    onValueChange = { /* leaveType is set by dropdown */ },
                    label = { Text("User Type") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true },
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown",
                            modifier = modifier.clickable {
                                expanded = true
                            }
                        )
                    },
                    isError = userType.isBlank() && formError.isNotEmpty()
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = modifier.fillMaxWidth()
                ) {
                    userTypes.forEach { type ->
                        DropdownMenuItem(
                            onClick = {
                                userType = type
                                expanded = false
                            },
                            text = {
                                Text(type)
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        userPreferences.saveUser(userName, email, userType)

                        // Navigate to another screen
                        onNavigate("Dashboard")

                    }
                }
            ) {
                Text("Setup Profile")
            }

            if (formError.isNotEmpty()) {
                Text(formError, color = MaterialTheme.colorScheme.error)
            }
        }
    }

}

@Preview
@Composable
private fun PreviewProfileScreen() {
    MyLeaveManagerTheme {
        val context = LocalContext.current
        ProfileScreen(
            userPreferences = UserPreferences(context)
        ) { }
    }
}