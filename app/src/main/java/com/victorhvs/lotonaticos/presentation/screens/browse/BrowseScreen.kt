package com.victorhvs.lotonaticos.presentation.screens.browse

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.victorhvs.lotonaticos.domain.State
import com.victorhvs.lotonaticos.domain.models.Lottery

@Composable
fun BrowseScreen(
    navController: NavController,
    browseViewModel: BrowseViewModel = hiltViewModel()
) {
    val state = browseViewModel.selectedBrewery.collectAsState(initial = State.loading()).value
    BrowseContainer(state)


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseContainer(state: State<List<Lottery>>) {
    Scaffold(
        content = {
            when (state) {
                is State.Failed -> {
                    Text(
                        modifier = Modifier.padding(it),
                        text = state.message
                    )
                }
                is State.Loading -> {
                    Text(
                        modifier = Modifier.padding(it),
                        text = "LOADING"
                    )
                }
                is State.Success ->{
                    Text(
                        modifier = Modifier.padding(it),
                        text = state.data.map { it.title }.toString()
                    )
                }
            }
        }
    )
}

//@Preview(showSystemUi = true)
//@Composable
//fun BrowseScreenPreview() {
//    BrowseContainer()
//}