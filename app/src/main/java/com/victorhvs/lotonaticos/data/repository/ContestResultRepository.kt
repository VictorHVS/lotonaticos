package com.victorhvs.lotonaticos.data.repository

import com.victorhvs.lotonaticos.core.DispatcherProvider
import com.victorhvs.lotonaticos.data.datasource.FirebaseDataSource
import com.victorhvs.lotonaticos.domain.State
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContestResultRepository @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource,
    private val dispatcher: DispatcherProvider
) {

    fun getMegaResults() = flow {
        emit(State.loading())

        val lotteries = firebaseDataSource.getMegaResults()
        emit(State.success(lotteries))
    }.catch {
        emit(State.failed(it.message.toString()))
    }.flowOn(dispatcher.io())
}
