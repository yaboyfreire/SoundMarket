package pt.iade.ei.martim.rodrigo.SoundMarket

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SettingsScreen()
        }
    }
}

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    var showDeleteDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(350.dp, 50.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Settings
        SettingsRow(
            title = "Profile Settings",
            icon = painterResource(id = R.drawable.person),
            onClick = {
                val intent = Intent(context, EditProfileActivity::class.java)
                context.startActivity(intent)
            }
        )

        // Language Selection
        LanguageSelectionRow()

        Spacer(modifier = Modifier.weight(1f))

        // Log Out Button
        Button(
            onClick = { val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Log Out")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Delete Account Button
        TextButton(
            onClick = { showDeleteDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .border(BorderStroke(2.dp, color = Color.Black), shape = RectangleShape)
        ) {
            Text(
                text = "Delete Account",
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
    if (showDeleteDialog) {
        DeleteAccountDialog(onDismiss = { showDeleteDialog = false }, onConfirm = {

            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
            //DELETE ACCOUNT HERE AFTER CHANGING ACTIVITY TO LOGIN

            showDeleteDialog = false
        })
    }
}

@Composable
fun SettingsRow(title: String, icon: Painter, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = title,
            modifier = Modifier.size(24.dp) // Adjust size as needed
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Navigate"
        )
    }
}

@Composable
fun LanguageSelectionRow() {
    var expanded by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("English") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { expanded = !expanded })
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(R.drawable.language),
                contentDescription = "Language Icon"
            )
            Text(
                text = selectedLanguage,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.weight(1f).padding(start = 15.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown",
                modifier = Modifier.size(24.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            //LANGUAGES LIST ADD MORE IF NEEDED
            DropdownMenuItem(onClick = { selectedLanguage = "English"; expanded = false }) {
                Text(text = "English")
            }
            DropdownMenuItem(onClick = { selectedLanguage = "Spanish"; expanded = false }) {
                Text(text = "Spanish")
            }
            DropdownMenuItem(onClick = { selectedLanguage = "Portuguese"; expanded = false }) {
                Text(text = "Portuguese")
            }

        }
    }
}


@Composable
fun DeleteAccountDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Are you sure you want to delete your account?")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                }
            ) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("No")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}
