package vn.thailam.presentation.user.register

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
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel(),
    onRegisterSuccess: () -> Unit,
    onGoToLogin: () -> Unit,
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val memOnRegisterSuccess by rememberUpdatedState(onRegisterSuccess)
    val isAlertDialogOpened = remember { mutableStateOf(false) }

    LaunchedEffect(viewModel, lifecycle) {
        // Whenever the uiState changes, check if the user is logged in and
        // call the `onUserLogin` event when `lifecycle` is at least STARTED
        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .collect {
                when (it) {
                    is RegisterScreenState.Error -> {
                        isAlertDialogOpened.value = true
                    }

                    is RegisterScreenState.Success -> memOnRegisterSuccess()
                    else -> Unit
                }
            }
    }

    Column {
        Text(text = "Register")

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
                viewModel.register(email = email.text, password = password.text)
            }
        ) {
            Text(text = "Register")
        }

        Text(
            text = "Already have an account?",
            modifier = Modifier.clickable {
                onGoToLogin()
            }
        )

        if (isAlertDialogOpened.value) {
            SimpleErrorDialog(
                errorMessage = viewModel.getErrorMessage(),
                isAlertDialogOpened = isAlertDialogOpened
            )
        }
    }
}
