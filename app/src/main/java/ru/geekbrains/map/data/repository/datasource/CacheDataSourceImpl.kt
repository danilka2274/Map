package ru.geekbrains.map.data.repository.datasource

import ru.geekbrains.map.data.mappers.markerToDataLayer
import ru.geekbrains.map.data.storage.Storage
import ru.geekbrains.map.domain.AppState
import ru.geekbrains.map.domain.models.*
import com.yandex.mapkit.geometry.Point


class CacheDataSourceImpl(private val storage: Storage) : CacheDataSource {
    override suspend fun addMarker(marker: MarkerDomain) =
        try {
            val result = storage
                .storageDao()
                .addMarker(markerToDataLayer(marker))
            AppState.Success(
                NewMarkerResult(
                    result > ZERO_INT, Point(
                        marker.latitude, marker.longitude
                    )
                )
            )
        } catch (err: Exception) {
            AppState.Error(err)
        }

    override suspend fun getMarkers() =
        try {
            val result = storage
                .storageDao()
                .getMarkers()
                .map { it.toDomain() }
            AppState.Success(MarkersResult(result))
        } catch (err: Exception) {
            AppState.Error(err)
        }

    override suspend fun getMarkerById(markerId: Int): AppState =
        try {
            val result = storage
                .storageDao()
                .getMarkerById(markerId = markerId)
                .toDomain()
            AppState.Success(MarkerResult(result = result))
        } catch (err: Exception) {
            AppState.Error(err)
        }

    override suspend fun removeMarker(markerId: Int) =
        try {
            val result = storage
                .storageDao()
                .removeMarker(markerId)
            AppState.Success(OperationResult(result > ZERO_INT))
        } catch (err: Exception) {
            AppState.Error(err)
        }

    override suspend fun updateMarker(marker: MarkerDomain) =
        try {
            val result = storage
                .storageDao()
                .updateMarker(markerToDataLayer(marker))
            AppState.Success(OperationResult(result > 0))
        } catch (err: Exception) {
            AppState.Error(err)
        }

    companion object {
        private const val ZERO_INT = 0
    }
}