package com.example.dictionaryappwithcaching.feature_dictionary.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryappwithcaching.core.util.Resource
import com.example.dictionaryappwithcaching.feature_dictionary.domain.usecases.GetWordInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfoUseCase: GetWordInfoUseCase
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(WordInfoState())
    val state: State<WordInfoState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    sealed class UIEvent {
        data class ShowSnackBar(val message: String): UIEvent()
    }

    fun onSearch(query: String) {
        _searchQuery.value = query
        // Cancelling the job if another letter is typed withing a time frame of 500ms.
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            // Waiting for the user to type and pause to make a search call
            delay(500L)
            getWordInfoUseCase(query)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                wordInfoItem = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                wordInfoItem = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(UIEvent.ShowSnackBar(
                                result.message ?: "Unknown Error"
                            ))
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                wordInfoItem = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }
}