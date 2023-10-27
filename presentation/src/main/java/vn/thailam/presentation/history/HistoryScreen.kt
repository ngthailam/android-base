package vn.thailam.presentation.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    historyViewModel: HistoryViewModel = viewModel()
) {
    val histories = historyViewModel.allHistories.collectAsState(initial = listOf())

    LazyColumn {
        items(histories.value) { history ->
            Text(text = "History id=${history.id}")
        }
    }
}
