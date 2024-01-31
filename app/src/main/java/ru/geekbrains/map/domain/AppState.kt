package ru.geekbrains.map.domain


import ru.geekbrains.map.domain.models.DataResult


sealed class AppState {
    data class Success(val data: DataResult) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}