package cz.okycelt.pg.navigation.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseScreen(
        modifier = modifier,
        title = {
            Text(text = "Detail screen")
        },
        content = {
            Button(onClick = navigateBack) {
                Text(text = "Navigate back")
            }
        }
    )
}