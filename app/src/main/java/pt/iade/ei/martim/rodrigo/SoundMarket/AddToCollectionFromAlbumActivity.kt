package pt.iade.ei.martim.rodrigo.SoundMarket

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.tooling.preview.Preview

class AddToCollectionFromAlbumActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AddToCollectionFromCollectionScreen()

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddToCollectionFromAlbumScreen() {
    var albumName by remember { mutableStateOf("") }
    var format by remember { mutableStateOf("") }
    var condition by remember { mutableStateOf("") }

    // State for dropdown menus
    var isFormatDropdownExpanded by remember { mutableStateOf(false) }
    var isConditionDropdownExpanded by remember { mutableStateOf(false) }

    // Options for dropdown menus
    val formatOptions = listOf("Vinyl", "CD")
    val conditionOptions = listOf("New", "Very Good", "Good", "Used", "Damaged")

    // State for size and selected text
    var formatTextFieldSize by remember { mutableStateOf(Size.Zero) }
    var conditionTextFieldSize by remember { mutableStateOf(Size.Zero) }

    // Icons for dropdown
    val formatIcon = if (isFormatDropdownExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    val conditionIcon = if (isConditionDropdownExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Toolbar with logo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), // replace with your logo
                contentDescription = "Logo",
                modifier = Modifier.size(300.dp, 100.dp),
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxSize() // Makes the Column fill the parent container
                .padding(16.dp), // Adds padding around the entire Column
            verticalArrangement = Arrangement.Top, // Aligns items to the top
            horizontalAlignment = Alignment.CenterHorizontally // Centers items horizontally
        ) {


            Spacer(modifier = Modifier.height(48.dp))

            // Format Dropdown
            OutlinedTextField(
                value = format,
                onValueChange = { format = it },
                label = { Text("Format") },
                trailingIcon = {
                    Icon(
                        imageVector = formatIcon,
                        contentDescription = "Dropdown Icon",
                        Modifier.clickable { isFormatDropdownExpanded = !isFormatDropdownExpanded }
                    )
                },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        formatTextFieldSize = coordinates.size.toSize()
                    }
            )

            DropdownMenu(
                expanded = isFormatDropdownExpanded,
                onDismissRequest = { isFormatDropdownExpanded = false },
                modifier = Modifier.width(with(LocalDensity.current) { formatTextFieldSize.width.toDp() })
            ) {
                formatOptions.forEach { option ->
                    DropdownMenuItem(onClick = {
                        format = option
                        isFormatDropdownExpanded = false
                    }) {
                        Text(text = option)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Condition Dropdown
            OutlinedTextField(
                value = condition,
                onValueChange = { condition = it },
                label = { Text("Condition") },
                trailingIcon = {
                    Icon(
                        imageVector = conditionIcon,
                        contentDescription = "Dropdown Icon",
                        Modifier.clickable {
                            isConditionDropdownExpanded = !isConditionDropdownExpanded
                        }
                    )
                },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        conditionTextFieldSize = coordinates.size.toSize()
                    }
            )

            DropdownMenu(
                expanded = isConditionDropdownExpanded,
                onDismissRequest = { isConditionDropdownExpanded = false },
                modifier = Modifier.width(with(LocalDensity.current) { conditionTextFieldSize.width.toDp() })
            ) {
                conditionOptions.forEach { label ->
                    DropdownMenuItem(onClick = {
                        condition = label
                        isConditionDropdownExpanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }

            Spacer(modifier = Modifier.height(250.dp))

            // Add To Collection Button
            Button(
                onClick = { val intent = Intent(context, CollectionActivity::class.java)
                    context.startActivity(intent) },
                modifier = Modifier
                    .width(200.dp) // Set a specific width
                    .height(50.dp), // Keep the height as is
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
            ) {
                Text("Add To Collection", color = Color.White)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewAddToCollectionFromAlbum() {
    AddToCollectionFromAlbumScreen()
}
