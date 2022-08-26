package com.victorhvs.lotonaticos.data.repository

import com.victorhvs.lotonaticos.data.datasource.FirebaseDataSource
import com.victorhvs.lotonaticos.domain.State
import com.victorhvs.lotonaticos.domain.models.ContestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContestResultRepository @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource
) {

    fun getMegaResults() = flow<State<List<ContestResult>>> {

        emit(State.loading())

        val lotteries = firebaseDataSource.getMegaResults()
        emit(State.success(lotteries))

    }.catch {
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}