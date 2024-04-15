package cz.okycelt.pg.navigation.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    navigateToDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseScreen(
        modifier = modifier,
        title = {
            Text(text = "Home screen")
        },
        content = {
            Button(onClick = navigateToDetail) {
                Text(text = "Navigate to detail")
            }
        }
    )
}