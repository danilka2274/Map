package ru.geekbrains.map.domain.usecases

import ru.geekbrains.map.domain.AppState
import ru.geekbrains.map.domain.repository.CacheRepository


class GetMarkersUseCase(private val repository: CacheRepository) {
    suspend fun execute(): AppState =
        repository.getMarkers()
}