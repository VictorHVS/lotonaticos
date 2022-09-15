package com.victorhvs.lotonaticos.presentation.screens.resultList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorhvs.lotonaticos.R
import com.victorhvs.lotonaticos.data.fake.Mocks.contestResultMock
import com.victorhvs.lotonaticos.data.fake.Mocks.lotteryMock
import com.victorhvs.lotonaticos.domain.State
import com.victorhvs.lotonaticos.domain.models.ContestResult
import com.victorhvs.lotonaticos.presentation.components.CardItem
import com.victorhvs.lotonaticos.presentation.theme.LotonaticosTheme

@Composable
fun ContestResultListScreen(
    modifier: Modifier = Modifier,
    viewModel: ResultsViewModel = hiltViewModel()
) {
    val state = viewModel.contestResults.collectAsState(initial = State.loading()).value
    BrowseContainer(modifier, state)
}

@Composable
fun BrowseContainer(
    modifier: Modifier = Modifier,
    state: State<List<ContestResult>>
) {
    when (state) {
        is State.Failed -> {
            Text(text = state.message)
        }
        is State.Loading -> {
            Text(
                modifier = Modifier
                    .fillMaxSize(),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.loading)
            )
        }
        is State.Success -> {
            ContestResultList(modifier = modifier, results = state.data)
        }
    }
}

@Composable
fun ContestResultList(
    results: List<ContestResult>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = results, key = { it.hashCode() }) { result ->
            CardItem(contestResult = result, lottery = lotteryMock)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview() {
    val results = listOf(
        contestResultMock.copy(id = "1"),
        contestResultMock.copy(id = "2", amountRaised = 12345.0),
        contestResultMock.copy(id = "3"),
        contestResultMock.copy(id = "4"),
        contestResultMock.copy(id = "5"),
        contestResultMock.copy(id = "6"),
        contestResultMock.copy(id = "7")
    )
    val state = State.Success(results)

    LotonaticosTheme {
        BrowseContainer(state = state)
    }
}
