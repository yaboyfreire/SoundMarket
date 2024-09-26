package pt.iade.ei.martim.rodrigo.SoundMarket.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onSearchQueryChanged: (String) -> Unit,
    shape: Shape = RoundedCornerShape(16.dp) // Define the shape as rounded corners
) {
    var query by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = query,
        onValueChange = { newText ->
            query = newText
            onSearchQueryChanged(newText.text)
        },
        modifier = Modifier
            .fillMaxWidth(), // Fill the parent width
        shape = RoundedCornerShape(50.dp),  // Apply the rounded corners here
        placeholder = { Text(text = "Search") },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}