package vn.thailam.presentation.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun SimpleErrorDialog(
    errorMessage: String,
    isAlertDialogOpened: MutableState<Boolean>
) {
    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
            isAlertDialogOpened.value = false
        },
        title = {
            Text(text = "Error")
        },
        text = {
            Text((errorMessage))
        },
        confirmButton = {
            Button(
                onClick = {
                    isAlertDialogOpened.value = false
                }
            ) {
                Text("Confirm")
            }
        },
    )
}
