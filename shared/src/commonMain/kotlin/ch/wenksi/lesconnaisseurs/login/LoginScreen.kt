package ch.wenksi.lesconnaisseurs.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onLogin: (email: String, password: String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val emailTextFieldState = rememberTextFieldState()
        Text(
            text = "LES CONNAISSEURS",
            style = MaterialTheme.typography.displayMedium,

        )
        Spacer(Modifier.height(24.dp))
        TextField(
            state = emailTextFieldState,
            label = {
                Text("Email")
            }
        )

        Spacer(Modifier.height(12.dp))

        val pwTextFieldState = rememberTextFieldState()
        TextField(
            state = pwTextFieldState,
            label = {
                Text("Email")
            }
        )

        Spacer(Modifier.height(16.dp))

        TextButton(onClick = {
            onLogin(
                emailTextFieldState.text.toString(),
                pwTextFieldState.text.toString(),
            )
        }) {
            Text("Login")
        }
    }
}
