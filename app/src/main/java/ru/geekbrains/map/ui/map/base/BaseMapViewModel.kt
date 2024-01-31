package ru.geekbrains.map.ui.map.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.map.domain.AppState
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.*


abstract class BaseMapViewModel : ViewModel() {
    private val operationLiveDataMut = MutableLiveData<AppState>()

    protected val viewModelScopeCoroutine = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    abstract fun handleError(throwable: Throwable): Any

    fun getOperationLiveData() = operationLiveDataMut

    abstract fun saveMarker(position: Point): Job
    abstract fun getMarkers(): Job

    override fun onCleared() {
        super.onCleared()
        viewModelScopeCoroutine
            .coroutineContext
            .cancel()
    }
}