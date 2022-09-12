package com.victorhvs.lotonaticos.presentation.screens.resultList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorhvs.lotonaticos.data.repository.ContestResultRepository
import com.victorhvs.lotonaticos.domain.State
import com.victorhvs.lotonaticos.domain.models.ContestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val repository: ContestResultRepository
) : ViewModel() {

    private val _contestResults: MutableStateFlow<State<List<ContestResult>>> =
        MutableStateFlow(State.loading())
    val contestResults: StateFlow<State<List<ContestResult>>> = _contestResults

    init {
        getAllPosts()
    }

    private fun getAllPosts() = viewModelScope.launch {
        try {
            repository.getMegaResults().collect {
                _contestResults.value = it
            }
        } catch (exception: Exception) {
            _contestResults.value = State.failed("${exception.message}")
        }
    }
}
