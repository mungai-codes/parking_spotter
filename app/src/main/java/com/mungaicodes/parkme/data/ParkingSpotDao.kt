package com.mungaicodes.parkme.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ParkingSpotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpot(spot: ParkingSpotEntity)

    @Delete
    suspend fun deleteSpot(spot: ParkingSpotEntity)

    @Query("select * from parkingspotentity")
    fun getAllSpots(): Flow<List<ParkingSpotEntity>>
}