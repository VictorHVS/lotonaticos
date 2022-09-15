package com.victorhvs.lotonaticos.presentation.screens.resultList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
    topBar: @Composable () -> Unit = {},
    viewModel: ResultsViewModel = hiltViewModel()
) {
    val state = viewModel.contestResults.collectAsState(initial = State.loading()).value
    BrowseContainer(topBar, state)
}

@Composable
fun BrowseContainer(
    topBar: @Composable () -> Unit = {},
    state: State<List<ContestResult>>
) {
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.inversePrimary),
        topBar = {
            topBar()
//            CenterAlignedTopAppBar(
//                modifier = Modifier.background(Color.Black),
//                title = {
//                    val globalText = stringResource(id = R.string.app_name)
//                    val spanStyles = listOf(
//                        AnnotatedString.Range(
//                            SpanStyle(fontWeight = FontWeight.Bold),
//                            start = 0,
//                            end = 4
//                        )
//                    )
//                    Text(text = AnnotatedString(globalText, spanStyles), style = MaterialTheme.typography.titleLarge)
//                }
//            )
        },
        content = {
            when (state) {
                is State.Failed -> {
                    Text(
                        modifier = Modifier.padding(it),
                        text = state.message
                    )
                }
                is State.Loading -> {
                    Text(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize(),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.loading)
                    )
                }
                is State.Success -> {
                    ContestResultList(modifier = Modifier.padding(it), results = state.data)
                }
            }
        }
    )
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
        BrowseContainer(state = state, )
    }
}
