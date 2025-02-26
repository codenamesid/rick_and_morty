package com.example.rickandmorty.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.data.Character
import com.example.rickandmorty.model.repository.CharacterRepository
import com.example.rickandmorty.model.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LoadingState {
    data object Initialize : LoadingState()
    data object Loading : LoadingState()
    data object Success : LoadingState()
    data class Error(val message: String) : LoadingState()
}

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState.Initialize)
    val loadingState: StateFlow<LoadingState> = _loadingState

    fun searchCharacters(name: String) {
        viewModelScope.launch {
            _loadingState.value = LoadingState.Loading
            when (val result = repository.getCharacters(name)) {
                is Result.Success -> {
                    _characters.value = result.data.results
                    _loadingState.value = LoadingState.Success
                }

                is Result.Error -> {
                    _loadingState.value = LoadingState.Error(result.message)
                }

                else -> {
                    _loadingState.value = LoadingState.Error("Unknown error")
                }
            }
        }
    }
}