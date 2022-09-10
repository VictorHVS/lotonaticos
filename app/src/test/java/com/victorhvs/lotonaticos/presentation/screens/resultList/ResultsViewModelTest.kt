package com.victorhvs.lotonaticos.presentation.screens.resultList

import com.victorhvs.lotonaticos.CoroutinesRule
import com.victorhvs.lotonaticos.data.fake.Mocks
import com.victorhvs.lotonaticos.data.repository.ContestResultRepository
import com.victorhvs.lotonaticos.domain.State
import com.victorhvs.lotonaticos.domain.models.ContestResult
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

internal class ResultsViewModelTest {

    @get:Rule
    val coroutineRule = CoroutinesRule()

    private val repository = mockk<ContestResultRepository>()

    lateinit var viewModel: ResultsViewModel

    @Test
    fun `GIVEN an valid result RETURN State success WHEN viewModel is created`() =
        runTest {
            // GIVEN
            val success = State.success(listOf(Mocks.contestResultMock))

            coEvery {
                repository.getMegaResults()
            } returns flowOf(success)
            // WHEN
            // THEN
            viewModel = ResultsViewModel(repository, coroutineRule.dispatcherProvider)
            viewModel.contestResults.value shouldBe success
        }

    @Test
    fun `GIVEN an exception result RETURN State fail WHEN viewModel is created`() =
        runTest {
            // GIVEN
            val error = State.failed<List<ContestResult>>("ERROR")

            coEvery {
                repository.getMegaResults()
            } returns flowOf(error)
            // WHEN
            // THEN
            viewModel = ResultsViewModel(repository, coroutineRule.dispatcherProvider)
            viewModel.contestResults.value shouldBe error
        }
}