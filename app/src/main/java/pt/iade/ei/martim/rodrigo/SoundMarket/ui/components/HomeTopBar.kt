package pt.iade.ei.martim.rodrigo.SoundMarket.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.martim.rodrigo.SoundMarket.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    onIconClick: (String) -> Unit // Callback to handle icon clicks
) {
    TopAppBar(
        modifier = Modifier.padding(top=25.dp,bottom=25.dp),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Logo on the left
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(400.dp)
                        .padding(end = 8.dp)
                )
            }
        },
        actions = {
            // Icons on the right
            IconButton(onClick = { onIconClick("account") }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Account"
                )
            }
            IconButton(onClick = { onIconClick("notifications") }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications"
                )
            }
            IconButton(onClick = { onIconClick("settings") }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings"
                )
            }

        },

    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeTopBar() {
    HomeTopBar(onIconClick = { /* No-op for preview */ })
}