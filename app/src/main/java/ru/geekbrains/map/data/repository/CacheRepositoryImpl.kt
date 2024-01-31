package ru.geekbrains.map.data.repository

import ru.geekbrains.map.data.repository.datasource.CacheDataSource
import ru.geekbrains.map.domain.AppState
import ru.geekbrains.map.domain.models.MarkerDomain
import ru.geekbrains.map.domain.repository.CacheRepository


class CacheRepositoryImpl(private val dataSource: CacheDataSource) : CacheRepository {
    override suspend fun addMarker(marker: MarkerDomain): AppState =
        dataSource.addMarker(marker = marker)

    override suspend fun getMarkers(): AppState =
        dataSource.getMarkers()

    override suspend fun getMarkerById(markerId: Int): AppState =
        dataSource.getMarkerById(markerId = markerId)

    override suspend fun removeMarker(markerId: Int): AppState =
        dataSource.removeMarker(markerId = markerId)

    override suspend fun updateMarker(marker: MarkerDomain): AppState =
        dataSource.updateMarker(marker = marker)
}