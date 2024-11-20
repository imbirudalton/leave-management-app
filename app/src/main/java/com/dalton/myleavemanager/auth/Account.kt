package com.dalton.myleavemanager.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dalton.myleavemanager.R
import com.dalton.myleavemanager.User
import com.dalton.myleavemanager.UserPreferences
import com.dalton.myleavemanager.ui.theme.Primary
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    userPreferences: UserPreferences,
    onNavigate: (String) -> Unit
    ) {

    val user = userPreferences.userDetails.collectAsState(initial = User(username = "Dalton", email = "dalton@email.com", type = "Admin"))
    val scope = rememberCoroutineScope()

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Box() {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "profile picture",
                        modifier = modifier.size(80.dp)
                            .clip(CircleShape)
                            .border(1.dp, color = Primary, CircleShape)
                    )
                }

                Spacer(modifier.height(16.dp))

                Box() {
                    Text("${user.value?.username}", fontSize = 20.sp)
                }
            }


            Spacer(modifier.height(16.dp))

            Box(modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    onClick = {
                        scope.launch {
                            Firebase.auth.signOut()
                            userPreferences.clearUserDetails()
                            onNavigate("Login")
                        }
                }) {
                    Text("Logout")
                }
            }
        }

    }

}