package ru.geekbrains.map.data.repository.datasource

import ru.geekbrains.map.domain.AppState
import ru.geekbrains.map.domain.models.MarkerDomain


interface CacheDataSource {
    suspend fun addMarker(marker: MarkerDomain): AppState
    suspend fun getMarkers(): AppState
    suspend fun getMarkerById(markerId: Int): AppState
    suspend fun removeMarker(markerId: Int): AppState
    suspend fun updateMarker(marker: MarkerDomain): AppState
}