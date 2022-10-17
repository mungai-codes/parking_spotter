package com.mungaicodes.parkme.data.repository

import com.mungaicodes.parkme.data.ParkingSpotDao
import com.mungaicodes.parkme.data.toParkingSpot
import com.mungaicodes.parkme.data.toParkingSpotEntity
import com.mungaicodes.parkme.domain.ParkingSpot
import com.mungaicodes.parkme.domain.repository.ParkingSpotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ParkingSpotRepositoryImpl(
    private val dao: ParkingSpotDao
) : ParkingSpotRepository {

    override suspend fun insertParkingSpot(spot: ParkingSpot) =
        dao.insertSpot(spot.toParkingSpotEntity())

    override suspend fun deleteParkingSpot(spot: ParkingSpot) =
        dao.deleteSpot(spot.toParkingSpotEntity())

    override fun getParkingSpots(): Flow<List<ParkingSpot>> =
        dao.getAllSpots().map { spots ->
            spots.map { it.toParkingSpot() }
        }
}