package pt.iade.ei.martim.rodrigo.SoundMarket

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import pt.iade.ei.martim.rodrigo.SoundMarket.models.API.RegisterRequestDTO
import pt.iade.ei.martim.rodrigo.SoundMarket.models.API.ResponseDTO
import retrofit2.Call
import java.util.Calendar

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterScreen(onTextClick = { navigateToLoginActivity() })
        }
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onTextClick: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var aboutMe by remember { mutableStateOf("") }

    // For Country Dropdown
    var countryExpanded by remember { mutableStateOf(false) }
    val countries = listOf("Portugal", "Spain", "France", "Germany", "Italy")
    var selectedCountry by remember { mutableStateOf("") }

    // For Gender Dropdown
    var genderExpanded by remember { mutableStateOf(false) }
    val genderOptions = listOf("Male", "Female")
    var selectedGender by remember { mutableStateOf("") }

    // For DatePicker
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    var birthdate by remember { mutableStateOf("") }
    val context = LocalContext.current

    var registerError by remember { mutableStateOf("") }

    // Icon for expanding/collapsing dropdown
    val countryIcon = if (countryExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    val genderIcon = if (genderExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    val authService = RetrofitClientSoundMarket.instance.create(AuthService::class.java)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp) // Adjusts spacing between items
    ) {
        item {
            // Logo and App Name
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(300.dp, 150.dp)
            )
        }

        item {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clickable { /* Handle click event here */ },
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.defaultpfp),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Profile Picture",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .padding(4.dp)
                )
            }
        }


        item {
            // Name Field
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                trailingIcon = { IconButton(onClick = { name = "" }) { Icon(painterResource(R.drawable.close), contentDescription = "Clear Name") } },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                trailingIcon = { IconButton(onClick = { username = "" }) { Icon(painterResource(R.drawable.close), contentDescription = "Clear Username") } },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                trailingIcon = { IconButton(onClick = { email = "" }) { Icon(painterResource(R.drawable.close), contentDescription = "Clear Email") } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
            )
        }

        item {
            // Password Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = if (passwordVisible) painterResource(R.drawable.visibility)
                            else painterResource(R.drawable.visibility_off),
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }

        item {
            // Country Dropdown
            OutlinedTextField(
                value = selectedCountry,
                onValueChange = { selectedCountry = it },
                label = { Text("Country") },
                modifier = Modifier
                    .fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    Icon(
                        countryIcon,
                        "contentDescription",
                        Modifier.clickable { countryExpanded = !countryExpanded }
                    )
                }
            )
        }

        item {
            // Country Dropdown Menu
            DropdownMenu(
                expanded = countryExpanded,
                onDismissRequest = { countryExpanded = false }
            ) {
                countries.forEach { country ->
                    DropdownMenuItem(onClick = {
                        selectedCountry = country
                        countryExpanded = false
                    }) {
                        Text(text = country)
                    }
                }
            }
        }

        item {
            // Gender Dropdown
            OutlinedTextField(
                value = selectedGender,
                onValueChange = { selectedGender = it },
                label = { Text("Gender") },
                modifier = Modifier
                    .fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    Icon(
                        genderIcon,
                        "contentDescription",
                        Modifier.clickable { genderExpanded = !genderExpanded }
                    )
                }
            )
        }

        item {
            // Gender Dropdown Menu
            DropdownMenu(
                expanded = genderExpanded,
                onDismissRequest = { genderExpanded = false }
            ) {
                genderOptions.forEach { gender ->
                    DropdownMenuItem(onClick = {
                        selectedGender = gender
                        genderExpanded = false
                    }) {
                        Text(text = gender)
                    }
                }
            }
        }

        item {
            // Birthdate Picker
            OutlinedTextField(
                value = birthdate,
                onValueChange = { birthdate = it },
                label = { Text("Birthdate") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = {
                        DatePickerDialog(
                            context,
                            { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                                birthdate = "$dayOfMonth/${monthOfYear + 1}/$year"
                            },
                            year,
                            month,
                            day
                        ).show()
                    }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Pick Date")
                    }
                }
            )
        }

        item {
            // AboutMe Field
            OutlinedTextField(
                value = aboutMe,
                onValueChange = { aboutMe = it },
                label = { Text("AboutMe") },
                trailingIcon = { IconButton(onClick = { aboutMe = "" }) { Icon(painterResource(R.drawable.close), contentDescription = "Clear Name") } },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            // Register Button
            Button(
                onClick = { authService.register(RegisterRequestDTO(email,password,name,selectedGender,username,selectedCountry)).enqueue(object : retrofit2.Callback<ResponseDTO> {
                    override fun onResponse(call: Call<ResponseDTO>, response: retrofit2.Response<ResponseDTO>) {
                        if (response.isSuccessful) {
                            val token = response.body()?.token
                            // Save token and navigate
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        } else {
                            // Log the error for debugging purposes
                            registerError = "Register failed: ${response.code()} - ${response.message()}"
                            response.errorBody()?.let {
                                // Optionally, you can also parse the error body to show more specific errors
                                Log.e("RegisterError", it.string())
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseDTO>, t: Throwable) {
                        registerError = "Network error: ${t.localizedMessage}"
                    }
                })},
                modifier = Modifier.size(300.dp, 48.dp),
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
            ) {
                Text(text = "Register", color = Color.White)
            }
        }

        item {
            // Signup Link
            Text(
                text = "Already have an account? Login",
                fontSize = 14.sp,
                color = Color.Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable { onTextClick() },
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(onTextClick = {  })
}
