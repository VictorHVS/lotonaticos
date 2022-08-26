package com.victorhvs.lotonaticos.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.victorhvs.lotonaticos.domain.Constants
import com.victorhvs.lotonaticos.domain.models.ContestResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

interface FirebaseDataSource {

    suspend fun getMegaResults(): List<ContestResult>
}

class FirebaseDataSourceImp(
    val client: FirebaseFirestore,
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : FirebaseDataSource {

    override suspend fun getMegaResults(): List<ContestResult> {
        return withContext(dispatcher) {
            val mLotteryCollection = client.collection(Constants.FIRESTORE_COLLECTION_MEGA)

            val snapshot = mLotteryCollection.limit(15).get()
//            .orderBy(Constants.FIRESTORE_CONTEST_DATEFIELD, Query.Direction.DESCENDING).get()
                .await()
            snapshot.toObjects(ContestResult::class.java)
        }
    }
}