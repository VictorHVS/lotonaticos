package com.victorhvs.lotonaticos.data.fake

import com.victorhvs.lotonaticos.domain.models.ContestResult
import com.victorhvs.lotonaticos.domain.models.Lottery
import java.util.Date

object Mocks {

    val contestResultMock = ContestResult(
        resultDrawAt = Date(1659924818),
        amountRaised = 12314.22,
        drawnNumbersSorted = listOf(1, 2, 3, 4, 5, 6)
    )

    val lotteryMock = Lottery(
        uuid = "MEGa",
        title = "Mega-Sena",
        description = "A Mega-Sena é a loteria que paga milhões para o acertador dos 6 números. Para apostar você escolhe de 6 a 15 números, entre os 60 disponíveis no volante.",
        color = "#209869",
        enabled = true
    )
}