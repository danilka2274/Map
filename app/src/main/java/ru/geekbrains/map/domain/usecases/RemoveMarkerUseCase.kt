package ru.geekbrains.map.domain.usecases

import ru.geekbrains.map.domain.AppState
import ru.geekbrains.map.domain.repository.CacheRepository


class RemoveMarkerUseCase(private val repository: CacheRepository) {
    suspend fun execute(markerId: Int): AppState =
        repository.removeMarker(markerId = markerId)
}