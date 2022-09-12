package com.victorhvs.lotonaticos.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.victorhvs.lotonaticos.R
import com.victorhvs.lotonaticos.data.fake.Mocks
import com.victorhvs.lotonaticos.domain.models.ContestResult
import com.victorhvs.lotonaticos.domain.models.Lottery
import com.victorhvs.lotonaticos.presentation.theme.LotonaticosTheme
import com.victorhvs.lotonaticos.presentation.theme.gray
import com.victorhvs.lotonaticos.presentation.utils.dateToString
import java.util.Date

@Composable
fun CardItem(
    lottery: Lottery,
    contestResult: ContestResult,
    modifier: Modifier = Modifier
) {
    Card(
        shape = CardDefaults.outlinedShape,
        colors = CardDefaults.outlinedCardColors(),
        elevation = CardDefaults.outlinedCardElevation(),
        border = CardDefaults.outlinedCardBorder()
    ) {
        CardResultHeader(lottery, contestResult, modifier)
        CardResultNumbers(contestResult)
    }
}

@Composable
fun CardResultNumbers(contestResult: ContestResult) {
    Row(
        modifier = Modifier
            .background(gray)
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        contestResult.drawnNumbersSorted.forEach {
            RoundText(text = it.toString())
        }
    }
}

@Composable
fun CardResultHeader(
    lottery: Lottery,
    contestResult: ContestResult,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.clover),
            contentDescription = lottery.title,
            modifier = Modifier
                .size(40.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer, shape = CircleShape)
                .padding(8.dp),
            alignment = Alignment.Center
        )

        Column(
            modifier = modifier
                .padding(start = 16.dp)
                .weight(1.0f, true)
        ) {
            Text(
                text = lottery.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
                text = stringResource(
                    R.string.result_description,
                    contestResult.id,
                    contestResult.resultDrawAt.dateToString()
                )
            )
        }

        SuggestionChip(
            modifier = Modifier.wrapContentHeight(),
            onClick = { },
            label = {
                val winnerText =
                    if (contestResult.winnersList.isEmpty()) {
                        stringResource(id = R.string.jackpot)
                    } else {
                        pluralStringResource(
                            id = R.plurals.winners_count,
                            count = contestResult.winnersList.size,
                            contestResult.winnersList.size
                        )
                    }
                Text(winnerText)
            },
            icon = {
                Icon(
                    Icons.Filled.Groups,
                    contentDescription = "Localized description",
                    Modifier.size(SuggestionChipDefaults.IconSize),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            border = SuggestionChipDefaults.suggestionChipBorder(borderColor = Color.Transparent),
            colors = SuggestionChipDefaults.suggestionChipColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContestResultListPreview() {
    LotonaticosTheme {
        CardItem(
            Mocks.lotteryMock,
            ContestResult(
                id = "123",
                accumulated = true,
                amountRaised = 12.0,
                cityUfName = "Teresina - PI",
                drawLocation = "aeho",
                drawn05AmountRaised = 12.0,
                drawnNumbers = listOf(1, 2),
                drawnNumbersSorted = listOf(1, 2, 3, 4, 5, 6),
                especialDrawnAmountRaised = 123.0,
                nextDrawnAmountEstimated = 1234.0,
                nextDrawnAmountRaised = 12.0,
                nextDrawnAt = Date(),
                prizeSharingList = listOf(),
                resultDrawAt = Date(),
                updatedAt = Date(),
                winnersList = listOf()
            )
        )
    }
}
