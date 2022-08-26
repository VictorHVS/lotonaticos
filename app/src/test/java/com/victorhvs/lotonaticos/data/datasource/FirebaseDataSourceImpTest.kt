package com.victorhvs.lotonaticos.data.datasource

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FirebaseDataSourceImpTest {

    val client: FirebaseFirestore = mockk()
    val firebaseDataSourceImp = FirebaseDataSourceImp(client = client)

    @Test
    fun `GIVEN a valid datasource RETURN items WHEN getMegaResults is called`() = runTest(
        UnconfinedTestDispatcher()
    ) {
        // GIVEN
        coEvery { client.collection(any()).limit(any()).get().await() } returns mockk()
        // WHEN
        val results = firebaseDataSourceImp.getMegaResults()
        // THEN
        assert(results.isNotEmpty())
    }
}