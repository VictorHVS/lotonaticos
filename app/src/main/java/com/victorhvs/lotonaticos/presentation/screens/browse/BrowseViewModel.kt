package com.victorhvs.lotonaticos.presentation.screens.browse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorhvs.lotonaticos.data.LotteryRepository
import com.victorhvs.lotonaticos.domain.State
import com.victorhvs.lotonaticos.domain.models.Lottery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrowseViewModel @Inject constructor(
    private val repository: LotteryRepository
) : ViewModel() {

    private val _selectedBrewery: MutableStateFlow<State<List<Lottery>>> = MutableStateFlow(State.loading())
    val selectedBrewery: StateFlow<State<List<Lottery>>> = _selectedBrewery

    init {
        getAllPosts()
    }

    private fun getAllPosts() {
        viewModelScope.launch {
            repository.getAllLotteries().collect {
                _selectedBrewery.value = it
            }
        }
    }

}