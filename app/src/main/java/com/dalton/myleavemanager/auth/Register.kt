package com.dalton.myleavemanager.auth


import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dalton.myleavemanager.ui.theme.MyLeaveManagerTheme
import com.dalton.myleavemanager.ui.theme.Primary
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationPage(
    modifier: Modifier = Modifier,
//    auth: FirebaseAuth = Firebase.auth,
    onNavigate: (String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }
    var registrationError by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        topBar = {
            TopAppBar(title = { Text("REGISTER ACCOUNT") })
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
                onValueChange = {
                    email = it
                    emailError = null
                    registrationError = null
                },
                label = { Text("Email") },
                isError = emailError != null,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    cursorColor = Primary,
                    selectionColors = TextSelectionColors(
                        handleColor = Primary,
                        backgroundColor = Primary.copy(alpha = .3f)
                    ),
                    focusedLabelColor = Primary,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Primary,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Primary
                )
            )
            if (emailError != null) {
                Text(emailError!!, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = null
                    registrationError = null
                },
                label = { Text("Password") },
                isError = passwordError != null,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    cursorColor = Primary,
                    selectionColors = TextSelectionColors(
                        handleColor = Primary,
                        backgroundColor = Primary.copy(alpha = .3f)
                    ),
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Primary,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Primary
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            if (passwordError != null) {
                Text(passwordError!!, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    confirmPasswordError = null
                    registrationError = null
                },
                label = { Text("Confirm Password") },
                isError = confirmPasswordError != null,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    cursorColor = Primary,
                    selectionColors = TextSelectionColors(
                        handleColor = Primary,
                        backgroundColor = Primary.copy(alpha = .3f)
                    ),
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Primary,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Primary
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            if (confirmPasswordError != null) {
                Text(confirmPasswordError!!, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White
                ),
                onClick = {
                    var isValid = true
                    if (email.isBlank()) {
                        emailError = "Email cannot be empty"
                        isValid = false
                    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        emailError = "Invalid email address"
                        isValid = false
                    }
                    if (password.isBlank()) {
                        passwordError = "Password cannot be empty"
                        isValid = false
                    } else if (password != confirmPassword) {
                        confirmPasswordError = "Passwords do not match"
                        isValid = false
                    }
                    if (isValid) {
                        coroutineScope.launch {
                          /*  val error = signUp(auth, email, password)
                            if (error == null) {
                                onNavigate("Login")
                            } else {
                                registrationError = error
                            }*/
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Register")
            }

            if (registrationError != null) {
                Text(registrationError!!, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                colors = ButtonDefaults.buttonColors(
                    contentColor = Primary,
                    containerColor = Color.Transparent
                ),
                onClick = {
                    onNavigate("Login")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Already have an account? Login")
            }
        }
    }
}


@Preview
@Composable
private fun RegistrationPagePreview() {
    MyLeaveManagerTheme  {
        RegistrationPage() {

        }
    }

}
