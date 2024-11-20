package com.dalton.myleavemanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dalton.myleavemanager.ui.theme.MyLeaveManagerTheme
import com.dalton.myleavemanager.ui.theme.Primary

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit
) {
    val email = remember { mutableStateOf("")}
    val password = remember { mutableStateOf("")}
    var passwordVisible = remember { mutableStateOf(false) }

    val emailError = remember { mutableStateOf("")}
    val passwordError = remember { mutableStateOf("")}

    Scaffold(modifier = modifier.fillMaxSize()
    ) { innerPadding ->

        Box(modifier = modifier
            .fillMaxSize()
            .padding(innerPadding),
            contentAlignment = Alignment.Center) {

            Column(
                modifier = modifier.fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("LOGIN")

                Spacer(modifier = modifier.height(16.dp))

                OutlinedTextField(
                    value = email.value,
                    onValueChange = {
                        email.value = it
                    },
                    placeholder = {
                        Text("Email")
                    },
                    isError = emailError.value.isNotEmpty(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Transparent,
                        unfocusedContainerColor = Transparent,
//                        focusedIndicatorColor = Primary,
//                        unfocusedIndicatorColor = Primary.copy(alpha = 0.5f),
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    modifier = modifier.fillMaxWidth()
                )

                Spacer(modifier = modifier.height(16.dp))

                OutlinedTextField(
                    value = password.value,
                    onValueChange = {
                        password.value = it
                    },
                    placeholder = {
                        Text("*****")
                    },
                    isError = passwordError.value.isNotEmpty(),
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Transparent,
                        unfocusedContainerColor = Transparent,
//                        focusedIndicatorColor = Primary,
//                        unfocusedIndicatorColor = Primary.copy(alpha = 0.5f),
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    trailingIcon = {
                        val icon = if (passwordVisible.value) {
                            R.drawable.eye_show_svgrepo_com
                        } else {
                            R.drawable.eye_off_svgrepo_com
                        }

                        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                            Icon(
                                painter = painterResource(icon),
                                contentDescription = if (passwordVisible.value) "Hide password" else "Show password",
                                modifier = modifier.size(24.dp)
                            )
                        }
                    },
                    modifier = modifier.fillMaxWidth()
                )

                Spacer(modifier = modifier.height(16.dp))

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        modifier = modifier,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary,
                        ),
                        onClick = {
                            if (email.value.isBlank()) {
                                emailError.value = "Invalid email address"
                                return@Button
                            }

                            if (password.value.length < 6) {
                                passwordError.value = "Password must be at least 6 characters"
                                return@Button
                            }
                            onNavigate("Dashboard")
                        }
                    ) {
                        Text("Login")
                    }
                }

            }
        }
    }

}

@Preview
@Composable
private fun LoginPreview() {
    MyLeaveManagerTheme() {
        LoginScreen (
            modifier = Modifier,
            onNavigate = {

            })
    }
}