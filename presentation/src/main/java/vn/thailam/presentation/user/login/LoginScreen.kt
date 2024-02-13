package vn.thailam.presentation.user.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.flowWithLifecycle
import vn.thailam.presentation.common.SimpleErrorDialog

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    onGoToRegister: () -> Unit,
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val memOnLoginSuccess by rememberUpdatedState(onLoginSuccess)
    val isAlertDialogOpened = remember { mutableStateOf(false) }

    LaunchedEffect(viewModel, lifecycle) {
        // Whenever the uiState changes, check if the user is logged in and
        // call the `onUserLogin` event when `lifecycle` is at least STARTED
        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .collect {
                when (it) {
                    is LoginScreenState.Success -> memOnLoginSuccess()
                    is LoginScreenState.Error -> isAlertDialogOpened.value = true
                    else -> Unit
                }
            }
    }
    Column {
        Text(text = "Login")

        TextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Email") },
        )

        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text(text = "Password") },
            placeholder = { Text(text = "password") },
        )

        Button(
            onClick = {
                viewModel.login(email = email.text, password = password.text)
            }
        ) {
            Text(text = "Confirm")
        }

        Text(
            text = "Doesn't have an account? Register now",
            modifier = Modifier.clickable {
                onGoToRegister()
            }
        )
    }

    if (isAlertDialogOpened.value) {
        SimpleErrorDialog(
            errorMessage = "Cannot login",
            isAlertDialogOpened = isAlertDialogOpened
        )
    }
}
