package com.victorhvs.lotonaticos.data

import com.victorhvs.lotonaticos.CoroutinesRule
import com.victorhvs.lotonaticos.data.datasource.FirebaseDataSource
import com.victorhvs.lotonaticos.data.fake.Mocks
import com.victorhvs.lotonaticos.data.repository.ContestResultRepository
import com.victorhvs.lotonaticos.domain.State
import com.victorhvs.lotonaticos.domain.models.ContestResult
import io.kotest.matchers.collections.shouldContainAll
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class ContestResultRepositoryTest {

    private val datasource: FirebaseDataSource = mockk()

    @get:Rule
    val coroutineRule = CoroutinesRule()
    private val repository = ContestResultRepository(datasource, coroutineRule.dispatcherProvider)

    @Test
    fun `GIVEN an exception RETURN loading and fail WHEN getMegaresult is called`() = runTest {
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
        states shouldContainAll listOf(State.loading(), State.failed(""))

        job.cancel()
    }

    @Test
    fun `GIVEN an valid result RETURN loading and success WHEN getMegaresult is called`() =
        runTest {
            // GIVEN
            val states = mutableListOf<State<List<ContestResult>>>()

            coEvery {
                datasource.getMegaResults()
            } returns listOf(Mocks.contestResultMock)
            // WHEN
            val job = launch(coroutineRule.scope.coroutineContext) {
                repository.getMegaResults().toList(states)
            }

            // THEN
            states shouldContainAll listOf(
                State.loading(),
                State.success(listOf(Mocks.contestResultMock))
            )

            job.cancel()
        }

}
