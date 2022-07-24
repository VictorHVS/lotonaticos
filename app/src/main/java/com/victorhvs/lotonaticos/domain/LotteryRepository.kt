package com.victorhvs.lotonaticos.domain

import com.google.firebase.firestore.FirebaseFirestore
import com.victorhvs.lotonaticos.domain.Constants.FIRESTORE_COLLECTION_LOTTERY
import com.victorhvs.lotonaticos.domain.models.Lottery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LotteryRepository @Inject constructor() {
    private val mLotteryCollection =
        FirebaseFirestore.getInstance().collection(FIRESTORE_COLLECTION_LOTTERY)

    fun getAllLotteries() = flow<State<List<Lottery>>> {

        emit(State.loading())

        val snapshot = mLotteryCollection.get().await()
        val lotteries = snapshot.toObjects(Lottery::class.java)
        emit(State.success(lotteries))

    }.catch {
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}