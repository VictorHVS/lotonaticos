package com.victorhvs.lotonaticos.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.victorhvs.lotonaticos.domain.Constants.FIRESTORE_COLLECTION_MEGA
import com.victorhvs.lotonaticos.domain.Constants.FIRESTORE_CONTEST_DATEFIELD
import com.victorhvs.lotonaticos.domain.State
import com.victorhvs.lotonaticos.domain.models.ContestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ContestResultRepository @Inject constructor() {
    private val mLotteryCollection =
        FirebaseFirestore.getInstance().collection(FIRESTORE_COLLECTION_MEGA)

    fun getMegaResults() = flow<State<List<ContestResult>>> {

        emit(State.loading())

        val snapshot = mLotteryCollection.limit(15).orderBy(FIRESTORE_CONTEST_DATEFIELD, Query.Direction.DESCENDING).get().await()
        val lotteries = snapshot.toObjects(ContestResult::class.java)
        emit(State.success(lotteries))

    }.catch {
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}