package com.mungaicodes.parkme.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import com.mungaicodes.parkme.MapStyle
import com.mungaicodes.parkme.domain.ParkingSpot
import com.mungaicodes.parkme.domain.repository.ParkingSpotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val repo: ParkingSpotRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MapState())
    val uiState: StateFlow<MapState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repo.getParkingSpots().collectLatest { spots ->
                _uiState.update {
                    it.copy(
                        parkingSpots = spots
                    )
                }
            }
        }
    }

    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.ToggleFalloutMap -> {
                _uiState.update { currentstate ->
                    currentstate.copy(
                        properties = MapProperties(
                            mapStyleOptions = if (_uiState.value.isFalloutMap) null else
                                MapStyleOptions(MapStyle.json)
                        ),
                        isFalloutMap = !_uiState.value.isFalloutMap
                    )
                }
            }
            is MapEvent.OnMapLongClick -> {
                viewModelScope.launch {
                    repo.insertParkingSpot(
                        ParkingSpot(event.latLng.latitude, event.latLng.longitude)
                    )
                }
            }
            is MapEvent.OnInfoWindowLongClick -> {
                viewModelScope.launch {
                    repo.deleteParkingSpot(event.spot)
                }
            }
        }
    }
}