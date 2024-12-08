package pt.iade.ei.martim.rodrigo.SoundMarket

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.martim.rodrigo.SoundMarket.APIStuff.AuthService
import pt.iade.ei.martim.rodrigo.SoundMarket.APIStuff.RetrofitClientSoundMarket
import pt.iade.ei.martim.rodrigo.SoundMarket.models.API.LoginRequestDTO
import pt.iade.ei.martim.rodrigo.SoundMarket.models.API.ResponseDTO
import pt.iade.ei.martim.rodrigo.SoundMarket.utils.SessionManager

class LoginActivity : ComponentActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionManager = SessionManager(this) // Initialize session manager

        setContent {
            // Pass the sessionManager to the composable
            LoginScreen(onTextClick = { navigateToRegisterActivity() }, sessionManager = sessionManager)
        }
    }

    private fun navigateToRegisterActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun LoginScreen(onTextClick: () -> Unit, sessionManager: SessionManager) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf("") }
    val context = LocalContext.current
    val authService = RetrofitClientSoundMarket.instance.create(AuthService::class.java)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo and App Name
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(300.dp, 150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("EMAIL") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("PASSWORD") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = if (passwordVisible) painterResource(R.drawable.visibility)
                        else painterResource(R.drawable.visibility_off),
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (loginError.isNotEmpty()) {
            Text(
                text = loginError,
                color = Color.Red,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        // Login Button
        Button(
            onClick = {
                authService.login(LoginRequestDTO(email, password)).enqueue(object : retrofit2.Callback<ResponseDTO> {
                    override fun onResponse(
                        call: retrofit2.Call<ResponseDTO>,
                        response: retrofit2.Response<ResponseDTO>
                    ) {
                        if (response.isSuccessful) {
                            val token = response.body()?.token
                            val userId = response.body()?.userId

                            if (token != null && userId != null) {
                                // Save token and userId in SessionManager
                                sessionManager.saveAuthToken(token)
                                sessionManager.saveUserId(userId) // Save the userId received from the backend

                                Log.d("Login", "Token: $token, User ID: $userId")

                                // Proceed to the MainActivity after login
                                val intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)
                            } else {
                                loginError = "Login failed: Missing token or user ID"
                            }
                        } else {
                            loginError = "Login failed: ${response.code()}"
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<ResponseDTO>, t: Throwable) {
                        loginError = "Network error: ${t.message}"
                    }
                })
            },
            modifier = Modifier.size(300.dp, 48.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            shape = RoundedCornerShape(30)
        ) {
            Text(text = "Login", color = Color.White)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Signup Link
        Text(
            text = "Don't have an account yet? Sign up",
            fontSize = 14.sp,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable { onTextClick() },
            textDecoration = TextDecoration.Underline
        )

        Spacer(modifier = Modifier.height(200.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    // Pass the sessionManager to the preview
    val sessionManager = SessionManager(LocalContext.current)
    LoginScreen(onTextClick = { /* Preview click handler */ }, sessionManager = sessionManager)
}

