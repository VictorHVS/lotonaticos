package com.victorhvs.lotonaticos.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.victorhvs.lotonaticos.domain.Constants
import com.victorhvs.lotonaticos.domain.models.ContestResult
import com.victorhvs.lotonaticos.test.DispatcherProvider
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface FirebaseDataSource {
    suspend fun getMegaResults(): List<ContestResult>
}

class FirebaseDataSourceImp @Inject constructor(
    val client: FirebaseFirestore,
    val dispatcher: DispatcherProvider
) : FirebaseDataSource {

    override suspend fun getMegaResults(): List<ContestResult> {
        return withContext(dispatcher.io()) {
            val mLotteryCollection = client.collection(Constants.FIRESTORE_COLLECTION_MEGA)

            val snapshot = mLotteryCollection.limit(15)
                .orderBy(Constants.FIRESTORE_CONTEST_DATEFIELD, Query.Direction.DESCENDING)
                .get()
                .await()

            snapshot.toObjects(ContestResult::class.java)
        }
    }
}