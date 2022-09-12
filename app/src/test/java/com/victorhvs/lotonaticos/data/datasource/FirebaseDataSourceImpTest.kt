package com.victorhvs.lotonaticos.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.victorhvs.lotonaticos.CoroutinesRule
import com.victorhvs.lotonaticos.domain.models.ContestResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import java.util.Date

class FirebaseDataSourceImpTest {

    val client: FirebaseFirestore = mockk()

    @get:Rule
    val coroutineRule = CoroutinesRule()

    val firebaseDataSourceImp = FirebaseDataSourceImp(
        client = client,
        dispatcher = coroutineRule.dispatcherProvider
    )

    val contestResultMock = ContestResult(
        resultDrawAt = Date(1659924818),
        amountRaised = 12314.22,
        drawnNumbersSorted = listOf(1, 2, 3, 4, 5, 6)
    )

    @Test
    fun `GIVEN a valid datasource RETURN items WHEN getMegaResults is called`() = runTest {
        // GIVEN
        prepareScenario()
        // WHEN
        val results = firebaseDataSourceImp.getMegaResults()
        // THEN
        assert(results.isNotEmpty())
    }

    private fun prepareScenario(
        result: ContestResult = contestResultMock
    ) {
        mockkStatic("kotlinx.coroutines.tasks.TasksKt")

        val querySnap = mockk<QuerySnapshot>()
        coEvery {
            val query = client.collection(any())
                .limit(any())
                .orderBy(any<String>(), any())
                .get()
            query.await()
        } returns querySnap

        every { querySnap.toObjects(ContestResult::class.java) } returns listOf(result)
    }
}
