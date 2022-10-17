package com.mungaicodes.parkme.presentation

import com.google.maps.android.compose.MapProperties
import com.mungaicodes.parkme.domain.ParkingSpot

data class MapState(
    val properties: MapProperties = MapProperties(),
    val parkingSpots: List<ParkingSpot> = emptyList(),
    val isFalloutMap: Boolean = false
)
