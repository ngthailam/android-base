package vn.thailam.presentation.example

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ExampleScreen(
    modifier: Modifier = Modifier,
    exampleViewModel: ExampleViewModel = hiltViewModel()
) {
    val list = exampleViewModel.list.collectAsState(initial = listOf())

    LazyColumn {
        items(list.value) { listItem ->
            Text(text = "Example id=${listItem.title}")
        }
    }
}
