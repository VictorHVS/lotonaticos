package com.victorhvs.lotonaticos.domain.models

import java.util.*

data class ContestResult(
    val id: String,
    val accumulated: Boolean,
    val resultDrawAt: Date?,
    val nextDrawnAt: Date?,
    val drawnNumbers: List<Int>,
    val drawnNumbersSorted: List<Int>,
    val winnersList: List<WinnerByCity>,
    val prizeSharingList: List<PrizeSharing>,
    val drawLocation: String,
    val cityUfName: String,
    val amountRaised: Float,
    val nextDrawnAmountRaised: Float,
    val drawn05AmountRaised: Float,
    val especialDrawnAmountRaised: Float,
    val nextDrawnAmountEstimated: Float,
    val updatedAt: Date
) {

    data class WinnerByCity(
        val winnersCount: Int,
        val city: String,
        val uf: String,
        val prizeAmount: Float
    )

    data class PrizeSharing(
        val trackDescription: String,
        val track: Int,
        val winnersCount: Int,
        val prizeAmount: Float
    )
}