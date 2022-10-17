package com.mungaicodes.parkme.di

import android.app.Application
import androidx.room.Room
import com.mungaicodes.parkme.data.ParkingSpotDatabase
import com.mungaicodes.parkme.data.repository.ParkingSpotRepositoryImpl
import com.mungaicodes.parkme.domain.repository.ParkingSpotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ParkingSpotDatabase {
        return Room.databaseBuilder(
            app,
            ParkingSpotDatabase::class.java,
            "park_me_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(db: ParkingSpotDatabase): ParkingSpotRepository {
        return ParkingSpotRepositoryImpl(db.dao)
    }
}