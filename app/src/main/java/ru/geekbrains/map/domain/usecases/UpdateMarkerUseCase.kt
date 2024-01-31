package ru.geekbrains.map.domain.usecases

import ru.geekbrains.map.domain.AppState
import ru.geekbrains.map.domain.models.MarkerDomain
import ru.geekbrains.map.domain.repository.CacheRepository

class UpdateMarkerUseCase(private val repository: CacheRepository) {
    suspend fun execute(marker: MarkerDomain): AppState =
        repository.updateMarker(marker = marker)
}