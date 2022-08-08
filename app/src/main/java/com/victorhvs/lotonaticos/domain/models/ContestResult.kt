package com.victorhvs.lotonaticos.domain.models

import com.google.firebase.firestore.PropertyName
import java.util.Date

data class ContestResult(
    @PropertyName("id") val id: String = "",
    @JvmField @PropertyName("accumulated") val accumulated: Boolean = false,
    @JvmField @PropertyName("result_draw_at") val resultDrawAt: Date? = null,
    @JvmField @PropertyName("next_drawn_at") val nextDrawnAt: Date? = null,
    @JvmField @PropertyName("drawn_numbers") val drawnNumbers: List<Int> = listOf(),
    @JvmField @PropertyName("drawn_numbers_sorted") val drawnNumbersSorted: List<Int> = listOf(),
    @JvmField @PropertyName("winners_list") val winnersList: List<WinnerByCity> = listOf(),
    @JvmField @PropertyName("prize_sharing_list") val prizeSharingList: List<PrizeSharing> = listOf(),
    @JvmField @PropertyName("draw_location") val drawLocation: String = "",
    @JvmField @PropertyName("city_uf_name") val cityUfName: String = "",
    @JvmField @PropertyName("amount_raised") val amountRaised: Double = 0.0,
    @JvmField @PropertyName("next_drawn_amount_raised") val nextDrawnAmountRaised: Double = 0.0,
    @JvmField @PropertyName("drawn_0_5_amount_raised") val drawn05AmountRaised: Double = 0.0,
    @JvmField @PropertyName("especial_drawn_amount_raised") val especialDrawnAmountRaised: Double = 0.0,
    @JvmField @PropertyName("next_drawn_amount_estimated") val nextDrawnAmountEstimated: Double = 0.0,
    @JvmField @PropertyName("updated_at") val updatedAt: Date = Date()
) {

    data class WinnerByCity(
        @PropertyName("winners_count") val winnersCount: Int = 0,
        @PropertyName("city") val city: String = "",
        @PropertyName("uf") val uf: String = "",
        @PropertyName("prize_amount") val prizeAmount: Double = 0.0
    )

    data class PrizeSharing(
        @JvmField @PropertyName("track_description") val trackDescription: String = "",
        @JvmField @PropertyName("track") val track: Int = 0,
        @JvmField @PropertyName("winners_count") val winnersCount: Int = 0,
        @JvmField @PropertyName("prize_amount") val prizeAmount: Double = 0.0
    )
}