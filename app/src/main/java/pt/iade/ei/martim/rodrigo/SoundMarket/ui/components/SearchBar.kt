package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchAction: (String) -> Unit
) {
    var localQuery by remember { mutableStateOf(query) }

    OutlinedTextField(
        value = localQuery,
        onValueChange = { newQuery ->
            localQuery = newQuery
            onSearchQueryChanged(newQuery)
        },
        label = { Text("Search for albums...") },
        trailingIcon = {
            IconButton(
                onClick = { onSearchAction(localQuery) }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        keyboardActions = KeyboardActions(
            onDone = {
                onSearchAction(localQuery)
            }
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp,bottom =20.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            cursorColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchBar() {
    MaterialTheme {
        SearchBar(
            query = "",
            onSearchQueryChanged = {},
            onSearchAction = {}
        )
    }
}