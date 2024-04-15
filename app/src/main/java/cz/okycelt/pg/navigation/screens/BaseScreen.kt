package cz.okycelt.pg.navigation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    title: @Composable () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = title) }
    ) { contentPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            content()
        }
    }
}