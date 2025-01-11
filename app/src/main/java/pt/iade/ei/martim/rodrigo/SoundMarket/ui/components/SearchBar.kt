package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchAction: (String) -> Unit // Add the onSearchAction parameter
) {
    var localQuery by remember { mutableStateOf(query) }

    OutlinedTextField(
        value = localQuery,
        onValueChange = { newQuery ->
            localQuery = newQuery
            onSearchQueryChanged(newQuery) // Update the query in the parent composable
        },
        label = { Text("Search") },
        trailingIcon = {
            IconButton(onClick = { onSearchAction(localQuery) }) {
                Icon(Icons.Default.Search, contentDescription = "Search Icon")
            }
        },
        keyboardActions = KeyboardActions(
            onDone = {
                onSearchAction(localQuery) // Trigger search when "Enter" is pressed
            }
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
