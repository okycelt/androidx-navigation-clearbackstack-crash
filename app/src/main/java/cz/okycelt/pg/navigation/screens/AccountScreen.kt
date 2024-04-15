package cz.okycelt.pg.navigation.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AccountScreen(
    logOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseScreen(
        modifier = modifier,
        title = {
            Text(text = "Account screen")
        },
        content = {
            Button(onClick = logOut) {
                Text(text = "Log out")
            }
        }
    )
}