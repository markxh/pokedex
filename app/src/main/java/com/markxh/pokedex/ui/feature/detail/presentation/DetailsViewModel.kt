package com.markxh.pokedex.ui.feature.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markxh.pokedex.ui.feature.detail.domain.usecase.GetPokemonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : ViewModel() {

    private val _state = MutableLiveData<PokemonDetailsState>()
    val state: LiveData<PokemonDetailsState> = _state
    
    fun getPokemonDetails(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonDetailsUseCase(name)
                .onSuccess {
                    _state.postValue(it?.let { PokemonDetailsState.Success(it) })
                }
                .onFailure { error ->
                    _state.postValue(
                        PokemonDetailsState.Error(
                            error.localizedMessage ?: "Unknown error"
                        )
                    )
                }
        }
    }
}