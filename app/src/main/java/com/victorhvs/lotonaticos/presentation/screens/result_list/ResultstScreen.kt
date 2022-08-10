package com.victorhvs.lotonaticos.presentation.screens.result_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.victorhvs.lotonaticos.R
import com.victorhvs.lotonaticos.data.fake.Mocks.contestResultMock
import com.victorhvs.lotonaticos.data.fake.Mocks.lotteryMock
import com.victorhvs.lotonaticos.domain.State
import com.victorhvs.lotonaticos.domain.models.ContestResult
import com.victorhvs.lotonaticos.domain.models.Lottery
import com.victorhvs.lotonaticos.presentation.shared.RoundText
import com.victorhvs.lotonaticos.presentation.theme.LotonaticosTheme
import com.victorhvs.lotonaticos.presentation.utils.dateToString
import com.victorhvs.lotonaticos.presentation.utils.toFormatedCurrency
import java.util.Date
import kotlin.math.ceil

@Composable
fun ContestResultListScreen(
    navController: NavController,
    viewModel: ResultsViewModel = hiltViewModel()
) {
    val state = viewModel.contestResults.collectAsState(initial = State.loading()).value
    BrowseContainer(state, navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseContainer(
    state: State<List<ContestResult>>,
    navController: NavController
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Toolbar()
                }
            )
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
                        modifier = Modifier.padding(it),
                        text = "LOADING"
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
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items = results, key = { it.hashCode() }) { result ->
            ContestResultItem(contestResult = result, lottery = lotteryMock)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContestResultItem(
    lottery: Lottery,
    contestResult: ContestResult,
    modifier: Modifier = Modifier
) {

    val configuration = LocalConfiguration.current

    val height by remember {
        val screenWidth = configuration.screenWidthDp.dp
        val lines = ceil(contestResult.drawnNumbersSorted.size / screenWidth.div(55.dp))
        val height = lines.times(57.dp + lines.dp)
        mutableStateOf(height)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        Row(
            modifier = modifier
                .padding(horizontal = 4.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.clover_neutral),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically),
                alignment = Alignment.Center,
                colorFilter = ColorFilter.tint(Color(0xFF8fcbb3), BlendMode.Modulate),
            )

            Column(modifier = modifier.padding(4.dp)) {
                Text(
                    text = stringResource(
                        R.string.result_title,
                        lottery.title,
                        contestResult.id
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    modifier = modifier,
                    style = MaterialTheme.typography.bodySmall,
                    text = stringResource(
                        R.string.result_description,
                        contestResult.resultDrawAt.dateToString(),
                        contestResult.nextDrawnAmountEstimated.toFormatedCurrency()
                    )
                )
            }
        }

        LazyVerticalGrid(
            modifier = modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(height),
            userScrollEnabled = false,
            columns = GridCells.Fixed(6),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(contestResult.drawnNumbersSorted, key = { it.hashCode() }) { numberSorted ->
                RoundText(modifier = modifier.padding(2.dp), text = numberSorted.toString())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContestResultListPreview() {
    LotonaticosTheme {
        ContestResultItem(
            lotteryMock,
            ContestResult(
                id = "123",
                accumulated = true,
                amountRaised = 12.0,
                cityUfName = "Teresina - PI",
                drawLocation = "aeho",
                drawn05AmountRaised = 12.0,
                drawnNumbers = listOf(1, 2),
                drawnNumbersSorted = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
                especialDrawnAmountRaised = 123.0,
                nextDrawnAmountEstimated = 1234.0,
                nextDrawnAmountRaised = 12.0,
                nextDrawnAt = null,
                prizeSharingList = listOf(),
                resultDrawAt = null,
                updatedAt = Date(),
                winnersList = listOf()
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResultsScreenPreview() {
    val results = listOf(
        contestResultMock.copy(id = "1"),
        contestResultMock.copy(id = "2", amountRaised = 12345.0),
        contestResultMock.copy(id = "3"),
        contestResultMock.copy(id = "4"),
        contestResultMock.copy(id = "5"),
        contestResultMock.copy(id = "6"),
        contestResultMock.copy(id = "7"),
    )
    val state = State.Success(results)

    LotonaticosTheme {
        BrowseContainer(state = state, navController = NavController(LocalContext.current))
    }
}

@Composable
fun Toolbar() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.clover),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier
                .size(24.dp),
            alignment = Alignment.Center,
            colorFilter = ColorFilter.tint(Color(0xFF8fcbb3), BlendMode.Modulate),
        )
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ToolbarPreview() {
    LotonaticosTheme {
        Toolbar()
    }
}