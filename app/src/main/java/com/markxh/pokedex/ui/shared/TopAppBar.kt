package com.markxh.pokedex.ui.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    navigationIcon: ImageVector,
    actions: @Composable RowScope.() -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            Icon(
                imageVector = navigationIcon,
                contentDescription = null,
                modifier = Modifier
                    .clickable { }
                    .padding(12.dp)
            )
        },
        actions = actions
    )
}

@Composable
fun ToolbarAction(
    icon: ImageVector,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .padding(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}