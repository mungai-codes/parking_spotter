package com.mungaicodes.parkme.domain.repository

import com.mungaicodes.parkme.domain.ParkingSpot
import kotlinx.coroutines.flow.Flow


interface ParkingSpotRepository {

    suspend fun insertParkingSpot(spot: ParkingSpot)
    suspend fun deleteParkingSpot(spot: ParkingSpot)

    fun getParkingSpots(): Flow<List<ParkingSpot>>
}