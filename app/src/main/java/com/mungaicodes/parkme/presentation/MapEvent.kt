package com.mungaicodes.parkme.presentation

import com.google.android.gms.maps.model.LatLng
import com.mungaicodes.parkme.domain.ParkingSpot

sealed interface MapEvent {
    object ToggleFalloutMap: MapEvent
    data class OnMapLongClick(val latLng: LatLng): MapEvent
    data class OnInfoWindowLongClick(val spot: ParkingSpot): MapEvent
}