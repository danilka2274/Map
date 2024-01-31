package ru.geekbrains.map.ui.markers.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.map.domain.AppState
import kotlinx.coroutines.*


abstract class BaseMarkersViewModel : ViewModel() {
    private val operationLiveData = MutableLiveData<AppState>()

    protected val viewModelScopeCoroutine = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    abstract fun handleError(throwable: Throwable): Any

    fun getOperationLiveData() = operationLiveData

    abstract fun getMarkers(): Job
    abstract fun removeMarker(markerId: Int): Job
}