package ru.geekbrains.map.ui.edit.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.map.domain.AppState
import ru.geekbrains.map.domain.models.MarkerDomain
import kotlinx.coroutines.*


abstract class BaseEditViewModel : ViewModel() {
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

    abstract fun updateMarker(marker: MarkerDomain): Job
    abstract fun getMarker(markerId: Int): Job

    override fun onCleared() {
        super.onCleared()
        viewModelScopeCoroutine
            .coroutineContext
            .cancel()
    }
}