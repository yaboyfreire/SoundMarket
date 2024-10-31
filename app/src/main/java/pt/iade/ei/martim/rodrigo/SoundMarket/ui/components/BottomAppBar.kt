package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
fun BottomAppBar( onIconClick: (String) -> Unit) {
    BottomAppBar(
        actions = {

            Box(
                modifier = Modifier
                    .padding(start = 40.dp)
            ) {
                Row {
                    IconButton(onClick = { onIconClick("home") }){
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    }
                }
            }


            IconButton(
                onClick = { onIconClick("add") },
                modifier = Modifier.padding(horizontal = 78.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }


            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
            ) {
                Row(horizontalArrangement = Arrangement.End) {
                    IconButton(onClick = { onIconClick("email") }){
                        Icon(Icons.Filled.Email, contentDescription = "Email")
                    }
                }
            }
        }
    )
}




@Preview(showBackground = true)
@Composable
fun PreviewBottomBar() {
    BottomAppBar(onIconClick = { /* op */ })
}