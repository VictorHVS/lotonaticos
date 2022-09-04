package com.victorhvs.lotonaticos.data

import com.victorhvs.lotonaticos.CoroutinesRule
import com.victorhvs.lotonaticos.data.datasource.FirebaseDataSource
import com.victorhvs.lotonaticos.data.repository.ContestResultRepository
import com.victorhvs.lotonaticos.domain.State
import com.victorhvs.lotonaticos.domain.models.ContestResult
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import java.util.Date

class ContestResultRepositoryTest {

    val slot = slot<State<List<ContestResult>>>()
    val datasource: FirebaseDataSource = mockk()

    @get:Rule
    val coroutineRule = CoroutinesRule()
    val repository = ContestResultRepository(datasource, coroutineRule.dispatcherProvider)

    val contestResultMock = ContestResult(
        resultDrawAt = Date(1659924818),
        amountRaised = 12314.22,
        drawnNumbersSorted = listOf(1, 2, 3, 4, 5, 6)
    )

    @Test
    fun `GIVEN RETURN WHEN`() = runTest {
        // GIVEN
        val states = mutableListOf<State<List<ContestResult>>>()

        coEvery {
            datasource.getMegaResults()
        } throws Exception("")
        // WHEN
        val job = launch(coroutineRule.scope.coroutineContext) {
            repository.getMegaResults().toList(states)
        }

        // THEN
        states.first() shouldBeSameInstanceAs State.Loading
//        states.last() shouldBeSameInstanceAs State.Success(listOf(contestResultMock))

        job.cancel()
    }
}
