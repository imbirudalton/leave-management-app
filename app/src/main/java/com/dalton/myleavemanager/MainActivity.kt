package com.dalton.myleavemanager

import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dalton.myleavemanager.admin.AdminScreenRoom
import com.dalton.myleavemanager.admin.LeaveDetailsScreenRoom
import com.dalton.myleavemanager.auth.AccountScreen
import com.dalton.myleavemanager.auth.LoginPage
import com.dalton.myleavemanager.auth.ProfileScreen
import com.dalton.myleavemanager.auth.RegistrationPage
import com.dalton.myleavemanager.database.room.entities.LeaveRecordEntity
import com.dalton.myleavemanager.database.room.entities.toLeaveRecord
import com.dalton.myleavemanager.ui.theme.MyLeaveManagerTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LeaveRecordViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LeaveRecordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LeaveRecordViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


class MainActivity : ComponentActivity() {
//    private val realm = MyApp.realm
private val viewModel: LeaveRecordViewModel by viewModels {
    LeaveRecordViewModelFactory(application)
}

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyLeaveManagerTheme {
                NavigationComponent(
                    modifier = Modifier,
//                    realm,
                    viewModel
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationComponent(
    modifier: Modifier,
//    realm: Realm,
    viewModel: LeaveRecordViewModel
) {
    val navController = rememberNavController()
    val context = LocalContext.current

    // Initialize UserPreferences
    val userPreferences = remember { UserPreferences(context) }
    val dbPreferences = remember { DBPreferences(context) }
    val user = userPreferences.userDetails.collectAsState(initial = null)

    NavHost(navController = navController, startDestination = "Login") {
        composable("Login") {
            val currentUser = remember{ mutableStateOf(Firebase.auth.currentUser) }
            val user = userPreferences.userDetails.collectAsState(initial = null)

            if (user.value != null && user.value?.username?.isNotEmpty() == true) {
//            if (currentUser.value != null) {
                navController.navigate("Dashboard") {
                    popUpTo("Login") { inclusive = true }
                }
            } else {
                LoginPage(
                    modifier = modifier,
                    onNavigate = {
                        navController.navigate(it) {
                            popUpTo("Login") { inclusive = true }
                        }
                    }
                )
            }
        }
        composable("Register") {
            RegistrationPage(
                modifier = modifier,
                onNavigate = {
                    navController.navigate(it) {
                        popUpTo("Register") { inclusive = true }
                    }
                }
            )
        }
        composable("Profile") {
            ProfileScreen(
                modifier = modifier,
                userPreferences = userPreferences,
                onNavigate = {
                    navController.navigate(it) {
                        popUpTo("Profile") { inclusive = true }
                    }
                }
            )
        }
        composable("Account") {
            AccountScreen(
                modifier = modifier,
                userPreferences = userPreferences,
                onNavigate = {
                    navController.navigate(it) {
                        popUpTo("Account") { inclusive = true }
                    }
                }
            )
        }
        composable("Dashboard") {
            val user = user.value
            when (user?.type) {
                "Admin", "Manager" -> {
                    AdminScreenRoom(
                        Modifier,
                        dbPreferences = dbPreferences,
                        viewModel = viewModel
                    ) {
                        navController.navigate(it)
                    }
                }
                "Employee" -> {

                    DashboardRoom (
                        Modifier,
                        userPreferences,
                        viewModel
                    ) {
                        navController.navigate(it)
                    }
                }
                else -> {
                   navController.navigate("Login")
                }
            }
        }
        composable("Admin") {
            AdminScreenRoom(
                Modifier,
                dbPreferences = dbPreferences,
                viewModel = viewModel
            ) {
                navController.navigate(it)
            }
        }
        composable("LeaveDetails") {
            val selectedRecordID = dbPreferences.selectedRecordId
            viewModel.getLeaveRecordById(selectedRecordID)
            val leaveRecord by viewModel.leaveRecordById.observeAsState(LeaveRecordEntity())

            LeaveDetailsScreenRoom (
                leaveRecord = leaveRecord.toLeaveRecord(),
                onApprove = {
                    viewModel.update(leaveRecord.copy(status = "Approved"))
                    navController.navigateUp()
                },
                onDecline = {
                    viewModel.update(leaveRecord.copy(status = "Declined"))
                    navController.navigateUp()
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyLeaveManagerTheme {
        Dashboard() {

        }
    }
}