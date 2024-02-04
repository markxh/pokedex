package com.markxh.pokedex.ui.feature.dashboard.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markxh.pokedex.ui.feature.dashboard.domain.usecase.GetPokemonDetailsUseCase
import com.markxh.pokedex.ui.feature.dashboard.domain.usecase.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : ViewModel() {

    private val _pokemonState = MutableLiveData<PokemonState>()
    val pokemonState: LiveData<PokemonState> = _pokemonState

    init {
        _pokemonState.postValue(PokemonState.Loading)
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonUseCase()
                .onSuccess { pokemonList ->
                    _pokemonState.postValue(pokemonList?.let { PokemonState.Success(it) })
                }
                .onFailure { error ->
                    _pokemonState.postValue(
                        PokemonState.Error(
                            error.localizedMessage ?: "Unknown error"
                        )
                    )
                }
        }
    }

//    private fun getPokemonDetails(name: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            getPokemonDetailsUseCase(name)
//                .onSuccess { pokemon ->
//                    _pokemonState.postValue(pokemon?.let { PokemonState.Success(it) })
//                }
//                .onFailure { error ->
//                    _pokemonState.postValue(
//                        PokemonState.Error(
//                            error.localizedMessage ?: "Unknown error"
//                        )
//                    )
//                }
//        }
//    }
}